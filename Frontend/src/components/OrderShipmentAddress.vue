<template>
  <div class="my-10">
    <h1 class="text-5xl">DATOS DEL USUARIO</h1>
    <div class="inline-block mt-5">
      <form class="mt-10">
        <div class="grid gap-6 grid-cols-3 my-6 mx-auto items-center">
          <p>DIRECCIÓN</p>
          <input v-model="info.address" type="text" placeholder="Dirección"
                 class="col-span-2 mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0">
          <div v-if="addressErrors.length!==0">
            {{ addressErrors }}
          </div>
          <p>PAÍS</p>
          <input v-model="info.country" type="text" placeholder="País"
                 class="col-span-2 mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0">
          <div v-if="countryErrors.length!==0">
            {{ countryErrors }}
          </div>
          <p>CÓDIGO POSTAL</p>
          <input v-model="info.zipCode" type="text" placeholder="Código postal"
                 class="col-span-2 mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0">
          <div v-if="zipCodeErrors.length!==0">
            {{ zipCodeErrors }}
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
const axios = require("axios");

import {validationMixin} from "vuelidate";
import {
  required,
  sameAs,
} from "vuelidate/lib/validators";

const validAddress = value => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return false
  }
  return /^.{10,150}$/.test(value)
}

const validCountry = value => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return false
  }
  return /^[A-Za-zÀ-ÖØ-öø-ÿ\s\-]{4,20}$/.test(value)
}

const validZipCode = value => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return false
  }
  return /^\d{5}(?:[-\s]\d{4})?$/.test(value)
}

export default {
  name: "OrderShipmentAddress",
  mixins: [validationMixin],
  validations: {
    info: {
      address: {required, validAddress},
      country: {required, validCountry},
      zipCode: {required, validZipCode},
    }
  },
  data() {
    return {
      info: {
        address: '',
        country: '',
        zipCode: '',
      }
    }
  },
  mounted() {
    this.getData()
  },
  methods: {
    getData() {
      axios({url: 'http://localhost:8888/user/data', method: 'GET'})
          .then(resp => {
            let userData = resp.data
            this.info.address = userData.address
            this.info.country = userData.country
            this.info.zipCode = userData.zipCode
          })
          .catch(err => {
            console.log(err)

          })
    },
    validateUpdateAddress(){
      this.$v.info.address.$touch()
      this.$v.info.country.$touch()
      this.$v.info.zipCode.$touch()
      return !this.$v.info.address.$error && !this.$v.info.country.$error && !this.$v.info.zipCode.$error
    }
  },
  computed: {
    addressErrors() {
      const errors = [];
      if (!this.$v.info.address.$dirty) return errors;
      !this.$v.info.address.validAddress &&
      errors.push("Formato inválido, debe contener entre 10 y 150 caracteres");
      !this.$v.info.address.required && errors.push("Campo requerido.");
      return errors;
    },
    countryErrors() {
      const errors = [];
      if (!this.$v.info.country.$dirty) return errors;
      !this.$v.info.country.validCountry &&
      errors.push("Formato inválido, debe contener entre 4 y 20 caracteres");
      !this.$v.info.country.required && errors.push("Campo requerido.");
      return errors;
    },
    zipCodeErrors() {
      const errors = [];
      if (!this.$v.info.zipCode.$dirty) return errors;
      !this.$v.info.zipCode.validZipCode &&
      errors.push("Formato inválido");
      !this.$v.info.zipCode.required && errors.push("Campo requerido.");
      return errors;
    },
  },
  watch: {
    info: {
      deep: true,
      handler() {
        if(this.validateUpdateAddress()){
          this.$emit('addressUpdated', this.$data.info)
        }
      }
    }
  }
}
</script>

<style scoped>
p {
  text-align: right
}
</style>