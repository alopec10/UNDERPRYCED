<script src="../store/index.js"></script>
<template>
  <div class="home" style="font-family:'Quantico'">

    <div class="min-w-screen h-96 items-center justify-center px-5 py-5"
         :style="{ backgroundImage: 'url(' + require('@/assets/banner3.png') + ')' }">
      <div class="relative mt-32">
        <input type="text" id="" placeholder="Buscar..." v-model="searchString" v-on:keyup.enter="search"
               class="h-12 uppercase text-xl text-black text-center placeholder-black w-96 bg-bgpurple bg-opacity-90 border border-gray-900 hover:border-black focus:outline-none focus:border-black transition-colors">

        <button
            @click="search"
            class="w-7 h-7 text-center text-xl relative right-8 text-black focus:outline-none hover:text-purple-900 transition-colors">
          <i class="fas fa-search"></i>
        </button>
      </div>

    </div>
    <div class="xl:mx-32">
      <h1 class="text-5xl font-bold mt-20">MARCAS</h1>
      <div class="grid gap-6 grid-cols-1 sm:grid-cols-1 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-4 mx-10 mb-8 mt-8">
        <BrandCard brand="YEEZY"
                   bgUrl="background-image:url('https://blog.klekt.com/wp-content/uploads/2020/04/adidas-Yeezy-Boost-350-V2-Zebra-On-Foot-800x450.jpg')"
                   logoUrl="https://1000marcas.net/wp-content/uploads/2020/02/yeezy-logo-transparent-png-1.png"
                   class="cursor-pointer"

        ></BrandCard>
        <BrandCard brand="JORDAN"
                   bgUrl="background-image:url('https://www.43einhalb.com/media/232762/w/1000/h/600/n/nike-air-jordan-1-retro-high-og-gs-court-purple-575441-500-mood-2.jpg')"
                   logoUrl="https://i.pinimg.com/originals/01/39/93/0139937c2f641ab61fd020844ccfd459.png"
        ></BrandCard>
        <BrandCard brand="NIKE"
                   bgUrl="background-image:url('https://sneakers-magazine.com/wp-content/uploads/2017/12/sean-wotherspoon-x-nike-air-max-1-97-collectors-dream-aj4219-400-on-feet-facebook.jpg')"
                   logoUrl="https://logodownload.org/wp-content/uploads/2014/04/nike-logo.png"
        ></BrandCard>
        <BrandCard brand="OFF-WHITE"
                   bgUrl="background-image:url('https://www.wag1mag.com/wp-content/uploads/nike-preview-sept-2018-serena-virgil-7-wag1mag.jpg')"
                   logoUrl="https://iconape.com/wp-content/files/fw/85449/svg/off-white-logo.svg"
                   class=""
        ></BrandCard>
        <BrandCard brand="ADIDAS"
                   bgUrl="background-image:url('https://i.pinimg.com/originals/1d/85/fc/1d85fca70f67b23ce1fbd8579770f9b0.jpg')"
                   logoUrl="http://assets.stickpng.com/images/580b57fcd9996e24bc43c4f2.png"
                   class="hidden"
        ></BrandCard>
      </div>
      <hr class="mt-14">
      <div>
        <h1 class="text-5xl font-bold mt-14">MÁS POPULARES</h1>
        <div
            class="grid gap-6 grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-4 2xl:grid-cols-5 my-6 mx-10">

          <SmallCard v-for="(item, index) in mostPopular" :key="index"
                     :title="item.title"
                     :url="item.url"
                     :price="item.retailPrice"
                     :refer="item.ref"
          ></SmallCard>

        </div>
      </div>

      <hr class="mt-14">
      <div class=mb-20>
        <h1 class="text-5xl font-bold mt-14">RECOMENDADOS</h1>
        <div
            class="grid gap-6 grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-4 2xl:grid-cols-5 my-6 mx-10"
        >
          <SmallCard v-for="(item, index) in recommended" :key="index"
                     :title="item.title"
                     :url="item.url"
                     :price="item.retailPrice"
                     :refer="item.ref"

          ></SmallCard>

        </div>
      </div>

    </div>
  </div>
</template>


<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import NavBar from '@/components/NavBar.vue'
import BrandCard from '@/components/BrandCard.vue'
import SmallCard from '@/components/SmallCard.vue'
import TestCard from '@/components/TestCard.vue'

const axios = require("axios");

export default {
  name: 'Home',
  components: {
    HelloWorld,
    NavBar,
    BrandCard,
    SmallCard,
    TestCard
  },
  created() {
    this.getMostPopular()
    this.getRecommended()
  },
  data() {
    return {
      mostPopular: [],
      recommended: [],
      searchString: ""
    }
  },
  methods: {
    getMostPopular() {
      const refs = {
        "refs": [
          "8659ggdd727-dafb-322-21221-9909",
          "8654d027-dafb-676d-b309-c8e12bf1f15b",
          "8654d027-dafb-322-21221-9909",
          "1f43b12h31j-3f121-51gs2-21f12-mj98",
          "8654d027-dafb-676d-2121-9909"
        ]
      }
      this.mostPopular = []
      axios({url: 'http://localhost:8888/product/ref', data: refs, method: 'POST'})
          .then(resp => {
            console.log(resp)
            this.mostPopular = resp.data

          })
          .catch(err => {

          })
    },
    getRecommended() {
      const refs = {
        "refs": [
          "865fa321d727-dafb-51512-21221-99fs1",
          "8654d027-dafb-3321-2121-9909",
          "1f431f3g3k31j-3f121-51gs2-21f12-mj98",
          "fa1a8868-9dd0-4b1b-a52f-dc248143d797",
          "8334d027-dafb-426d-b209-b8e07bf1b15b"
        ]
      }
      this.recommended = []
      axios({url: 'http://localhost:8888/product/ref', data: refs, method: 'POST'})
          .then(resp => {
            console.log(resp)
            this.recommended = resp.data
          })
          .catch(err => {

          })
    },
    search() {
      if (this.isBlank(this.searchString)) {
        this.$router.push({
          name: "Buscar",
        });
      } else {
        this.$router.push({
          name: "Buscar",
          params: {
            title: this.searchString,
          }
        });
      }
    },

    isBlank(str) {
      return (!str || /^\s*$/.test(str));
    },

  }
}
</script>
