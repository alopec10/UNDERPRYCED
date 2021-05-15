<template>
  <div class="m-10">
    <div class="justify-center">
      <h1 class="text-black text-md sm:text-2xl p-5">
        PENDIENTES DE APROBACIÓN
      </h1>
    </div>
    <div class="align-center">

      <div
          class="grid grid-cols-1 my-5 mx-auto sm:mx-20 justify-center gap-6">
        <AdminOrderCard v-for="(op, index) in orders" :key="index"
                        :op="op"
                        v-on:delete="getPendingOrders"
                        class="mx-auto">
        </AdminOrderCard>
      </div>


    </div>
  </div>
</template>

<script>
const axios = require("axios");
import AdminOrderCard from "../components/AdminOrderCard";

export default {
  name: "CheckOrder",
  components: {
    AdminOrderCard,
  },
  data() {
    return {
      orders: [],
    }
  },
  created() {
    this.getPendingOrders()
  },
  methods: {
    getPendingOrders() {
      axios({url: 'http://localhost:8888/order/getPending', method: 'GET'})
          .then(resp => {
            this.orders = []
            this.orders = resp.data
          })
          .catch(err => {
            console.log(err.response)
          })
    },

  }
}
</script>

<style scoped>

</style>