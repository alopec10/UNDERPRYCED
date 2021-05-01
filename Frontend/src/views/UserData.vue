<template>
  <div class="my-10">
    <h1 class="text-5xl">Datos del usuario</h1>
    <div class="inline-block w-1/2 mt-5">
      <form class="mt-10">


        <div class="grid gap-6 grid-cols-2 my-6 mx-auto items-center">
          <p>NOMBRE</p>
          <input v-model="name" type="text" placeholder="Nombre"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0"
                 @blur="$v.name.$touch()">
          <div v-if="nameErrors.length!==0">
            {{ nameErrors }}
          </div>
          <p>APELLIDOS</p>

          <input v-model="surname" type="text" placeholder="Apellidos"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0"
                 @blur="$v.surname.$touch()">
          <div v-if="surnameErrors.length!==0">
            {{ surnameErrors }}
          </div>
          <p>EMAIL</p>
          <input v-model="email" type="email" placeholder="Email"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0"
                 @blur="$v.email.$touch()">
          <div v-if="emailErrors.length!==0">
            {{ emailErrors }}
          </div>
          <p>CONTRASEÑA</p>
          <input v-model="password" type="password" placeholder="Contraseña"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0"
                 @blur="$v.password.$touch()">
          <div v-if="passwordErrors.length!==0">
            {{ passwordErrors }}
          </div>
          <p>DIRECCIÓN</p>
          <input v-model="address" type="text" placeholder="Dirección"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0"
                 @blur="$v.address.$touch()">
          <div v-if="addressErrors.length!==0">
            {{ addressErrors }}
          </div>
          <p>PAÍS</p>
          <input v-model="country" type="text" placeholder="País"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0"
                 @blur="$v.country.$touch()">
          <div v-if="countryErrors.length!==0">
            {{ countryErrors }}
          </div>
          <p>CÓDIGO POSTAL</p>
          <input v-model="zipCode" type="text" placeholder="Código postal"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0"
                 @blur="$v.zipCode.$touch()">
          <div v-if="zipCodeErrors.length!==0">
            {{ zipCodeErrors }}
          </div>
          <p>TELÉFONO</p>
          <input v-model="phoneNumber" type="text" placeholder="Nº teléfono"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0"
                 @blur="$v.phoneNumber.$touch()">
        </div>
        <div v-if="phoneNumberErrors.length!==0">
          {{ phoneNumberErrors }}
        </div>
        <div class="mt-10">
          <button @click="update" type="button"
                  class="bg-purple-500 text-xl p-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
            Guardar cambios
          </button>
        </div>
      </form>
    </div>
    <div class="inline-block ml-10 mb-10 w-2/5 items-center">
      <div class="">

        <img class="m-auto"
             src="https://cdn.shopify.com/s/files/1/0255/9429/8467/products/air-jordan-1-retro-high-og-court-purple-2-0-555088-500_7_1000x1000.jpg?v=1598850987"/>
      </div>
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

const validName = value => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return false
  }
  return /^.{1,20}$/.test(value)
}

const validSurname = value => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return false
  }
  return /^.{1,30}$/.test(value)
}

const validEmail = value => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return false
  }
  return /^(?=.{1,64}@)[A-Za-z0-9_-]+(\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\.[A-Za-z0-9-]+)*(\.[A-Za-z]{2,})$/.test(value)
}

const validPassword = value => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return true
  }
  return /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,30}$/.test(value)
}

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

const validPhoneNumber = value => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return true
  }
  return /^(\+\d{1,3}( )?)?((\(\d{3}\))|\d{3})[- .]?\d{3}[- .]?\d{4}$|^(\+\d{1,3}( )?)?(\d{3}[ ]?){2}\d{3}$|^(\+\d{1,3}( )?)?(\d{3}[ ]?)(\d{2}[ ]?){2}\d{2}$/.test(value)
}

export default {
  name: "UserData",
  mixins: [validationMixin],
  validations: {
    name: {required, validName},
    surname: {required, validSurname},
    email: {required, validEmail},
    password: {validPassword},
    address: {required, validAddress},
    country: {required, validCountry},
    zipCode: {required, validZipCode},
    phoneNumber: {validPhoneNumber}
  },
  data() {
    return {
      name: '',
      surname: '',
      email: '',
      password: '',
      address: '',
      country: '',
      zipCode: '',
      phoneNumber: ''
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
            this.name = userData.name
            this.surname = userData.surname
            this.email = userData.email
            this.address = userData.address
            this.country = userData.country
            this.zipCode = userData.zipCode
            this.phoneNumber = userData.phoneNumber
          })
          .catch(err => {
            console.log(err)

          })
    },
    update() {
      const userData = {
        name: this.name,
        surname: this.surname,
        email: this.email,
        password: this.password,
        address: this.address,
        country: this.country,
        zipCode: this.zipCode,
        phoneNumber: this.phoneNumber
      }
      axios({url: 'http://localhost:8888/user/update', data: userData, method: 'POST'})
          .then(resp => {
            console.log(resp)
          })
          .catch(err => {
            console.log(err)

          })
    }
  },
  computed: {
    nameErrors() {
      const errors = [];
      if (!this.$v.name.$dirty) return errors;
      !this.$v.name.validName &&
      errors.push("El nombre debe tener como máximo 20 caracteres.");
      !this.$v.name.required && errors.push("Campo requerido.");
      return errors;
    },
    surnameErrors() {
      const errors = [];
      if (!this.$v.surname.$dirty) return errors;
      !this.$v.surname.validSurname &&
      errors.push("El apellido debe tener como máximo 30 caracteres.");
      !this.$v.surname.required && errors.push("Campo requerido.");
      return errors;
    },
    emailErrors() {
      const errors = [];
      if (!this.$v.email.$dirty) return errors;
      !this.$v.email.validEmail &&
      errors.push("Formato inválido");
      !this.$v.email.required && errors.push("Campo requerido.");
      return errors;
    },
    passwordErrors() {
      const errors = [];
      if (!this.$v.password.$dirty) return errors;
      !this.$v.password.validPassword &&
      errors.push("Formato inválido, debe contener entre 8 y 30 caracteres, una letra minúscula, una letra mayúscula, un número y un símbolo especial");
      return errors;
    },
    addressErrors() {
      const errors = [];
      if (!this.$v.address.$dirty) return errors;
      !this.$v.address.validAddress &&
      errors.push("Formato inválido, debe contener entre 10 y 150 caracteres");
      !this.$v.address.required && errors.push("Campo requerido.");
      return errors;
    },
    countryErrors() {
      const errors = [];
      if (!this.$v.country.$dirty) return errors;
      !this.$v.country.validCountry &&
      errors.push("Formato inválido, debe contener entre 4 y 20 caracteres");
      !this.$v.country.required && errors.push("Campo requerido.");
      return errors;
    },
    zipCodeErrors() {
      const errors = [];
      if (!this.$v.zipCode.$dirty) return errors;
      !this.$v.zipCode.validZipCode &&
      errors.push("Formato inválido");
      !this.$v.zipCode.required && errors.push("Campo requerido.");
      return errors;
    },
    phoneNumberErrors() {
      const errors = [];
      if (!this.$v.phoneNumber.$dirty) return errors;
      !this.$v.phoneNumber.validPhoneNumber &&
      errors.push("Formato inválido");
      return errors;
    }
  }
}
</script>

<style scoped>
p {
  text-align: right
}

img {
  width: 30vw
}
</style>