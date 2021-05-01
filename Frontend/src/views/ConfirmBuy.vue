<template>
  <div class="min-w-screen m-12 flex">
    <div class="inline items-center justify-center w-5/12">
      <ConfirmOrderProduct :product="product"/>
    </div>
    <div class="inline w-7/12 my-auto">
      <div v-show="step1">
        <OrderShipmentAddress v-on:addressUpdated="addressUpdated"/>
        <button @click="step1=false; step2 = true"
                class="bg-purple-500 text-xl p-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">

          SIGUIENTE
        </button>
      </div>

      <div v-show="step2">
        <OrderPaymentMethods v-on:cardClicked="cardClicked"/>
        <div class="space-x-4">
          <button @click="step1=true; step2=false"
                  class="bg-purple-500 text-xl p-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
            ATRÁS
          </button>
          <button @click="step2=false; step3 = true"
                  class="bg-purple-500 text-xl p-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">

            SIGUIENTE
          </button>
        </div>


      </div>
      <div v-show="step3">

        <div>
          <h1 class="text-4xl">RESUMEN DE LA COMPRA</h1>
          <div class="flex my-10 justify-center items-center border-2 rounded-lg shadow-xl border-purple-300  ">
            <div class=" w-1/3 my-5">
              <div>
                <h1 class="text-md sm:text-2xl">
                  PRECIO
                </h1>
                <h1 class="text-md sm:text-lg mt-5">
                  Precio: {{ price.toFixed(2) }}€
                </h1>
                <h1 class="text-md sm:text-lg mt-3">
                  Tasas de compra: {{ fees.toFixed(2) }}€
                </h1>
                <h1 class="text-md sm:text-lg mt-3">
                  Gastos de envío: {{ shipping.toFixed(2) }}€
                </h1>
                <h1 class="text-md sm:text-2xl mt-5">
                  TOTAL: {{ totalPrice.toFixed(2) }}€
                </h1>
              </div>
            </div>
            <div class=" w-1/3">
              <h1 class="text-md sm:text-2xl">
                DIRECCIÓN
              </h1>
              <h1 class="text-md sm:text-lg mt-5">
                Dirección: {{ addressInfo.address }}
              </h1>
              <h1 class="text-md sm:text-lg mt-5">
                Código Postal: {{ addressInfo.zipCode }}
              </h1>
              <h1 class="text-md sm:text-lg mt-5">
                País: {{ addressInfo.country }}
              </h1>

            </div>
            <div class="w-1/3">
              <h1 class="text-md sm:text-2xl">
                MÉTODO DE PAGO
              </h1>
              <h1 class="text-md sm:text-lg mt-5">
                Número: {{ modifiedNumber }}
              </h1>
              <h1 class="text-md sm:text-lg mt-5">
                Fecha caducidad: {{ card.expMonth }}/{{ card.expYear }}
              </h1>
            </div>
          </div>

        </div>
        <div class="space-x-4">
          <button @click="step2=true; step3=false"
                  class="bg-purple-500 text-xl p-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
            ATRÁS
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
      card: {
        idPayMethod: null,
        number: "",
        expMonth: "",
        expYear: ""
      },
      size: "",
      price: 340,
      fees: 0,
      shipping: 15,
      totalPrice: 0,

      addressInfo: {
        address: null,
        country: null,
        zipCode: null
      },
      step1: true,
      step2: false,
      step3: false
    }
  },
  computed: {
    modifiedNumber() {
      let pad = "************"
      return pad + this.card.number
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
                this.calcPriceBuy()
              }
            }
          })
          .catch(err => {
          })
    },
    confirmBuy() {
      const dat = {
        "idPayMethod": this.card.idPayMethod,
        "ref": this.ref,
        "size": this.size,
        "address": this.addressInfo.address,
        "country": this.addressInfo.country,
        "zipCode": this.addressInfo.zipCode,
      }
      console.log(dat)
      axios({url: 'http://localhost:8888/order/createBuy', data: dat, method: 'POST'})
          .then(resp => {
            alert("Venta creada correctamente")
          })
          .catch(err => {
            console.log(err.response)
          })
    },
    cardClicked(card) {
      this.card.idPayMethod = card.idPayMethod
      this.card.number = card.number
      this.card.expMonth = card.expMonth
      this.card.expYear = card.expYear
    },
    addressUpdated(addressInfo) {
      this.addressInfo = addressInfo
    },
    calcPriceBuy(){
      let url = "http://localhost:8888/order/getPriceBuy?price="+this.price
      axios({url: url, method: 'GET'})
          .then(resp => {
            this.fees = resp.data.fees
            this.shipping = resp.data.shipping
            this.totalPrice = resp.data.total
          })
          .catch(err => {
            console.log(err.response)
          })
    }
  }

}
</script>

<style scoped>

</style>