<template>
<div class="min-w-screen items-center mx-auto justify-center mt-12">
  <div class="mx-auto flex justify-center w-5/12">
    <img :src="product.url" class="my-6 mx-10 p-5 border-2 rounded-lg shadow-xl border-purple-300 "/>
  </div>
</div>
</template>

<script>
const axios = require("axios");

export default {
  name: "Sell",
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