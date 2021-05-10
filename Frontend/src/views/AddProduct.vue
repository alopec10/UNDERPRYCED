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
          <multiselect v-model="categoryValue" :options="categories" :multiple="false" :close-on-select="true"
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
          <multiselect v-model="genderValue" :options="genders" :multiple="false" :close-on-select="true"
                       :clear-on-select="false" :preserve-search="true" placeholder="Selecciona el género" label="name"
                       track-by="name" :preselect-first="false" :searchable="false"
                       selectLabel="Pulsa enter para seleccionar" deselectLabel="Pulsa enter para deseleccionar"
                       selectedLabel="Seleccionada">
            <template slot="selection" slot-scope="{ values, search, isOpen }"><span class="multiselect__single"
                                                                                     v-if="values.length &amp;&amp; !isOpen">{{
                values.length
              }} opciones seleccionadas</span></template>
            <span slot="noOptions">Error! No hay géneros</span>
          </multiselect>
          <input v-model="newProduct.retailPrice" type="text" placeholder="Precio de salida ($)"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0 focus:outline-none">

          <datepicker :value="newProduct.releaseDate" name="releaseDatePicker" format="dd-MM-yyyy"
                      placeholder="Fecha de lanzamiento" :monday-first="true" :language="es"
                      :disabled-dates="disabledDates"
                      input-class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0 focus:outline-none"></datepicker>
          <input v-model="newProduct.sizes" type="text" placeholder="Tallas"
                 class="mt-1 block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0 focus:outline-none">
          <multiselect v-model="sizesValues" :options="sizes" :multiple="true" :close-on-select="false"
                       :clear-on-select="false" :preserve-search="true" placeholder="Selecciona las tallas" label="name"
                       track-by="name" :preselect-first="false" :searchable="false"
                       selectLabel="Pulsa enter para seleccionar" deselectLabel="Pulsa enter para deseleccionar"
                       selectedLabel="Seleccionada">
            <template slot="selection" slot-scope="{ values, search, isOpen }"><span class="multiselect__single"
                                                                                     v-if="values.length &amp;&amp; !isOpen">{{
                values.length
              }} tallas seleccionadas</span></template>
            <span slot="noOptions">Selecciona una categoría!</span>
          </multiselect>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
const axios = require("axios");
import Datepicker from 'vuejs-datepicker';
import Multiselect from 'vue-multiselect'
import {es} from 'vuejs-datepicker/dist/locale'

export default {
  name: "AddProduct",
  components: {
    Datepicker,
    Multiselect
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
      categoryValue: undefined,
      genders: [{name:'m'}, {name:'f'}, {name:'gs'}, {name:'ps'}],
      genderValue: undefined,
      es: es,
      disabledDates: {
        from: new Date()
      },
      sizesValues: [],
      sneakerSizes: [{name: '40'}, {name: '40.5'}, {name: '41'}, {name: '42'}, {name: '42.5'}, {name: '43'}, {name: '44'}, {name: '44.5'}, {name: '45'}],
      clothingSizes: [{name: 'XS'}, {name: 'S'}, {name: 'M'}, {name: 'L'}, {name: 'XL'}, {name: 'XXL'}]
    }
  },
  created() {
    this.getCategories()
  },
  watch: {
    'newProduct.categoryType': function (newVal, oldVal){
      this.sizesValues = []
    }
  },
  methods: {
    getCategories() {
      this.categories = []
      axios({url: 'http://localhost:8888/product/getCategories', method: 'GET'})
          .then(resp => {
            this.categories = []
            for(var item of resp.data){
              this.categories.push({name: item})
            }
          })
          .catch(err => {
            console.log(err.response)
          })
    },
    addProduct() {

    }
  },
  computed: {
    sizes() {
      if(this.categoryValue == undefined){
        return []
      }
      if (this.categoryValue.name === "Zapatillas") {
        return this.sneakerSizes
      } else if(this.categoryValue.name === "Ropa"){
        return this.clothingSizes
      }
      else{
        return []
      }
    }
  }
}
</script>

<style scoped>

select {
  text-align-last: center;
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