<template>
  <div class="m-10">

    <div>
      <h1 class="text-2xl">ACTIVE ASKS</h1>
      <div
          class="grid gap-6 grid-cols-1 xl:grid-cols-2 2xl:grid-cols-3 my-10 mx-2 sm:mx-20">
        <OperationCard v-for="(op, index) in asks" :key="op.ref"
                       :op="op"
                       typeOp="ask"
                       v-on:delete="getAsks">
        </OperationCard>

      </div>
    </div>

    <div class="mt-20">
      <h1 class="text-2xl">ACTIVE BIDS</h1>
      <div
          class="grid gap-6 grid-cols-1 xl:grid-cols-2 2xl:grid-cols-3 my-10 mx-2 sm:mx-20">
        <OperationCard v-for="(op, index) in bids" :key="op.ref"
                       :op="op"
                       typeOp="bid"
                       v-on:delete="getBids">
        </OperationCard>
      </div>
    </div>

    <div class="mt-20">
      <h1 class="text-2xl">SELLS</h1>
      <div
          class="grid gap-6 grid-cols-1 xl:grid-cols-2 2xl:grid-cols-3 my-10 mx-2 sm:mx-20">
        <OperationCard v-for="(op, index) in sells" :key="op.ref"
                       :op="op"
                       typeOp="sell"
                       v-on:delete="getSells">
        </OperationCard>
      </div>
    </div>

    <div class="mt-20">
      <h1 class="text-2xl">PURCHASES</h1>
      <div
          class="grid gap-6 grid-cols-1 xl:grid-cols-2 2xl:grid-cols-3 my-10 mx-2 sm:mx-20">
        <OperationCard v-for="(op, index) in sells" :key="op.ref"
                       :op="op"
                       typeOp="purchase"
                       v-on:delete="getPurchases">
        </OperationCard>
      </div>
    </div>
  </div>
</template>

<script>
import OperationCard from "../components/OperationCard";

const axios = require("axios");

export default {
  name: "History",
  components: {OperationCard},
  data() {
    return {
      asks: [],
      bids: [],
      sells: [],
      purchases: []
    }
  },
  created() {
    this.getAsks()
    this.getBids()
  },
  methods: {
    getAsks() {
      axios({url: 'http://localhost:8888/ask/getAllByUser', method: 'GET'})
          .then(resp => {
            this.asks = resp.data
          })
          .catch(err => {
          })
    },
    getBids() {
      axios({url: 'http://localhost:8888/bid/getAllByUser', method: 'GET'})
          .then(resp => {
            this.bids = resp.data
          })
          .catch(err => {
          })
    },
    getSells() {
      axios({url: 'http://localhost:8888/order/getSells', method: 'GET'})
          .then(resp => {
            this.sells = resp.data
          })
          .catch(err => {
          })
    },
    getPurchases() {
      axios({url: 'http://localhost:8888/order/getPurchases', method: 'GET'})
          .then(resp => {
            this.purchases = resp.data
          })
          .catch(err => {
          })
    },
  }
}
</script>

<style scoped>

</style>