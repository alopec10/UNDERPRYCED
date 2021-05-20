<template>
  <div id="product" class="min-w-screen items-center mx-auto justify-center mt-12" style="font-family:'Quantico'">
    <h1 class="text-6xl my-4 font-semibold uppercase">
      {{ product.title }}
    </h1>
    <div class="inline-flex align-center my-10">
      <div id="Size"
           class="w-32 h-20 sm:w-48 sm:h-24 bg-purple-100 rounded-lg mr-2 flex justify-center items-center border-2 border-purple-700 hover:bg-purple-200 transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105 hover:shadow-lg"
           @click="showSizes=!showSizes; $refs.modalName.openModal()"
      >
        <h1 class="text-purple-900 text-md sm:text-2xl ">
          TALLA: {{ size }}
        </h1>
      </div>
      <div id="LastSale"
           class="w-32 h-20 sm:w-48 sm:h-24 bg-purple-300 rounded-lg ml-2 mr-2 flex justify-center items-center px-2 divide-x border-2 border-purple-700 transition duration-500 ease-in-out hover:bg-purple-350  transform hover:-translate-x hover:scale-105 hover:shadow-lg">

        <div class="block w-5/5">
          <h1 class="text-purple-900 text-lg sm:text-md ">
            ÚLTIMA COMPRA
          </h1>
          <h1 class="text-purple-900 text-md sm:text-2xl ">
            {{ lastSale }}
          </h1>
        </div>
      </div>
      <div>
        <div id="LowestAsk"
             class="block w-32 h-20 sm:w-56 sm:h-24 bg-purple-500 rounded-lg ml-2 mr-2 flex justify-center items-center px-2 divide-x cursor-pointer border-2 border-purple-700 transition duration-500 ease-in-out hover:bg-purple-600 transform hover:-translate-x hover:scale-105 hover:shadow-lg"
             @click="pushBuy">
          <div class="block w-3/5">
            <h1 class="text-white text-md sm:text-md ">
              MÍN. OFERTA
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
        <div @click="showAskTable" class="mt-3 block">
          <h1 class=" cursor-pointer">Ver todo</h1>
        </div>
      </div>
      <div>
        <div id="HighestBid"
             class="w-32 h-20 sm:w-56 sm:h-24 bg-purple-700 rounded-lg ml-2 mr-2 flex justify-center items-center px-2 divide-x cursor-pointer border-2 border-purple-900 transition duration-500 ease-in-out hover:bg-purple-800 transform hover:-translate-x hover:scale-105 hover:shadow-lg"
             @click="pushSell">
          <div class="block w-3/5">
            <h1 class="text-white text-md sm:text-md ">
              MÁX. PUJA
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
        <div @click="showBidTable" class="mt-3 block">
          <h1 class=" cursor-pointer w-auto">Ver todo </h1>
        </div>
      </div>
    </div>


    <div class="mx-auto flex justify-center w-1/2">
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

      <vue-tailwind-modal
          :showing="showAsks"
          @close="showAsks = false"
          :showClose="true"
          :backgroundClose="true"
      >
        <div>
          <div>
            <h1 class="text-4xl">ASKS</h1>
          </div>
          <div v-for="(item, index) in asks" :key=index>
            {{ item.price }} - {{ item.count }}
          </div>
        </div>

      </vue-tailwind-modal>
      <vue-tailwind-modal
          :showing="showBids"
          @close="showBids = false"
          :showClose="true"
          :backgroundClose="true"
      >
        <div>
          <div>
            <h1 class="text-4xl">BIDS</h1>
          </div>
          <div v-for="(item, index) in bids" :key=index>
            {{ item.price }} - {{ item.count }}
          </div>
        </div>

      </vue-tailwind-modal>

      <modal ref="modalName">
        <template v-slot:header>
          <h1 class="text-4xl font-semibold text-center my-2" style="font-family:'Quantico'">SELECCIONA TU TALLA</h1>
        </template>

        <template v-slot:body>
          <div class="grid grid-cols-3 gap-4 mx-10">
            <div v-for="(item, index) in product.productDetails" :value="item.size" @click="size=item.size"
                 class="border-2 border-purple-500 rounded-md text-3xl py-4 cursor-pointer h-24 my-auto items-center hover:bg-purple-100" v-bind:class="{'bg-purple-300':item.size === size}">
              {{ item.size }}
              <div
                  class="text-lg" v-if="item.lowestAsk !== null">
                {{ item.lowestAsk }}€
              </div>
              <div
                  class="text-lg" v-else>
                - €
              </div>
            </div>
          </div>


        </template>

        <template v-slot:footer>
          <div class="my-3">
            <button type="button" style="font-family:'Quantico'"
                    class="bg-purple-500 text-xl p-3 px-5 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105"
                    @click="$refs.modalName.closeModal();">CERRAR
            </button>
          </div>
        </template>
      </modal>

    </div>
  </div>

</template>

<script>

const axios = require("axios");
import VueTailwindModal from 'vue-tailwind-modal'
import Modal from "@/components/Modal";

export default {
  name: "Product",
  components: {
    VueTailwindModal,
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
      asks: [],
      bids: [],

      size: "",
      showSizes: false,
      showAsks: false,
      showBids: false
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
    pushBuy() {
      this.$router.push({
        name: "Comprar",
        params: {
          ref: this.product.ref,
          size: this.size
        }
      });
    },
    pushSell() {
      this.$router.push({
        name: "Vender",
        params: {
          ref: this.product.ref,
          size: this.size
        }
      });
    },
    showAskTable() {
      const dat =
          {
            "ref": this.product.ref,
            "size": this.size
          }
      axios({url: 'http://localhost:8888/ask/getAll', data: dat, method: 'POST'})
          .then(resp => {
            this.asks = resp.data
            this.showAsks = true
          })
          .catch(err => {
          })
    },
    showBidTable() {
      const dat =
          {
            "ref": this.product.ref,
            "size": this.size
          }
      axios({url: 'http://localhost:8888/bid/getAll', data: dat, method: 'POST'})
          .then(resp => {
            this.bids = resp.data
            this.showBids = true
          })
          .catch(err => {
          })
    }

  }
}
</script>

<style scoped>
</style>