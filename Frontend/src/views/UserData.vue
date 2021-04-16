<template>
  <div class="my-10">
    <h1 class="text-5xl">Datos del usuario</h1>
    <div class="inline-block w-1/2 mt-5">
      <form class="mt-10">


        <div class="grid gap-6 grid-cols-2 my-6 mx-auto items-center">
          <p>NOMBRE</p>
          <input v-model="name" type="text" placeholder="Nombre"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0">
          <p>APELLIDOS</p>

          <input v-model="surname" type="text" placeholder="Apellidos"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0">
          <p>EMAIL</p>
          <input v-model="email" type="email" placeholder="Email"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0">
          <p>CONTRASEÑA</p>
          <input v-model="password" type="password" placeholder="Contraseña"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0">

          <p>DIRECCIÓN</p>
          <input v-model="address" type="text" placeholder="Dirección"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0">

          <p>PAÍS</p>
          <input v-model="country" type="text" placeholder="País"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0">
          <p>CÓDIGO POSTAL</p>
          <input v-model="zipCode" type="text" placeholder="Código postal"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0">
          <p>TELÉFONO</p>
          <input v-model="phoneNumber" type="text" placeholder="Nº teléfono"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0">
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

export default {
  name: "UserData",
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