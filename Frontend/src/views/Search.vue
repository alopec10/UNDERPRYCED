<template>
  <div>
    <multiselect v-model="search_category" :options="categories" :multiple="false" :close-on-select="true"
                 :clear-on-select="false" :preserve-search="true" placeholder="Selecciona la categoría" label="name"
                 track-by="name" :preselect-first="false" :searchable="false"
                 selectLabel="Pulsa enter para seleccionar" deselectLabel="Pulsa enter para deseleccionar"
                 selectedLabel="Seleccionada">
      <template slot="selection" slot-scope="{ values, search, isOpen }"><span class="multiselect__single"
                                                                               v-if="values.length &amp;&amp; !isOpen">{{
          values.length
        }} opciones seleccionadas</span></template>
      <span slot="noOptions">Error! No hay categorías</span>
    </multiselect>

    <multiselect v-model="search_brand" :options="brands" :multiple="false" :close-on-select="true"
                 :clear-on-select="false" :preserve-search="true" placeholder="Selecciona la marca" label="name"
                 track-by="name" :preselect-first="false" :searchable="false"
                 selectLabel="Pulsa enter para seleccionar" deselectLabel="Pulsa enter para deseleccionar"
                 selectedLabel="Seleccionada">
      <template slot="selection" slot-scope="{ values, search, isOpen }"><span class="multiselect__single"
                                                                               v-if="values.length &amp;&amp; !isOpen">{{
          values.length
        }} opciones seleccionadas</span></template>
      <span slot="noOptions">Error! No hay marcas</span>
    </multiselect>

    <multiselect v-model="search_year" :options="years" :multiple="false" :close-on-select="true"
                 :clear-on-select="false" :preserve-search="true" placeholder="Selecciona el año de salida" label="name"
                 track-by="name" :preselect-first="false" :searchable="false"
                 selectLabel="Pulsa enter para seleccionar" deselectLabel="Pulsa enter para deseleccionar"
                 selectedLabel="Seleccionada">
      <template slot="selection" slot-scope="{ values, search, isOpen }"><span class="multiselect__single"
                                                                               v-if="values.length &amp;&amp; !isOpen">{{
          values.length
        }} opciones seleccionadas</span></template>
      <span slot="noOptions">Error! No hay años</span>
    </multiselect>

    <multiselect v-model="search_size" :options="sizesDivided" :multiple="false" :close-on-select="true"
                 :clear-on-select="false" :preserve-search="true" placeholder="Selecciona su talla" label="name"
                 track-by="name" :preselect-first="false" :searchable="false"
                 selectLabel="Pulsa enter para seleccionar" deselectLabel="Pulsa enter para deseleccionar"
                 selectedLabel="Seleccionada">
      <template slot="selection" slot-scope="{ values, search, isOpen }"><span class="multiselect__single"
                                                                               v-if="values.length &amp;&amp; !isOpen">{{
          values.length
        }} opciones seleccionadas</span></template>
      <span slot="noOptions">Error! No hay tallas</span>
    </multiselect>

    <button class="button" @click="getFiltered">
      Filtrar
    </button>


    <div>
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

    }
  },
  watch: {
    'search_category': function (newVal, oldVal){
      this.search_size = null
    }
  },
  computed: {
    sizesDivided() {
      if (this.search_category === null || this.search_category === undefined || this.search_category.name === "") {
        return this.sizes
      }
      else if (this.search_category.name === 'Zapatillas'){
        return this.sneakerSizes
      }
      else {
        return this.clothingSizes
      }
    }
  },
  created() {
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
              this.items = []
              if (this.search_category === null || this.search_category.name === "" || this.search_category === undefined) {
                this.items = resp.data
              } else {
                for (let item of resp.data) {
                  if (item.categoryType === this.search_category.name) {
                    this.items.push(item)
                  }
                }
              }
            })
            .catch(err => {

            })
      }

    },

  }
}
</script>

<style scoped>

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
  margin-right: 5px !important;
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

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>