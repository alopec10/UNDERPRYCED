<template>
  <div>
    <div class="inline items-center justify-center w-7/12">
      <h1 class="text-5xl my-4">
        {{ order.title }}
      </h1>
      <div class="mx-auto flex justify-center w-11/12">
        <img :src="order.url" class="my-6 mx-10 p-5 border-2 rounded-lg shadow-xl border-purple-300 "/>
      </div>
    </div>
    <div>
      <button @click="approveOrder" type="button" style="font-family:'Quantico'"
              class="bg-purple-500 text-xl p-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
        ACEPTAR
      </button>
    </div>
    <div>
      <button @click="rejectOrder" type="button" style="font-family:'Quantico'"
              class="bg-purple-500 text-xl p-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
        DENEGAR
      </button>
    </div>
    <div class="flex mt-10  justify-center space-x-7">
      <div class="">
        <h1>Precio</h1>
        <h1 class="text-xl">{{ order.price }}€</h1>
      </div>
      <div class="">
        <h1>Talla</h1>
        <h1 class="text-xl">{{ order.size }}</h1>
      </div>
      <div class="">
        <h1>Fecha</h1>
        <h1 class="text-xl">{{ order.date }}</h1>
      </div>

      <div class="">
        <h1>Referencia</h1>
        <h1 class="text-xl">{{ order.orderRef }}</h1>
      </div>

    </div>
    <div class="mt-10">
      <div class="">
        <h1>Email vendedor</h1>
        <h1 class="text-xl">{{ order.emailSeller }}</h1>
      </div>
      <div class="">
        <h1>Email comprador</h1>
        <h1 class="text-xl">{{ order.emailBuyer }}</h1>
      </div>
    </div>
    <div class="my-10">
      <h1>Direccion destino</h1>
      <h1 class="text-xl">{{ order.address }} {{order.zipCode}} {{order.country}}</h1>
    </div>
  </div>
</template>

<script>
const axios = require("axios");
export default {
  name: "AdminOrderInfo",
  data(){
    return {
      order: {
        emailBuyer: '',
        emailSeller: '',
        title : '',
        url: '',
        price: '',
        size: '',
        date: '',
        status: '',
        orderRef: '',
        address: '',
        zipCode: '',
        country: '',

      }
    }
  },
  created() {
    this.getOrderInformation()
  },
  methods: {
    approveOrder () {
      const dat = {
        orderRef: this.$route.params.ref
      }
      axios({url: 'http://localhost:8888/order/approve', data:dat, method: 'POST'})
          .then(resp => {
            this.$router.push("/aprobarPedidos")
          })
          .catch(err => {
            console.log(err.response)
          })
    },
    rejectOrder () {
      const dat = {
        orderRef: this.$route.params.ref
      }
      axios({url: 'http://localhost:8888/order/reject', data:dat, method: 'POST'})
          .then(resp => {
            this.$router.push("/aprobarPedidos")
          })
          .catch(err => {
            console.log(err.response)
          })
    },
    getOrderInformation(){
      axios({url: 'http://localhost:8888/order/getPendingOrderByRef?ref=' + this.$route.params.ref, method: 'GET'})
          .then(resp => {
            this.order = resp.data
          })
          .catch(err => {
            console.log(err.response)
          })
    }
  }
}
</script>

<style scoped>

</style>