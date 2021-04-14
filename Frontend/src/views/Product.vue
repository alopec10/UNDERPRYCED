<template>
  <div id="product" class="min-w-screen items-center mx-auto justify-center">
    <h1 class="text-6xl my-4">
      {{ product.title }}
    </h1>
    <div class="inline-flex align-center my-6">
      <div id="Size"
           class="w-32 h-20 sm:w-48 sm:h-24 bg-purple-100 rounded-lg mx-4 flex justify-center items-center">
        <h1 class="text-purple-900 text-md sm:text-2xl ">
          SIZE: {{ size }}
        </h1>
      </div>
      <div id="LastSale"
           class="w-32 h-20 sm:w-48 sm:h-24 bg-purple-300 rounded-lg mr-4 flex justify-center items-center">
        <h1 class="text-white text-md sm:text-2xl ">
          LAST SALE: {{ lastSale }}
        </h1>
      </div>
      <div id="LowestAsk"
           class="w-32 h-20 sm:w-48 sm:h-24 bg-purple-700 rounded-lg m-auto flex justify-center items-center">
        <h1 class="text-white text-md sm:text-2xl m-auto">
          LOWEST ASK: {{ lowestAsk }}
        </h1>
      </div>
      <div id="HighestBid"
           class="w-32 h-20 sm:w-48 sm:h-24 bg-purple-500 rounded-lg mx-4 flex justify-center items-center">
        <h1 class="text-white text-md sm:text-2xl ">
          HIGHEST BID: {{ highestBid }}
        </h1>
      </div>
    </div>

    <img :src="product.url" class="mx-auto my-6 p-5 border-2 rounded-lg shadow-xl border-purple-300"/>

    <div class=" my-10 space-y-4 text-xl">
      <div>
        <div class="text-purple-700 inline-block">
          <i class="fas fa-caret-right"></i>
        </div>
        <h1 class=" text-xl mx-4 text-black inline-block">
          RETAIL PRICE {{ product.retail_price }}
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
          RELEASE DATE {{ product.date }}
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
  data () {
    return {
      product: {
        title: "",
        url: "",
        retailPrice: null,
        date: "",
        ref: "",
        brand: "",
        colorway: "",
        name: "",
        model: "",
        year: "",
        gender: "",
        categoryType: "",
        productDetails: []
      },

      size: "40",

    }
  },
  mounted() {
    this.getProduct()
  },
  computed: {
    lastSale(){
      for(let pd of this.product.productDetails) {
        if (pd.size == this.size && pd.lastSale != null) {
          return pd.lastSale + " €"
        }
      }
      return "--"
    },
    lowestAsk(){
      for(let pd of this.product.productDetails) {
        if (pd.size == this.size) {
          return pd.lowestAsk + " €"
        }
      }
      return "--"
    },
    highestBid(){
      for(let pd of this.product.productDetails) {
        if (pd.size == this.size) {
          return pd.highestBid + " €"
        }

      }
      return "--"
    }
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
          })
          .catch(err => {
          })
    },
    getProduct() {
      const dat = {
        "specifications": [
          {
            "key": "ref",
            "value": this.$route.params.ref,
            "operator": "=="
          }
        ]
      }
      axios({url: 'http://localhost:8888/product/specification', data: dat, method: 'POST'})
          .then(resp => {
            this.product = resp.data[0]
          })
          .catch(err => {
          })
    },

  }
}
</script>

<style scoped>

</style>