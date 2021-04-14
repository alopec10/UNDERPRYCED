<template>
  <form class="mt-10">
    <h1>Datos del usuario</h1>
    <div>
      <input v-model="name" type="text" placeholder="Nombre" class="mt-1 block w-full border-none bg-gray-100 h-11 rounded-xl shadow-lg hover:bg-blue-100 focus:bg-blue-100 focus:ring-0">
    </div>
    <div class="mt-7">
      <input v-model="surname" type="text" placeholder="Apellidos" class="mt-1 block w-full border-none bg-gray-100 h-11 rounded-xl shadow-lg hover:bg-blue-100 focus:bg-blue-100 focus:ring-0">
    </div>
    <div class="mt-7">
      <input v-model="email" type="email" placeholder="Email" class="mt-1 block w-full border-none bg-gray-100 h-11 rounded-xl shadow-lg hover:bg-blue-100 focus:bg-blue-100 focus:ring-0">
    </div>
    <div class="mt-7">
      <input v-model="password" type="password" placeholder="Contraseña" class="mt-1 block w-full border-none bg-gray-100 h-11 rounded-xl shadow-lg hover:bg-blue-100 focus:bg-blue-100 focus:ring-0">
    </div>
    <div class="mt-7">
      <input v-model="address" type="text" placeholder="Dirección" class="mt-1 block w-full border-none bg-gray-100 h-11 rounded-xl shadow-lg hover:bg-blue-100 focus:bg-blue-100 focus:ring-0">
    </div>
    <div class="mt-7">
      <input v-model="country" type="text" placeholder="País" class="mt-1 block w-full border-none bg-gray-100 h-11 rounded-xl shadow-lg hover:bg-blue-100 focus:bg-blue-100 focus:ring-0">
    </div>
    <div class="mt-7">
      <input v-model="zipCode" type="text" placeholder="Código postal" class="mt-1 block w-full border-none bg-gray-100 h-11 rounded-xl shadow-lg hover:bg-blue-100 focus:bg-blue-100 focus:ring-0">
    </div>
    <div class="mt-7">
      <input v-model="phoneNumber" type="text" placeholder="Nº teléfono" class="mt-1 block w-full border-none bg-gray-100 h-11 rounded-xl shadow-lg hover:bg-blue-100 focus:bg-blue-100 focus:ring-0">
    </div>
    <div class="mt-7">
      <button @click="update" type="button" class="bg-blue-500 w-full py-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
        Guardar cambios
      </button>
    </div>
  </form>
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
  mounted(){
    this.getData()
  },
  methods: {
    getData(){
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
    update(){
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

</style>