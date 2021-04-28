<template>
  <div class="min-w-screen m-12 flex">
    <div class="inline items-center justify-center w-7/12">
      <ConfirmOrderProduct :product="product"/>
    </div>
    <div class="inline w-5/12 my-auto">
      <div v-show="step1">
        <OrderShipmentAddress v-on:addressUpdated="addressUpdated"/>
        <button @click="step1=!step1"
                class="bg-purple-500 text-xl p-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">

          SIGUIENTE
        </button>
      </div>

      <div v-show="!step1">
        <OrderPaymentMethods v-on:cardClicked="cardClicked"/>
        <div class="space-x-4">
          <button @click="step1=!step1"
                  class="bg-purple-500 text-xl p-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
            ATRAS
          </button>
          <button @click="confirmBuy"
                  class="bg-purple-500 text-xl p-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
            CONFIRMAR
          </button>
        </div>

      </div>
    </div>


  </div>

</template>

<script>
const axios = require("axios");
import ConfirmOrderProduct from "@/components/ConfirmOrderProduct";
import OrderPaymentMethods from "@/components/OrderPaymentMethods";
import OrderShipmentAddress from "@/components/OrderShipmentAddress";

export default {
  name: "ConfirmBuy",
  components: {
    ConfirmOrderProduct,
    OrderPaymentMethods,
    OrderShipmentAddress
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
      addressInfo: {
        address: null,
        country: null,
        zipCode: null
      },
      step1: true
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
                this.price = pd.lowestAsk
                this.fees = 0.1 * this.price
                this.totalPrice = this.price + this.fees + this.shipping
              }
            }
          })
          .catch(err => {
          })
    },
    confirmBuy() {
      //MODIFY WHEN IMPLEMENTED
      const dat = {
        "idPayMethod": this.idPayMethod,
        "ref": this.ref,
        "size": this.size,
        "address": this.addressInfo.address,
        "country": this.addressInfo.country,
        "zipCode": this.addressInfo.zipCode,
      }
      axios({url: 'http://localhost:8888/order/createBuy', data: dat, method: 'POST'})
          .then(resp => {
            alert("Venta creada correctamente")
          })
          .catch(err => {
            console.log(err)
          })
    },
    cardClicked(id) {
      this.idPayMethod = id
    },
    addressUpdated(addressInfo) {
      this.addressInfo = addressInfo
    }
  }

}
</script>

<style scoped>

</style>