<template>
  <div class="m-10">

    <div class="inline-flex align-center">
      <div
          class="w-32 h-20 sm:w-56 sm:h-24 bg-purple-100 rounded-lg flex justify-center items-center cursor-pointer"
          @click="selectedCurrent = true"
          v-bind:class="{'bg-purple-500': selectedCurrent}">
        <h1 class="text-white text-md sm:text-2xl p-5"
            v-bind:class="{'text-gray-700': !selectedCurrent}">
          BIDS / ASKS
        </h1>
      </div>
      <div
          class="w-32 h-20 sm:w-56 sm:h-24 bg-purple-100 rounded-lg flex ml-4 justify-center items-center px-2 cursor-pointer"
          @click="selectedCurrent = false"
          v-bind:class="{'bg-purple-500': !selectedCurrent}">
        <h1 class="text-white text-md sm:text-2xl "
            v-bind:class="{'text-gray-700': selectedCurrent}">
          SELLS / PURCHASES
        </h1>
      </div>
    </div>
    <div v-if="selectedCurrent">
      <div class="mt-16">
        <h1 class="text-2xl">ACTIVE ASKS</h1>
        <div
            class="grid gap-6 grid-cols-1 xl:grid-cols-2 2xl:grid-cols-3 my-10 mx-2 sm:mx-20">
          <OperationCard v-for="(op, index) in asks" :key="index"
                         :op="op"
                         typeOp="ask"
                         v-on:delete="getAsks">
          </OperationCard>

        </div>
      </div>

      <div class="mt-20">
        <h1 class="text-2xl">ACTIVE BIDS</h1>
        <div
            class="grid gap-6 grid-cols-1 xl:grid-cols-2 2xl:grid-cols-2 my-10 mx-2 sm:mx-20">
          <OperationCard v-for="(op, index) in bids" :key="index"
                         :op="op"
                         typeOp="bid"
                         v-on:delete="getBids">
          </OperationCard>
        </div>
      </div>
    </div>

    <div v-else>
      <div class="mt-16">
        <h1 class="text-2xl">SELLS</h1>
        <div
            class="grid gap-6 grid-cols-1 xl:grid-cols-2 2xl:grid-cols-2 my-10 mx-2 sm:mx-20">
          <OrderCard v-for="(op, index) in sells" :key="index"
                     :op="op"
                     v-on:delete="getSells">
          </OrderCard>
        </div>
      </div>

      <div class="mt-20">
        <h1 class="text-2xl">PURCHASES</h1>
        <div
            class="grid gap-6 grid-cols-1 xl:grid-cols-2 2xl:grid-cols-3 my-10 mx-2 sm:mx-20">
          <OrderCard v-for="(op, index) in purchases" :key="index"
                     :op="op"
                     v-on:delete="getPurchases">
          </OrderCard>
        </div>
      </div>
    </div>


  </div>
</template>

<script>
import OperationCard from "../components/OperationCard";
import OrderCard from "../components/OrderCard";

const axios = require("axios");

export default {
  name: "History",
  components: {
    OperationCard,
    OrderCard
  },
  data() {
    return {
      asks: [],
      bids: [],
      sells: [],
      purchases: [],
      selectedCurrent: true
    }
  },
  created() {
    this.getAsks()
    this.getBids()
    this.getPurchases()
    this.getSells()
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