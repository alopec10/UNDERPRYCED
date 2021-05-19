<template>
  <div class="min-w-screen m-12 flex">
    <div class="inline items-center justify-center w-5/12">
      <ConfirmOrderProduct :product="product"/>
    </div>
    <div class="inline w-7/12 my-auto">
      <div v-show="step1">
        <OrderShipmentAddress v-on:addressUpdated="addressUpdated" v-on:addressNext="step1=false; step2 = true"/>
      </div>

      <div v-show="step2">
        <OrderPaymentMethods v-on:cardClicked="cardClicked" from-page="buy" v-on:paymentNext="step2=false; step3 = true" v-on:paymentPrev="step1=true; step2=false"/>
      </div>
      <div v-show="step3">

        <div>
          <h1 class="text-5xl font-semibold mb-10" style="font-family:'Quantico'">RESUMEN DE LA COMPRA</h1>
          <div class="flex my-10 justify-center items-center border-2 rounded-lg shadow-xl border-purple-300  ">
            <div class=" w-1/3 my-5">
              <div>
                <h1 class="text-md sm:text-3xl" style="font-family:'Quantico'">
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
              <h1 class="text-md sm:text-3xl" style="font-family:'Quantico'">
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
            <div class="w-1/3" >
              <h1 class="text-md sm:text-3xl" style="font-family:'Quantico'">
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
        <modal ref="modalName">
          <template v-slot:header>
            <h1>Modal title</h1>
          </template>

          <template v-slot:body>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nunc sed velit dignissim sodales ut eu sem integer vitae. Id aliquet lectus proin nibh nisl condimentum. Fringilla urna porttitor rhoncus dolor purus. Nam aliquam sem et tortor. Nisl vel pretium lectus quam id. Cras pulvinar mattis nunc sed. Quis ipsum suspendisse ultrices gravida dictum fusce ut placerat orci. Tristique magna sit amet purus. Fermentum et sollicitudin ac orci phasellus egestas tellus. Erat pellentesque adipiscing commodo elit at imperdiet dui accumsan. Felis eget nunc lobortis mattis aliquam faucibus. Tincidunt eget nullam non nisi est sit amet facilisis. Mi in nulla posuere sollicitudin aliquam ultrices sagittis orci. Vitae proin sagittis nisl rhoncus mattis rhoncus urna neque. Eget nunc scelerisque viverra mauris in aliquam sem fringilla ut. Nec nam aliquam sem et tortor consequat id. Commodo nulla facilisi nullam vehicula ipsum a. Elementum tempus egestas sed sed. Faucibus purus in massa tempor nec feugiat nisl pretium fusce.</p>
            <p>Arcu cursus vitae congue mauris rhoncus aenean. Tempor id eu nisl nunc mi. Pharetra diam sit amet nisl suscipit adipiscing bibendum. Ut faucibus pulvinar elementum integer enim. Odio facilisis mauris sit amet massa vitae tortor condimentum lacinia. Eu non diam phasellus vestibulum lorem sed risus. Porttitor rhoncus dolor purus non enim praesent. Sit amet mauris commodo quis imperdiet. Lobortis feugiat vivamus at augue eget. Nibh tellus molestie nunc non blandit. Tellus mauris a diam maecenas sed enim ut. Tortor aliquam nulla facilisi cras fermentum odio eu feugiat pretium. Mattis aliquam faucibus purus in massa.</p>
          </template>

          <template v-slot:footer>
            <div>
              <button @click="$refs.modalName.closeModal()">Cancel</button>
              <button @click="$refs.modalName.closeModal()">Save</button>
            </div>
          </template>
        </modal>
      </div>
    </div>


  </div>

</template>

<script>
const axios = require("axios");
import ConfirmOrderProduct from "@/components/ConfirmOrderProduct";
import OrderPaymentMethods from "@/components/OrderPaymentMethods";
import OrderShipmentAddress from "@/components/OrderShipmentAddress";
import Modal from "@/components/Modal";

export default {
  name: "ConfirmBuy",
  components: {
    ConfirmOrderProduct,
    OrderPaymentMethods,
    OrderShipmentAddress,
    Modal
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
            this.$refs.modalName.openModal()
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