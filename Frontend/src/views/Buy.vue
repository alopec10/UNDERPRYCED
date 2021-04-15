<template>
  <div class="min-w-screen m-12 flex">
    <div class="inline items-center justify-center w-7/12">
      <h1 class="text-5xl my-4">
        {{ product.title }}
      </h1>
      <div class="mx-auto flex justify-center w-11/12">
        <img :src="product.url" class="my-6 mx-10 p-5 border-2 rounded-lg shadow-xl border-purple-300 "/>
      </div>
    </div>
    <div class="inline w-5/12">
      <div class="inline-flex align-center">
        <div
            class="w-32 h-20 sm:w-56 sm:h-24 bg-purple-500 rounded-lg flex justify-center items-center cursor-pointer"
            @click="">
          <h1 class="text-white text-md sm:text-2xl p-5">
            COMPRAR AHORA
          </h1>
        </div>
        <div
            class="w-32 h-20 sm:w-56 sm:h-24 bg-purple-300 rounded-lg flex ml-4 justify-center items-center px-2 cursor-pointer"
            @click="">
          <h1 class="text-white text-md sm:text-2xl ">
            PUJAR
          </h1>
        </div>
      </div>
      <div>
        <div class="mt-10 align-center">
          <h1 class="text-md sm:text-2xl ">
            TALLA: {{this.size}}
          </h1>
          <h1 class="text-md sm:text-5xl mt-5">
            {{ lowestAsk }}€
          </h1>
          <h1 class="text-md sm:text-lg ">
            (Oferta más baja actualmente)
          </h1>
          <h1 class="text-md sm:text-lg mt-5">
            Tasas de compra: {{fees.toFixed(2)}}€
          </h1>
          <h1 class="text-md sm:text-lg mt-5">
            Gastos de envío: {{shipping.toFixed(2)}}€
          </h1>
          <h1 class="text-md sm:text-2xl mt-5">
            TOTAL: {{totalPrice.toFixed(2)}}€
          </h1>
          <div class="w-32 h-20 sm:w-48 sm:h-20 bg-purple-500 rounded-lg justify-center items-center px-2 flex mx-auto mt-10 cursor-pointer">
            <h1 class="text-white text-md sm:text-2xl ">
              CONTINUAR
            </h1>
          </div>
        </div>
      </div>

    </div>

  </div>
</template>

<script>
const axios = require("axios");

export default {
  name: "Buy",
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
      lowestAsk: 340,
      fees: null,
      shipping: 15,
      totalPrice: null
    }
  },
  mounted() {
    this.getProduct()
    this.fees = 0.1 * this.lowestAsk
    this.totalPrice = this.lowestAsk + this.fees + this.shipping

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
  }
}
</script>

<style scoped>

</style>