<template>
  <div id="product" class="min-w-screen items-center mx-auto justify-center">
    <h1 class="text-6xl my-4">
      {{ title }}
    </h1>
    <div id="LastSale">
      Last Sale: 430$
    </div>
    <div id="LowestAsk">
      Lowest Ask: 444$
    </div>
    <div id="HighestBid">
      Highest Bid: 420$
    </div>
    <img :src="url" class="mx-auto my-6 p-5 border-2 rounded-lg shadow-xl border-purple-300"/>

    <div>
      <h1>Retail price {{ retail_price }}</h1>
      <h1>Release date {{ rel_date }}</h1>
    </div>
<button @click="update">a</button>
  </div>

</template>

<script>
const axios = require("axios");

export default {
  name: "Product",
  props: {
    title: {
      type: String,
      required: true
    },
    url: {
      type: String,
      required: true
    },
    retail_price: {
      type: String,
      required: true
    },
    rel_date: {
      type: String,
      required: true
    },
  },
  methods: {
    update() {
      const dat = {
        "specifications": [
          {
            "key": "brand",
            "value": "Adidas",
            "operator": "=="
          }
        ]
      }

      axios({url: 'http://localhost:8888/product/specification', data: dat, method: 'POST'})
          .then(resp => {
            console.log(resp)
            resolve(resp)
          })
          .catch(err => {
            commit('auth_error')
            reject(err)
          })
    }
  }
}
</script>

<style scoped>

</style>