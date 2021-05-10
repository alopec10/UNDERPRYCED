<template>
  <div class="my-10">
    <h1 class="text-5xl">Añadir producto</h1>
    <div class="inline-block w-1/2 mt-5">
      <form class="mt-10">
        <div class="grid gap-6 grid-cols-2 my-6 mx-auto items-center">
          <input v-model="newProduct.ref" type="text" placeholder="Referencia"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0 focus:outline-none">
          <input v-model="newProduct.brand" type="text" placeholder="Marca"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0 focus:outline-none">
          <input v-model="newProduct.colorway" type="text" placeholder="Color"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0 focus:outline-none">
          <input v-model="newProduct.name" type="text" placeholder="Nombre"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0 focus:outline-none">
          <input v-model="newProduct.model" type="text" placeholder="Modelo"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0 focus:outline-none">
          <input v-model="newProduct.title" type="text" placeholder="Título"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0 focus:outline-none">
          <input v-model="newProduct.year" type="text" placeholder="Año de lanzamiento"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0 focus:outline-none">
          <select v-model="newProduct.categoryType"
                  class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0 focus:outline-none">
            <option value="" disabled hidden class="text-center">Categoría</option>
            <option v-for="(item, index) in categories" :value="item" :key="index" class="text-center">{{ item }}</option>
          </select>
          <select v-model="newProduct.gender"
                  class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0 focus:outline-none">
            <option value="" disabled hidden class="text-center">Género</option>
            <option v-for="(item, index) in genders" :value="item" :key="index" class="text-center">{{ item }}</option>
          </select>
          <input v-model="newProduct.retailPrice" type="text" placeholder="Precio de salida ($)"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0 focus:outline-none">

          <datepicker :value="newProduct.releaseDate" name="Fecha de lanzamiento" format="dd-MM-yyyy"
                      input-class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0 focus:outline-none"></datepicker>
          <input v-model="newProduct.sizes" type="text" placeholder="Tallas"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0 focus:outline-none">
        </div>
      </form>
    </div>
  </div>
</template>

<script>
const axios = require("axios");
import Datepicker from 'vuejs-datepicker';

export default {
  name: "AddProduct",
  components: {
    Datepicker
  },
  data() {
    return {
      newProduct: {
        ref: '',
        brand: '',
        colorway: '',
        name: '',
        model: '',
        title: '',
        year: '',
        categoryType: '',
        gender: '',
        retailPrice: undefined,
        releaseDate: undefined,
        sizes: []
      },
      categories: [],
      genders: ['m','f','gs','ps']
    }
  },
  created() {
    this.getCategories()
  },
  methods: {
    getCategories(){
      this.categories = []
      axios({url: 'http://localhost:8888/product/getCategories', method: 'GET'})
          .then(resp => {
            this.categories = resp.data
          })
          .catch(err => {
            console.log(err.response)
          })
    }
  }
}
</script>

<style scoped>

select { text-align-last:center; }
</style>