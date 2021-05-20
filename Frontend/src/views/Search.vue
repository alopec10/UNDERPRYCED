<template>
  <div class="mt-10">
    <div>
      <h1 class="text-5xl mb-10">
        BÚSQUEDA
      </h1>
    </div>
    <div class="flex space-x-3 sm:space-x-6 mx-10 justify-center">
      <div class="w-52">
        <multiselect v-model="search_category" :options="categories" :multiple="false" :close-on-select="true"
                     :clear-on-select="false" :preserve-search="true" placeholder="Categoría" label="name"
                     track-by="name" :preselect-first="false" :searchable="false"
                     selectLabel="" deselectLabel=""
                     selectedLabel="Seleccionada">
          <template slot="selection" slot-scope="{ values, search, isOpen }"><span class="multiselect__single"
                                                                                   v-if="values.length &amp;&amp; !isOpen">{{
              values.length
            }} opciones seleccionadas</span></template>
          <span slot="noOptions">Error! No hay categorías</span>
        </multiselect>
      </div>

      <div class="w-52">
        <multiselect v-model="search_brand" :options="brands" :multiple="false" :close-on-select="true"
                     :clear-on-select="false" :preserve-search="true" placeholder="Marca" label="name"
                     track-by="name" :preselect-first="false" :searchable="false"
                     selectLabel="" deselectLabel=""
                     selectedLabel="Seleccionada">
          <template slot="selection" slot-scope="{ values, search, isOpen }"><span class="multiselect__single"
                                                                                   v-if="values.length &amp;&amp; !isOpen">{{
              values.length
            }} opciones seleccionadas</span></template>
          <span slot="noOptions">Error! No hay marcas</span>
        </multiselect>

      </div>

      <div class="w-52">
        <multiselect v-model="search_year" :options="years" :multiple="false" :close-on-select="true"
                     :clear-on-select="false" :preserve-search="true" placeholder="Año de salida"
                     label="name"
                     track-by="name" :preselect-first="false" :searchable="false"
                     selectLabel="" deselectLabel=""
                     selectedLabel="Seleccionada">
          <template slot="selection" slot-scope="{ values, search, isOpen }"><span class="multiselect__single"
                                                                                   v-if="values.length &amp;&amp; !isOpen">{{
              values.length
            }} opciones seleccionadas</span></template>
          <span slot="noOptions">Error! No hay años</span>
        </multiselect>
      </div>


      <div class="w-52">
        <multiselect v-model="search_size" :options="sizesDivided" :multiple="false" :close-on-select="true"
                     :clear-on-select="false" :preserve-search="true" placeholder="Talla" label="name"
                     track-by="name" :preselect-first="false" :searchable="false"
                     selectLabel="" deselectLabel=""
                     selectedLabel="Seleccionada">
          <template slot="selection" slot-scope="{ values, search, isOpen }"><span class="multiselect__single"
                                                                                   v-if="values.length &amp;&amp; !isOpen">{{
              values.length
            }} opciones seleccionadas</span></template>
          <span slot="noOptions">Error! No hay tallas</span>
        </multiselect>
      </div>
    </div>
    <div class="flex mx-10 mt-5 justify-center space-x-3 sm:space-x-6">
      <div class="">
        <input type="number" v-model="minPrice" placeholder="Precio mínimo" :disabled="sizeSelected" min="0"
               class="mt-1 w-32 md:w-52 block text-center border-2 border-purple-500 h-11 rounded-md shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0 focus:outline-none"/>

      </div>
      <div class="">
        <input type="number" v-model="maxPrice" placeholder="Precio máximo" :disabled="sizeSelected" min="0"
               class="mt-1 w-32 md:w-52 block text-center border-2 border-purple-500 h-11 rounded-md shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0 focus:outline-none"/>

      </div>
      <div class="">
        <button @click="getFiltered"
                type="button" style="font-family:'Quantico'"
                class="bg-purple-500 w-32 md:w-52 h-11 mt-1 text-lg md:text-xl rounded-md text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out transform hover:-translate-x hover:scale-105">
          Aplicar filtros
        </button>
      </div>

    </div>


    <div
        class="grid gap-6 grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-4 2xl:grid-cols-5 my-20 mx-10"
    >
      <SmallCard v-for="(item, index) in items" :key="index"
                 :title="item.title"
                 :url="item.url"
                 :price="item.retailPrice"
                 :refer="item.ref"
      ></SmallCard>
    </div>
  </div>


</template>

<script>
import SmallCard from '@/components/SmallCard.vue'
import Multiselect from 'vue-multiselect'

const axios = require("axios");

export default {
  name: "Search",
  components: {
    SmallCard,
    Multiselect
  },
  data() {
    return {
      items: [],
      sneakerSizes: [{name: '40'}, {name: '40.5'}, {name: '41'}, {name: '42'}, {name: '42.5'}, {name: '43'}, {name: '44'}, {name: '44.5'}, {name: '45'}],
      clothingSizes: [{name: 'XS'}, {name: 'S'}, {name: 'M'}, {name: 'L'}, {name: 'XL'}, {name: 'XXL'}],
      sizes: [],
      brands: [],
      categories: [],
      years: [],
      search_name: "",
      search_brand: null,
      search_category: null,
      search_size: null,
      search_year: null,
      minPrice: null,
      maxPrice: null

    }
  },
  watch: {
    'search_category': function (newVal, oldVal) {
      this.search_size = null
    }
  },
  computed: {
    sizesDivided() {
      if (this.search_category === null || this.search_category === undefined || this.search_category.name === "") {
        return this.sizes
      } else if (this.search_category.name === 'Zapatillas') {
        return this.sneakerSizes
      } else {
        return this.clothingSizes
      }
    },
    sizeSelected() {
      if (this.search_size !== null && this.search_size.name !== "" && this.search_size !== undefined) {
        return false
      }
      return true
    }
  },
  created() {
    console.log(this.$route.params.title)
    if (this.$route.params.title) {
      this.search_name = this.$route.params.title
      this.getByName()
    }
    this.getFiltered()
    this.getAllBrands()
    this.getAllCategories()
    this.getAllSizes()
    this.getAllYears()
  },
  methods: {
    getAll() {
      const refs = {
        "specifications": [{
          "value": "",
          "key": "ref",
          "operator": "!="
        }]
      }
      axios({url: 'http://localhost:8888/product/specification', data: refs, method: 'POST'})
          .then(resp => {
            this.items = []
            this.items = resp.data
          })
          .catch(err => {

          })
    },
    getAllBrands() {
      axios({url: 'http://localhost:8888/product/getBrands', method: 'GET'})
          .then(resp => {
            this.brands = []
            for (let item of resp.data) {
              this.brands.push({name: item})
            }

          })
          .catch(err => {

          })
    },
    getAllCategories() {
      axios({url: 'http://localhost:8888/product/getCategories', method: 'GET'})
          .then(resp => {
            this.categories = []
            for (let item of resp.data) {
              this.categories.push({name: item})
            }
          })
          .catch(err => {

          })
    },
    getAllSizes() {
      axios({url: 'http://localhost:8888/product/getSizes', method: 'GET'})
          .then(resp => {
            this.sizes = []
            for (let item of resp.data) {
              this.sizes.push({name: item})
            }
          })
          .catch(err => {

          })
    },
    getAllYears() {
      axios({url: 'http://localhost:8888/product/getYears', method: 'GET'})
          .then(resp => {
            this.years = []
            for (let item of resp.data) {
              this.years.push({name: item})
            }
          })
          .catch(err => {

          })
    },
    getByName() {
      const refs = {
        "specifications": [{
          "value": this.search_name,
          "key": "title",
          "operator": "match"
        }]
      }
      axios({url: 'http://localhost:8888/product/specification', data: refs, method: 'POST'})
          .then(resp => {
            this.items = []
            this.items = resp.data
          })
          .catch(err => {

          })
    },
    getFiltered() {
      var filters = []
      if (this.search_name !== null && this.search_name !== undefined && this.search_name !== "") {
        filters.push({
          "value": this.search_name,
          "key": "title",
          "operator": "match"
        })
      }
      if (this.search_brand !== null && this.search_brand !== undefined && this.search_brand.name !== "") {
        filters.push({
          "value": this.search_brand.name,
          "key": "brand",
          "operator": "match"
        })
      }
      if (this.search_year !== null && this.search_year !== undefined && this.search_year.name !== "") {
        filters.push({
          "value": this.search_year.name,
          "key": "year",
          "operator": "match"
        })
      }
      if (filters.length === 0 && (this.search_category === null || this.search_category.name === "" || this.search_category === undefined)) {
        this.getAll()
      } else {
        if (filters.length === 0) {
          filters.push({
            "value": "",
            "key": "ref",
            "operator": "!="
          })
        }
        let filtersJSON = {"specifications": filters}

        axios({url: 'http://localhost:8888/product/specification', data: filtersJSON, method: 'POST'})
            .then(resp => {
              this.items = resp.data
              if (this.search_category !== null && this.search_category.name !== "" && this.search_category !== undefined) {
                this.filterByCategory()

              }
              if ((!this.isBlank(this.minPrice) || !this.isBlank(this.maxPrice)) && this.search_size) {
                this.filterByPrice()
              } else {
                this.minPrice = null
                this.maxPrice = null
              }
            })
            .catch(err => {

            })
      }

    },
    filterByPrice() {
      if (!this.isBlank(this.minPrice) && !this.isBlank(this.maxPrice) && parseInt(this.minPrice, 10) > parseInt(this.maxPrice, 10)) {
        alert("Price error")
        this.minPrice = null
        this.maxPrice = null
      } else {
        if (!this.isBlank(this.minPrice) && !this.isBlank(this.maxPrice)) {
          let aux_items = []
          for (let item of this.items) {
            let la = this.getPricesSize(item)
            if (la !== null && la >= parseInt(this.minPrice, 10) && la <= parseInt(this.maxPrice, 10)) {
              aux_items.push(item)
            }
          }
          this.items = aux_items
        } else if (!this.isBlank(this.minPrice) && this.isBlank(this.maxPrice)) {
          let aux_items = []
          for (let item of this.items) {
            let la = this.getPricesSize(item)
            if (la !== null && la >= parseInt(this.minPrice, 10)) {
              aux_items.push(item)
            }
          }
          this.items = aux_items
        } else if (this.isBlank(this.minPrice) && !this.isBlank(this.maxPrice)) {
          let aux_items = []
          for (let item of this.items) {
            let la = this.getPricesSize(item)
            if (la !== null && la <= parseInt(this.maxPrice, 10)) {
              aux_items.push(item)
            }
          }
          this.items = aux_items
        }
      }
    },
    getPricesSize(item) {
      for (let pd of item.productDetails) {
        if (pd.size === this.search_size.name) {
          return pd.lowestAsk
        }
      }
      return null
    },
    filterByCategory() {
      let aux_items = []
      for (let item of this.items) {
        if (item.categoryType === this.search_category.name) {
          aux_items.push(item)
        }
      }
      this.items = aux_items
    },
    isBlank(str) {
      return (!str || /^\s*$/.test(str));
    }


  }
}
</script>

<style scoped>
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
</style>

<style>
.multiselect__placeholder {
  display: inline-block !important;
  margin-bottom: 0px !important;
  padding-top: 0px !important;
  text-align: center !important;
}

.multiselect.invalid .multiselect__tags {
  border: 1px solid #f86c6b !important;
}

.multiselect {
  border: 2px solid #8B5CF6 !important;
  border-radius: 5px !important;
}

.multiselect__input {
  text-align: center !important;
}

.multiselect__option--highlight {
  background: #8B5CF6 !important;
}

.multiselect__option--highlight:after {
  background: #8B5CF6 !important;
}

.multiselect__tags {
  padding: 5px !important;
  min-height: 10px;
}

.multiselect__placeholder {
  margin-left: 10px;
  margin-top: 2px;
  text-align: center !important;
}

.multiselect__tag {
  background: #f0f0f0 !important;
  border: 1px solid #8B5CF6 !important;
  color: black !important;
  margin-bottom: 0px !important;
  margin-right: 0px !important;
}

.multiselect__tag-icon:after {
  color: #8B5CF6 !important;
}

.multiselect__tag {
  color: #8B5CF6 !important;
}

.multiselect__single {
  text-align: center !important;
}

.multiselect__tag-icon:focus,
.multiselect__tag-icon:hover {
  background: #8B5CF6 !important;
}

.multiselect__tag-icon:focus:after,
.multiselect__tag-icon:hover:after {
  color: red !important;
}

</style>

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
