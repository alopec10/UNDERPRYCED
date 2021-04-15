<template>
  <div id="product" class="min-w-screen items-center mx-auto justify-center mt-12">
    <h1 class="text-6xl my-4">
      {{ product.title }}
    </h1>
    <div class="inline-flex align-center my-6">
      <div id="Size"
           class="w-32 h-20 sm:w-48 sm:h-24 bg-purple-100 rounded-lg mx-4 flex justify-center items-center"
           @click="showSizes=!showSizes"
      >
        <h1 class="text-purple-900 text-md sm:text-2xl ">
          SIZE: {{ size }}
        </h1>
      </div>
      <div id="LastSale"
           class="w-32 h-20 sm:w-48 sm:h-24 bg-purple-300 rounded-lg mr-4 flex justify-center items-center px-2 divide-x">

        <div class="block w-3/5">
          <h1 class="text-white text-md sm:text-md ">
            LAST SALE
          </h1>
          <h1 class="text-white text-md sm:text-2xl ">
            {{ lastSale }}
          </h1>
        </div>
      </div>
      <div id="LowestAsk"
           class="w-32 h-20 sm:w-56 sm:h-24 bg-purple-500 rounded-lg mr-4 flex justify-center items-center px-2 divide-x cursor-pointer"
           @click="pushBuy">
        <div class="block w-3/5">
          <h1 class="text-white text-md sm:text-md ">
            LOWEST ASK
          </h1>
          <h1 class="text-white text-md sm:text-3xl ">
            {{ lowestAsk }}
          </h1>
        </div>
        <div class="block w-2/5">
          <h1 class="text-white text-md sm:text-sm ">
            COMPRAR<br>o pujar
          </h1>
        </div>
      </div>
      <div id="HighestBid"
           class="w-32 h-20 sm:w-56 sm:h-24 bg-purple-700 rounded-lg mr-4 flex justify-center items-center px-2 divide-x cursor-pointer"
           @click="pushSell">
        <div class="block w-3/5">
          <h1 class="text-white text-md sm:text-md ">
            HIGHEST BID
          </h1>
          <h1 class="text-white text-md sm:text-3xl ">
            {{ highestBid }}
          </h1>
        </div>
        <div class="block w-2/5">
          <h1 class="text-white text-md sm:text-sm ">
            VENDER<br>u ofertar
          </h1>
        </div>
      </div>
    </div>

    <select v-model="size">
      <option v-for="(item, index) in product.productDetails" :value="item.size">{{ item.size }}</option>
    </select>

    <div class="mx-auto flex justify-center w-5/12">
      <img :src="product.url" class="my-6 mx-10 p-5 border-2 rounded-lg shadow-xl border-purple-300 "/>
    </div>


    <div class=" my-10 space-y-4 text-xl">
      <div>
        <div class="text-purple-700 inline-block">
          <i class="fas fa-caret-right"></i>
        </div>
        <h1 class=" text-xl mx-4 text-black inline-block">
          RETAIL PRICE {{ product.retailPrice }} €
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
  </div>

</template>

<script>

const axios = require("axios");

export default {
  name: "Product",
  data() {
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
      showSizes: false,
    }
  },
  mounted() {
    this.getProduct()
  },
  computed: {
    lastSale() {
      for (let pd of this.product.productDetails) {
        if (pd.size == this.size && pd.lastSale != null) {
          return pd.lastSale + " €"
        }
      }
      return "--"
    },
    lowestAsk() {
      for (let pd of this.product.productDetails) {
        if (pd.size == this.size && pd.lowestAsk != null) {
          return pd.lowestAsk + " €"
        }
      }
      return "--"
    },
    highestBid() {
      for (let pd of this.product.productDetails) {
        if (pd.size == this.size && pd.highestBid != null) {
          return pd.highestBid + " €"
        }

      }
      return "--"
    }
  },
  methods: {
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
            this.size = this.product.productDetails[0].size
          })
          .catch(err => {
          })
    },
    pushBuy(){
      this.$router.push({
        name: "Comprar",
        params:{
          ref: this.product.ref,
          size: this.size
        }
      });
    },
    pushSell(){
      this.$router.push({
        name: "Vender",
        params:{
          ref: this.product.ref,
          size: this.size
        }
      });
    }

  }
}
</script>

<style scoped>

</style>