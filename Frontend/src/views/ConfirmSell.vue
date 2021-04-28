<template>
  <div class="min-w-screen m-12 flex">
    <OrderPaymentMethods v-on:cardClicked="cardClicked"/>
    <ConfirmOrderProduct :product="product"/>
    <button @click="confirmSell">
      CONFIRMAR
    </button>
  </div>

</template>

<script>
const axios = require("axios");
import ConfirmOrderProduct from "@/components/ConfirmOrderProduct";
import OrderPaymentMethods from "@/components/OrderPaymentMethods";

export default {
  name: "ConfirmSell",
  components: {
    ConfirmOrderProduct,
    OrderPaymentMethods,
  },
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
      size: "",
      price: 340,
      fees: 0,
      shipping: 15,
      totalPrice: 0,
      idPayMethod: null,
    }
  },
  mounted() {
    this.getProduct()
    this.size = this.$route.params.size
    this.ref = this.$route.params.ref
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
            for (let pd of this.product.productDetails) {
              if (this.size == pd.size) {
                this.price = pd.highestBid
                this.fees = 0.1 * this.price
                this.totalPrice = this.price + this.fees + this.shipping
              }
            }
          })
          .catch(err => {
          })
    },
    confirmSell() {
      const dat = {
        "idPayMethod": this.idPayMethod,
        "ref": this.ref,
        "size": this.size,
      }
      axios({url: 'http://localhost:8888/order/createSell', data: dat, method: 'POST'})
          .then(resp => {
            alert("Venta creada correctamente")
          })
          .catch(err => {
            console.log(err)
          })
    },
    cardClicked(id){
      this.idPayMethod = id
    },
  }

}
</script>

<style scoped>

</style>