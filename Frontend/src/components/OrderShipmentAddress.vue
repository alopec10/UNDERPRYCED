<template>
  <div class="my-10">
    <h1 class="text-5xl">DATOS DEL USUARIO</h1>
    <div class="inline-block mt-5">
      <form class="mt-10">
        <div class="grid gap-6 grid-cols-3 my-6 mx-auto items-center">
          <p>DIRECCIÓN</p>
          <input v-model="info.address" type="text" placeholder="Dirección"
                 class="col-span-2 mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0">
          <p>PAÍS</p>
          <input v-model="info.country" type="text" placeholder="País"
                 class="col-span-2 mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0">
          <p>CÓDIGO POSTAL</p>
          <input v-model="info.zipCode" type="text" placeholder="Código postal"
                 class="col-span-2 mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0">
          </div>
      </form>
    </div>
  </div>
</template>

<script>
const axios = require("axios");

export default {
  name: "OrderShipmentAddress",
  data() {
    return {
      info: {
        address: '',
        country: '',
        zipCode: '',
      }
    }
  },
  mounted() {
    this.getData()
  },
  methods: {
    getData() {
      axios({url: 'http://localhost:8888/user/data', method: 'GET'})
          .then(resp => {
            let userData = resp.data
            this.info.address = userData.address
            this.info.country = userData.country
            this.info.zipCode = userData.zipCode
          })
          .catch(err => {
            console.log(err)

          })
    }
  },
  watch: {
    info: {
      deep: true,
      handler(){
        this.$emit('addressUpdated', this.$data.info)
      }
    }
  }
}
</script>

<style scoped>
p {
  text-align: right
}
</style>