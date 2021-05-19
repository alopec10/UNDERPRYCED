<template>
  <div class="my-5">
    <div class="inline-block mt-5">
      <form class="mt-10">
        <h1 class="text-5xl font-semibold mb-10" style="font-family:'Quantico'">DATOS DEL USUARIO</h1>
        <div class="mx-auto mt-7">
          <div class="floating-input mb-5 relative">
            <input v-model="info.address" type="text" id="address"
                   class="border-2 border-purple-500 focus:outline-none rounded-xl hover:bg-purple-100 focus:bg-purple-100 w-full p-3 h-16"
                   placeholder="Dirección" autocomplete="off" @blur="$v.info.address.$touch()"/>
            <label for="address"
                   class="absolute top-0 left-0 px-3 py-5 h-full pointer-events-none transform origin-left transition-all duration-100 ease-in-out ">Dirección</label>
            <div v-if="addressErrors.length!==0" class="text-sm mt-2 text-red-400 absolute">
              {{ addressErrors[0] }}
            </div>
          </div>
          <div class="floating-input mb-5 relative">
            <input v-model="info.country" type="text" id="country"
                   class="border-2 border-purple-500 focus:outline-none rounded-xl hover:bg-purple-100 focus:bg-purple-100 w-full p-3 h-16"
                   placeholder="País" autocomplete="off" @blur="$v.info.country.$touch()"/>
            <label for="country"
                   class="absolute top-0 left-0 px-3 py-5 h-full pointer-events-none transform origin-left transition-all duration-100 ease-in-out ">País</label>
            <div v-if="countryErrors.length!==0" class="text-sm mt-2 text-red-400 absolute">
              {{ countryErrors[0] }}
            </div>
          </div>
          <div class="floating-input mb-5 relative">
            <input v-model="info.zipCode" type="text" id="zipCode"
                   class="border-2 border-purple-500 focus:outline-none rounded-xl hover:bg-purple-100 focus:bg-purple-100 w-full p-3 h-16"
                   placeholder="Código postal" autocomplete="off" @blur="$v.info.zipCode.$touch()"/>
            <label for="zipCode"
                   class="absolute top-0 left-0 px-3 py-5 h-full pointer-events-none transform origin-left transition-all duration-100 ease-in-out ">Código
              postal</label>
            <div v-if="zipCodeErrors.length!==0" class="text-sm mt-2 text-red-400 absolute">
              {{ zipCodeErrors[0] }}
            </div>
          </div>
        </div>
      </form>
      <button @click="nextPage" type="button" style="font-family:'Quantico'"
              class="bg-purple-500 text-xl p-3 px-5 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">

        SIGUIENTE
      </button>
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
    },
    nextPage(){
      if(this.validateUpdateAddress()){
        this.$emit('addressUpdated', this.$data.info)
        this.$emit('addressNext')
      }
    }
  },
  computed: {
    addressErrors() {
      const errors = [];
      if (!this.$v.info.address.$dirty) return errors;
      !this.$v.info.address.required && errors.push("Campo requerido.");
      !this.$v.info.address.validAddress &&
      errors.push("Formato inválido, debe contener entre 10 y 150 caracteres");
      return errors;
    },
    countryErrors() {
      const errors = [];
      if (!this.$v.info.country.$dirty) return errors;
      !this.$v.info.country.required && errors.push("Campo requerido.");
      !this.$v.info.country.validCountry &&
      errors.push("Formato inválido, debe contener entre 4 y 20 caracteres");
      return errors;
    },
    zipCodeErrors() {
      const errors = [];
      if (!this.$v.info.zipCode.$dirty) return errors;
      !this.$v.info.zipCode.required && errors.push("Campo requerido.");
      !this.$v.info.zipCode.validZipCode &&
      errors.push("Formato inválido");
      return errors;
    },
  }
}
</script>

<style scoped>
p {
  text-align: right
}

.floating-input > input::placeholder {
  color: transparent;
}

.floating-input > input:focus,
.floating-input > input:not(:placeholder-shown) {
  @apply pt-8
}

.floating-input > input:focus ~ label,
.floating-input > input:not(:placeholder-shown) ~ label {
  @apply opacity-75 scale-75 -translate-y-3 translate-x-1;
}

</style>