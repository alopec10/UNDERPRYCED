<template>
  <div class="font-sans">
    <div class="relative flex flex-col sm:justify-center items-center bg-purple-50 " style="height: 80vh">
      <div class="relative sm:max-w-sm w-full">
        <div class="card bg-purple-300 shadow-lg  w-full h-full rounded-3xl absolute  transform -rotate-6"></div>
        <div class="card bg-purple-500 shadow-lg  w-full h-full rounded-3xl absolute  transform rotate-6"></div>
        <div class="relative w-full rounded-3xl  px-6 py-4 bg-gray-100 shadow-md border-2 border-purple-500">
          <label class="block mt-4 text-2xl text-gray-700 text-center font-semibold">
            INICIAR SESIÓN
          </label>
          <form class="mt-8" v-on:submit.prevent="login">

            <div>
              <input v-model="email" type="email" placeholder="Email" class="mt-1 w-full block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg bg-purple-50 hover:bg-purple-100 focus:bg-purple-100 focus:ring-0 focus:outline-none">
            </div>

            <div class="mt-5">
              <input v-model="password" type="password" placeholder="Contraseña" class="mt-1 w-full block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg bg-purple-50 hover:bg-purple-100 focus:bg-purple-100 focus:ring-0 focus:outline-none">
            </div>

            <div class="flex mt-7 items-center text-center">
              <hr class="border-gray-300 border-1 w-full rounded-md">
              <label class="block font-medium text-sm text-gray-700 w-full">
                Accede ahora
              </label>
              <hr class="border-gray-300 border-1 w-full rounded-md">
            </div>

            <div class="mt-7">
              <button @click="login" type="button" class="bg-purple-500 w-full py-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
                INICIAR SESIÓN
              </button>
            </div>




            <div class="mt-7 mb-3">
              <div class="flex justify-center items-center">
                <label class="w-full text-md text-gray-600">¿Eres nuevo?</label>
                <a href="/registro" class="w-full text-purple-500 text-md transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
                  REGÍSTRATE AHORA
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
const axios = require("axios");

export default {
  name: "Login",
  data() {
    return {
      email: "",
      password: ""
    }

  },
  methods: {
      login: function () {
        let email = this.email
        let password = this.password
        console.log('b')
        this.$store.dispatch('login', { email, password })
            .then(() => this.$router.push('/'))
            .catch(err => console.log(err))
      }
    }

}
</script>

<style scoped>

</style>