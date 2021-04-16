<template>
  <div class="m-10">
    <div>
      <h1 class="text-2xl">ACTIVE ASKS</h1>
      <TinyProduct v-for="(op, index) in asks" :key="op.ref"
                   :op="op">
      </TinyProduct>
    </div>
    <div>
      <h1 class="text-2xl">ACTIVE BIDS</h1>
      <TinyProduct v-for="(op, index) in bids" :key="op.ref"
                   :op="op">
      </TinyProduct>
    </div>
  </div>
</template>

<script>
import TinyProduct from "../components/TinyProduct";

const axios = require("axios");

export default {
  name: "History",
  components: {TinyProduct},
  data() {
    return {
      asks: [],
      bids: [],
    }
  },
  created() {
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

  }
}
</script>

<style scoped>

</style>