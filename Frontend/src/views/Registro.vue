<template>
  <div class="font-sans">
    <div class="relative min-h-screen flex flex-col sm:justify-center items-center bg-gray-100 ">
      <div class="relative sm:max-w-sm w-full">
        <div class="card bg-blue-400 shadow-lg  w-full h-full rounded-3xl absolute  transform -rotate-6"></div>
        <div class="card bg-red-400 shadow-lg  w-full h-full rounded-3xl absolute  transform rotate-6"></div>
        <div class="relative w-full rounded-3xl  px-6 py-4 bg-gray-100 shadow-md">
          <label for="" class="block text-sm text-gray-700 text-center font-semibold">
            Register
          </label>
          <form class="mt-10">

            <div>
              <input type="text" v-model="name" placeholder="Nombre"
                     class="mt-1 block w-full border-none bg-gray-100 h-11 rounded-xl shadow-lg hover:bg-blue-100 focus:bg-blue-100 focus:ring-0"
                     @blur="$v.name.$touch()">
            </div>
            <div v-if="nameErrors.length!==0">
              {{ nameErrors }}
            </div>
            <div class="mt-7">
              <input type="text" v-model="surname" placeholder="Apellido"
                     class="mt-1 block w-full border-none bg-gray-100 h-11 rounded-xl shadow-lg hover:bg-blue-100 focus:bg-blue-100 focus:ring-0"
                     @blur="$v.surname.$touch()">
            </div>
            <div v-if="surnameErrors.length!==0">
              {{ surnameErrors }}
            </div>
            <div class="mt-7">
              <input type="email" v-model="email" placeholder="Email"
                     class="mt-1 block w-full border-none bg-gray-100 h-11 rounded-xl shadow-lg hover:bg-blue-100 focus:bg-blue-100 focus:ring-0"
                     @blur="$v.email.$touch()">
            </div>
            <div v-if="emailErrors.length!==0">
              {{ emailErrors }}
            </div>
            <div class="mt-7">
              <input type="password" v-model="password" placeholder="Contraseña"
                     class="mt-1 block w-full border-none bg-gray-100 h-11 rounded-xl shadow-lg hover:bg-blue-100 focus:bg-blue-100 focus:ring-0"
                     @blur="$v.password.$touch()">
            </div>
            <div v-if="passwordErrors.length!==0">
              {{ passwordErrors }}
            </div>
            <div class="mt-7">
              <input type="password" v-model="password_confirmation" placeholder="Confirmar contraseña"
                     class="mt-1 block w-full border-none bg-gray-100 h-11 rounded-xl shadow-lg hover:bg-blue-100 focus:bg-blue-100 focus:ring-0"
                     @blur="$v.password_confirmation.$touch()">
            </div>
            <div v-if="passwordConfirmationErrors.length!==0">
              {{ passwordConfirmationErrors }}
            </div>
            <div class="mt-7">
              <button @click='register' type="button"
                      class="bg-blue-500 w-full py-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
                Registrar
              </button>
            </div>

            <div class="flex mt-7 items-center text-center">
              <hr class="border-gray-300 border-1 w-full rounded-md">
              <label class="block font-medium text-sm text-gray-700 w-full">
                Registrate con
              </label>
              <hr class="border-gray-300 border-1 w-full rounded-md">
            </div>

            <div class="flex mt-7 justify-center w-full">
              <button
                  class="mr-5 bg-blue-500 border-none px-4 py-2 rounded-xl cursor-pointer text-white shadow-xl hover:shadow-inner transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">

                Facebook
              </button>

              <button
                  class="bg-red-500 border-none px-4 py-2 rounded-xl cursor-pointer text-white shadow-xl hover:shadow-inner transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">

                Google
              </button>
            </div>

            <div class="mt-7">
              <div class="flex justify-center items-center">
                <label class="w-full text-sm text-gray-600">¿Ya tienes una cuenta?</label>
                <a href="/iniciarsesion"
                   class="w-full text-blue-500 transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
                  Login
                </a>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
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
    return false
  }
  return /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,30}$/.test(value)
}

export default {
  name: "Registro.vue",
  mixins: [validationMixin],
  validations: {
    name: {required, validName},
    surname: {required, validSurname},
    email: {required, validEmail},
    password: {required, validPassword},
    password_confirmation: {required, sameAsPassword: sameAs("password")}
  },
  data() {
    return {
      name: "",
      surname: "",
      email: "",
      password: "",
      password_confirmation: "",
    }
  },
  methods: {
    register: function () {
      this.$v.$touch();
      if (!this.$v.$invalid) {
        let data = {
          name: this.name,
          surname: this.surname,
          email: this.email,
          password: this.password,
        }
        this.$store.dispatch('register', data)
            .then(() => this.$router.push('/'))
            .catch(err => console.log(err))
      }
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
      !this.$v.password.required && errors.push("Campo requerido.");
      return errors;
    },
    passwordConfirmationErrors() {
      const errors = [];
      if (!this.$v.password_confirmation.$dirty) return errors;
      !this.$v.password_confirmation.sameAsPassword &&
      errors.push("Las dos contraseñas deben ser iguales");
      !this.$v.password_confirmation.required && errors.push("Campo requerido.");
      return errors;
    }
  }
}
</script>

<style scoped>

</style>