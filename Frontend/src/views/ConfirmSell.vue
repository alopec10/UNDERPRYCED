<template>
  <div class="min-w-screen m-12 flex">
    <div class="inline items-center justify-center w-5/12">
      <ConfirmOrderProduct :product="product"/>
    </div>
    <div class="inline w-7/12 my-auto">
      <div v-show="step1">
        <OrderPaymentMethods name="COBRO" v-on:cardClicked="cardClicked" v-on:paymentNext="step1=false; step2 = true" from-page="sell"/>
      </div>
      <div v-show="step2">
        <h1 class="text-5xl font-semibold mb-10" style="font-family:'Quantico'">RESUMEN DE LA VENTA</h1>
        <div class="flex my-10 justify-center items-center border-2 rounded-lg shadow-xl border-purple-300  ">
          <div class=" w-1/2 my-5">
            <div>
              <h1 class="text-md sm:text-3xl" style="font-family:'Quantico'">
                PRECIO
              </h1>
              <h1 class="text-md sm:text-lg mt-5">
                Precio: {{ price.toFixed(2) }}€
              </h1>
              <h1 class="text-md sm:text-lg mt-3">
                Tasas de venta: {{ fees.toFixed(2) }}€
              </h1>
              <h1 class="text-md sm:text-lg mt-3">
                Gastos de envío: {{ shipping.toFixed(2) }}€
              </h1>
              <h1 class="text-md sm:text-2xl mt-5">
                TOTAL: {{ totalPrice.toFixed(2) }}€
              </h1>
            </div>
          </div>
          <div class=" w-1/2 my-5">

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
        <div class="space-x-4">
          <button @click="step1=true; step2=false" style="font-family:'Quantico'"
                  class="bg-purple-500 text-xl p-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
            ATRÁS
          </button>
          <button @click="confirmSell" style="font-family:'Quantico'"
                  class="bg-purple-500 text-xl p-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
            CONFIRMAR
          </button>
        </div>

      </div>

    </div>
    <modal ref="modalName">
      <template v-slot:header>
        <h1 class="text-4xl font-semibold mb-10" style="font-family:'Quantico'">VENTA CONFIRMADA</h1>
      </template>

      <template v-slot:body>
        <p>Tan pronto como recibamos el producto y podamos comprobar su veracidad procederemos a realizar el pago en su cuenta</p>
        <p class="mt-3">Si tiene cualquier duda o pregunta no dude en contactar con nosotros en la siguiente dirección de correo electrónico: customers@underpryced.com</p>
        <p class="mt-3">¡Gracias por confiar en nosotros!</p>
      </template>

      <template v-slot:footer>
        <div>
          <button type="button" style="font-family:'Quantico'"
                  class="bg-purple-500 text-xl p-3 px-5 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105"
                  @click="$refs.modalName.closeModal(); $router.push('/')">Cerrar</button>
        </div>
      </template>
    </modal>

  </div>

</template>

<script>
const axios = require("axios");
import ConfirmOrderProduct from "@/components/ConfirmOrderProduct";
import OrderPaymentMethods from "@/components/OrderPaymentMethods";
import Modal from "@/components/Modal";

export default {
  name: "ConfirmSell",
  components: {
    ConfirmOrderProduct,
    OrderPaymentMethods,
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
      step1: true,
      step2: false,
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
                this.price = pd.highestBid
                this.calcPriceSell()
              }
            }
          })
          .catch(err => {
          })
    },
    confirmSell() {
      const dat = {
        "idPayMethod": this.card.idPayMethod,
        "ref": this.ref,
        "size": this.size,
      }
      axios({url: 'http://localhost:8888/order/createSell', data: dat, method: 'POST'})
          .then(resp => {
            this.$refs.modalName.openModal()
            setTimeout(() => this.$router.push('/'), 15000);
          })
          .catch(err => {
            let error_msg = err.response.data
            if(error_msg === "It's not possible to sell a product to yourself"){
              this.$notify({
                group: 'err',
                title: 'Error en la venta',
                text: 'No puedes venderte un producto a ti mismo :(',
                type: 'error',
                duration: 5000,
              })
            }
            else{
              console.log(error_msg)
            }
          })
    },
    cardClicked(card) {
      this.card.idPayMethod = card.idPayMethod
      this.card.number = card.number
      this.card.expMonth = card.expMonth
      this.card.expYear = card.expYear
    },
    calcPriceSell(){
      let url = "http://localhost:8888/order/getPriceSell?price="+this.price
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