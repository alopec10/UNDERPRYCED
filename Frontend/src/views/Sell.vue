<template>
  <div class="min-w-screen m-12 flex">
    <div class="inline items-center justify-center w-7/12">
      <h1 class="text-5xl my-4 cursor-pointer uppercase" style="font-family:'Quantico'" @click="pushToProduct">
        {{ product.title }}
      </h1>
      <div class="mx-auto flex justify-center w-11/12">
        <img :src="product.url" class="my-6 mx-10 p-5 border-2 rounded-lg shadow-xl border-purple-300 cursor-pointer" @click="pushToProduct"/>
      </div>
    </div>
    <div class="inline w-5/12">
      <div class="inline-flex align-center space-x-4">
        <div
            class="w-32 h-20 sm:w-56 sm:h-24 bg-purple-100 rounded-lg flex justify-center items-center cursor-pointer"
            @click="selectedSell = true"
            v-bind:class="{'bg-purple-500': selectedSell}"
            v-if="price !== null">
          <h1 class="text-white text-md sm:text-2xl p-5" style="font-family:'Quantico'"
              v-bind:class="{'text-gray-700': !selectedSell}">
            VENDER AHORA
          </h1>
        </div>
        <div
            class="w-32 h-20 sm:w-56 sm:h-24 bg-purple-100 rounded-lg flex justify-center items-center px-2 cursor-pointer"
            @click="selectedSell = false"
            v-bind:class="{'bg-purple-500': !selectedSell}">
          <h1 class="text-white text-md sm:text-2xl " style="font-family:'Quantico'"
              v-bind:class="{'text-gray-700': selectedSell}">
            OFERTAR
          </h1>
        </div>
      </div>
      <div v-if="selectedSell">
        <div class="mt-10 align-center">
          <h1 class="text-md sm:text-2xl " style="font-family:'Quantico'">
            TALLA: {{ this.size }}
          </h1>
          <h1 class="text-md sm:text-5xl mt-5">
            {{ price }}€
          </h1>
          <h1 class="text-md sm:text-lg ">
            (Puja más alta actualmente)
          </h1>
          <h1 class="text-md sm:text-lg mt-5">
            Tasas de venta: {{ fees.toFixed(2) }}€
          </h1>
          <h1 class="text-md sm:text-lg mt-5">
            Gastos de envío: {{ shipping.toFixed(2) }}€
          </h1>
          <h1 class="text-md sm:text-2xl mt-5">
            TOTAL: {{ totalPrice.toFixed(2) }}€
          </h1>
          <div
              @click="confirmSell"
              class="w-32 h-20 sm:w-48 sm:h-20 bg-purple-500 rounded-lg justify-center items-center px-2 flex mx-auto mt-10 cursor-pointer">
            <h1 class="text-white text-md sm:text-2xl " style="font-family:'Quantico'">
              CONTINUAR
            </h1>
          </div>
        </div>
      </div>
      <div v-else>
        <div class="mt-10 align-center">
          <h1 class="text-md sm:text-2xl " style="font-family:'Quantico'">
            TALLA: {{ this.size }}
          </h1>

          <div class="mt-7">
            <input v-model="customPrice" type="text" placeholder="Precio" id="p"
                   class="inline-block text-center mx-auto w-40 h-16 block border-2 border-purple-500 p-3 text-5xl h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:outline-none focus:bg-purple-100"/>
            <div class="inline-block ml-2 text-5xl">€</div>
          </div>
          <h1 v-if="currentAsk == customPrice" class="text-md sm:text-lg mt-3">
            (Tu oferta actual)
          </h1>
          <h1 v-else class="text-md sm:text-lg mt-3">
            (Tu nueva oferta)
          </h1>
          <div v-if="parseInt(customPrice) > 0 && parseInt(customPrice) !=null">
            <h1 class="text-md sm:text-lg mt-5">
              Tasas de venta: {{ customFees.toFixed(2) }}€
            </h1>
            <h1 class="text-md sm:text-lg mt-5">
              Gastos de envío: {{ customShipping.toFixed(2) }}€
            </h1>
            <h1 class="text-md sm:text-2xl mt-5">
              TOTAL: {{ totalCustomPrice.toFixed(2) }}€
            </h1>
            <div
                class="w-32 h-20 sm:w-48 sm:h-20 bg-purple-500 rounded-lg justify-center items-center px-2 flex mx-auto mt-10 cursor-pointer"
                @click="placeAsk">
              <h1 class="text-white text-md sm:text-2xl " style="font-family:'Quantico'">
                CONTINUAR
              </h1>
            </div>
          </div>

        </div>
      </div>

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

      size: "",
      selectedSell: true,
      price: 0,
      fees: 0,
      shipping: 15,
      totalPrice: 0,

      customPrice: 0,
      customFees: 0,
      customShipping: 15,
      totalCustomPrice: 0,
      currentAsk: 0
    }
  },
  watch: {
    customPrice: function () {
      this.calcCustomPriceSell()
      }
  },
  mounted() {
    this.getProduct()
    this.size = this.$route.params.size
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
                if (this.price != null) {
                  this.calcPriceSell()
                }
                else{
                  this.selectedSell = false
                }
              }
            }
            this.getPriceIfExists()

          })
          .catch(err => {
          })
    },
    placeAsk() {
      const dat =
          {
            "ref": this.product.ref,
            "size": this.size,
            "price": this.customPrice
          }
      if (this.customPrice <= this.price && this.price != null) {
        this.selectedSell = true
        this.customPrice = 0
      }
      else{
        axios({url: 'http://localhost:8888/ask/make', data: dat, method: 'POST'})
            .then(resp => {
              this.$notify({
                group: 'err',
                title: 'Oferta creada',
                text: 'Oferta creada correctamente',
                type: 'success',
                duration: 5000,
              })
            })
            .catch(err => {
              let error_msg = err.response.data
              if(error_msg === "It's not possible to make an ask if you don't have at least one payment method added"){
                this.$notify({
                  group: 'err',
                  title: 'Método pago incorrecto',
                  text: 'No se puede crear una oferta si no se ha añadido ningún método de pago previamente',
                  type: 'error',
                  duration: 5000,
                })
              }
              else if(error_msg === "It's not possible to make an ask lower than the highest bid"){
                this.$notify({
                  group: 'err',
                  title: 'Valor incorrecto',
                  text: 'No se puede crear una oferta con valor inferior a la puja más alta',
                  type: 'error',
                  duration: 5000,
                })
              }
              else if(error_msg === "Price must be a positive integer number"){
                this.$notify({
                  group: 'err',
                  title: 'Valor incorrecto',
                  text: 'El precio de la oferta debe ser positivo',
                  type: 'error',
                  duration: 5000,
                })
              }
              else{
                console.log(err.response.data)
              }
            })
      }



    },
    confirmSell() {
      this.$router.push({
        name: "ConfirmarVenta",
        params: {
          ref: this.product.ref,
          size: this.size
        }
      });
    },
    calcPriceSell() {
      let url = "http://localhost:8888/order/getPriceSell?price=" + this.price
      axios({url: url, method: 'GET'})
          .then(resp => {
            this.fees = resp.data.fees
            this.shipping = resp.data.shipping
            this.totalPrice = resp.data.total
          })
          .catch(err => {
            console.log(err.response)
          })
    },
    calcCustomPriceSell() {
      let url = "http://localhost:8888/order/getPriceSell?price=" + this.customPrice
      axios({url: url, method: 'GET'})
          .then(resp => {
            this.customFees = resp.data.fees
            this.customShipping = resp.data.shipping
            this.totalCustomPrice = resp.data.total
          })
          .catch(err => {
            console.log(err.response)
          })
    },
    getPriceIfExists() {
      const dat =
          {
            "ref": this.product.ref,
            "size": this.size,
          }
      axios({url: "http://localhost:8888/ask/getPriceIfExists", data: dat, method: 'POST'})
          .then(resp => {
            this.currentAsk = resp.data.price
            this.customPrice = resp.data.price

          })
          .catch(err => {
            console.log(err.response)
          })
    },
    pushToProduct() {
      this.$router.push({
        name: "Producto",
        params: {
          ref: this.product.ref,
        }
      })
    }

  }
}
</script>

<style scoped>

</style>