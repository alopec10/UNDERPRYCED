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
  <div v-if="status==='En camino' && order.type === 'Buy'">
    <button @click="confirmArrival" type="button"
            class="bg-purple-500 text-xl p-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
      CONFIRMAR RECEPCIÓN
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
      <h1>Estado</h1>
      <h1 class="text-xl">{{ status }}</h1>
    </div>

    <div class="">
      <h1>Referencia</h1>
      <h1 class="text-xl">{{ order.orderRef }}</h1>
    </div>

  </div>
  <div class="my-10">
    <h1>Direccion</h1>
    <h1 class="text-xl">{{ order.address }} {{order.zipCode}} {{order.country}}</h1>
  </div>
</div>
</template>

<script>
const axios = require("axios");
export default {
  name: "OrderInfo",
  data(){
    return {
      order: {
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
        type: 'Sell'
      }
    }
  },
  created() {
    this.getOrderInformation()
  },
  computed: {
    status() {
      switch(this.order.status) {
        case "ON_WAY":
          return "En camino"
        case "PENDING_APPROVAL":
          return "Esperando confirm."
        case "COMPLETED":
          return "Completado"
        default:
          return "Cancelado"
      }
    }
  },
  methods: {
    confirmArrival () {

      axios({url: 'http://localhost:8888/order/confirmDelivery?ref=' + this.order.orderRef, method: 'GET'})
          .then(resp => {
            console.log(resp.data)
            this.$router.go(0)
          })
          .catch(err => {
            console.log(err.response)
          })
    },
    getOrderInformation(){
      axios({url: 'http://localhost:8888/order/getOrderByRef?ref=' + this.$route.params.ref, method: 'GET'})
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