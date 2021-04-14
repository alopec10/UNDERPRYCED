<template>
  <div id="product" class="min-w-screen items-center mx-auto justify-center">
    <h1 class="text-6xl my-4">
      {{ title }}
    </h1>
    <div class="inline-flex align-center my-6">
      <div id="LastSale"
           class="w-32 h-20 sm:w-48 sm:h-24 bg-purple-300 rounded-lg mx-4 flex justify-center items-center">
        <h1 class="text-white text-md sm:text-2xl ">
          LAST SALE: 430$
        </h1>
      </div>
      <div id="LowestAsk"
           class="w-32 h-20 sm:w-48 sm:h-24 bg-purple-700 rounded-lg m-auto flex justify-center items-center">
        <h1 class="text-white text-md sm:text-2xl m-auto">
          LOWEST ASK: 444$
        </h1>
      </div>
      <div id="HighestBid"
           class="w-32 h-20 sm:w-48 sm:h-24 bg-purple-500 rounded-lg mx-4 flex justify-center items-center">
        <h1 class="text-white text-md sm:text-2xl ">
          HIGHEST BID: 420$
        </h1>
      </div>
    </div>

    <img :src="url" class="mx-auto my-6 p-5 border-2 rounded-lg shadow-xl border-purple-300"/>

    <div class=" my-10 space-y-4 text-xl">
      <div>
        <div class="text-purple-700 inline-block">
          <i class="fas fa-caret-right"></i>
        </div>
        <h1 class=" text-xl mx-4 text-black inline-block">
          RETAIL PRICE {{ retail_price }}
        </h1>
        <div class="text-purple-700 inline-block">
          <i class="fas fa-caret-left"></i>
        </div>
      </div>
      <div>
        <div class="text-purple-700 inline-block">
          <i class="fas fa-caret-right"></i>
        </div>
        <h1 class="text-xl mx-4 text-black inline-block">
          RELEASE DATE {{ rel_date }}
        </h1>
        <div class="text-purple-700 inline-block">
          <i class="fas fa-caret-left"></i>
        </div>
      </div>
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