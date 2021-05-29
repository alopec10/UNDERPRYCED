<template>
  <div class="my-10">
    <h1 class="text-5xl font-semibold" style="font-family:'Quantico'">CREAR PRODUCTO</h1>
    <div class="inline-block w-1/2 mt-5">
      <form class="mt-10">
        <div class="grid gap-6 grid-cols-2 my-6 mx-auto items-center">
          <div class="floating-input mb-3 relative">
            <input v-model="newProduct.ref" type="text" id="ref"
                   class="border-2 border-purple-500 focus:outline-none rounded hover:bg-purple-100 focus:bg-purple-100 w-full p-3 h-16"
                   placeholder="Referencia" autocomplete="off"/>
            <label for="ref"
                   class="absolute top-0 left-0 px-3 py-5 h-full pointer-events-none transform origin-left transition-all duration-100 ease-in-out ">Referencia</label>
          </div>
          <div class="floating-input mb-3 relative">
            <input v-model="newProduct.brand" type="text" id="brand"
                   class="border-2 border-purple-500 focus:outline-none rounded hover:bg-purple-100 focus:bg-purple-100 w-full p-3 h-16"
                   placeholder="Marca" autocomplete="off"/>
            <label for="brand"
                   class="absolute top-0 left-0 px-3 py-5 h-full pointer-events-none transform origin-left transition-all duration-100 ease-in-out ">Marca</label>
          </div>
          <div class="floating-input mb-3 relative">
            <input v-model="newProduct.colorway" type="text" id="color"
                   class="border-2 border-purple-500 focus:outline-none rounded hover:bg-purple-100 focus:bg-purple-100 w-full p-3 h-16"
                   placeholder="Color" autocomplete="off"/>
            <label for="color"
                   class="absolute top-0 left-0 px-3 py-5 h-full pointer-events-none transform origin-left transition-all duration-100 ease-in-out ">Color</label>
          </div>
          <div class="floating-input mb-3 relative">
            <input v-model="newProduct.name" type="text" id="name"
                   class="border-2 border-purple-500 focus:outline-none rounded hover:bg-purple-100 focus:bg-purple-100 w-full p-3 h-16"
                   placeholder="Nombre" autocomplete="off"/>
            <label for="name"
                   class="absolute top-0 left-0 px-3 py-5 h-full pointer-events-none transform origin-left transition-all duration-100 ease-in-out ">Nombre</label>
          </div>
          <div class="floating-input mb-3 relative">
            <input v-model="newProduct.model" type="text" id="model"
                   class="border-2 border-purple-500 focus:outline-none rounded hover:bg-purple-100 focus:bg-purple-100 w-full p-3 h-16"
                   placeholder="Modelo" autocomplete="off"/>
            <label for="model"
                   class="absolute top-0 left-0 px-3 py-5 h-full pointer-events-none transform origin-left transition-all duration-100 ease-in-out ">Modelo</label>
          </div>
          <div class="floating-input mb-3 relative">
            <input v-model="newProduct.title" type="text" id="title"
                   class="border-2 border-purple-500 focus:outline-none rounded hover:bg-purple-100 focus:bg-purple-100 w-full p-3 h-16"
                   placeholder="Título" autocomplete="off"/>
            <label for="title"
                   class="absolute top-0 left-0 px-3 py-5 h-full pointer-events-none transform origin-left transition-all duration-100 ease-in-out ">Título</label>
          </div>
          <div class="floating-input mb-3 relative">
            <input v-model="newProduct.url" type="text" id="url"
                   class="border-2 border-purple-500 focus:outline-none rounded hover:bg-purple-100 focus:bg-purple-100 w-full p-3 h-16"
                   placeholder="Url" autocomplete="off"/>
            <label for="url"
                   class="absolute top-0 left-0 px-3 py-5 h-full pointer-events-none transform origin-left transition-all duration-100 ease-in-out ">Url</label>
          </div>
          <div class="grid grid-cols-2 mx-auto items-center space-x-2">
            <div class="floating-input mb-3 relative">
              <input v-model="newProduct.retailPrice" type="text" id="price"
                     class="border-2 border-purple-500 focus:outline-none rounded hover:bg-purple-100 focus:bg-purple-100 w-full p-3 h-16"
                     placeholder="Precio de salida ($)" autocomplete="off"/>
              <label for="price"
                     class="absolute top-0 left-0 px-3 py-5 h-full pointer-events-none transform origin-left transition-all duration-100 ease-in-out ">Precio
                de salida ($)</label>
            </div>
            <datepicker v-model="newProduct.releaseDate" name="releaseDatePicker" :format="format"
                        placeholder="Fecha de lanzamiento" :monday-first="true" :language="es"
                        :disabled-dates="disabledDates"
                        input-class="border-2 border-purple-500 focus:outline-none rounded hover:bg-purple-100 focus:bg-purple-100  mb-3 w-full p-3 h-16"></datepicker>
          </div>
          <multiselect v-model="genderValue" :options="genders" :multiple="false" :close-on-select="true"
                       :clear-on-select="false" :preserve-search="true" placeholder="Selecciona el género" label="name"
                       track-by="name" :preselect-first="false" :searchable="false"
                       selectLabel="" deselectLabel=""
                       selectedLabel="Seleccionada">
            <template slot="selection" slot-scope="{ values, search, isOpen }"><span class="multiselect__single"
                                                                                     v-if="values.length &amp;&amp; !isOpen">{{
                values.length
              }} opciones seleccionadas</span></template>
            <span slot="noOptions">Error! No hay géneros</span>
          </multiselect>
          <multiselect v-model="categoryValue" :options="categories" :multiple="false" :close-on-select="true"
                       :clear-on-select="false" :preserve-search="true" placeholder="Selecciona la categoría"
                       label="name"
                       track-by="name" :preselect-first="false" :searchable="false"
                       selectLabel="" deselectLabel=""
                       selectedLabel="Seleccionada">
            <template slot="selection" slot-scope="{ values, search, isOpen }"><span class="multiselect__single"
                                                                                     v-if="values.length &amp;&amp; !isOpen">{{
                values.length
              }} opciones seleccionadas</span></template>
            <span slot="noOptions">Error! No hay categorías</span>
          </multiselect>
          <div class="mt-3">
            <multiselect v-model="sizesValues" :options="sizes" :multiple="true" :close-on-select="false"
                         :clear-on-select="false" :preserve-search="true" placeholder="Selecciona las tallas"
                         label="name"
                         track-by="name" :preselect-first="false" :searchable="false"
                         selectLabel="" deselectLabel=""
                         selectedLabel="Seleccionada">
              <template slot="selection" slot-scope="{ values, search, isOpen }"><span class="multiselect__single"
                                                                                       v-if="values.length &amp;&amp; !isOpen">{{
                  values.length
                }} tallas seleccionadas</span></template>
              <span slot="noOptions">Selecciona una categoría!</span>
            </multiselect>
          </div>
          <div class="mt-3">
            <button @click="addProduct" type="button" style="font-family:'Quantico'"
                    class="bg-purple-500 text-xl p-2 px-5 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
              AÑADIR PRODUCTO
            </button>
          </div>
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
import {makeDateUtils} from '../utils/DateUtils'


export default {
  name: "AddProduct",
  components: {
    Datepicker,
    Multiselect
  },
  data() {
    const constructedDateUtils = makeDateUtils(this.useUtc)
    return {
      newProduct: {
        ref: '',
        brand: '',
        colorway: '',
        name: '',
        model: '',
        title: '',
        year: '',
        url: '',
        categoryType: '',
        gender: '',
        retailPrice: undefined,
        releaseDate: undefined,
        sizes: []
      },
      categories: [],
      categoryValue: null,
      genders: [{name: 'M'}, {name: 'F'}, {name: 'GS'}, {name: 'PS'}],
      genderValue: null,
      es: es,
      disabledDates: {
        from: new Date()
      },
      format: "dd-MM-yyyy",
      sizesValues: null,
      sneakerSizes: [{name: '40'}, {name: '40.5'}, {name: '41'}, {name: '42'}, {name: '42.5'}, {name: '43'}, {name: '44'}, {name: '44.5'}, {name: '45'}],
      clothingSizes: [{name: 'XS'}, {name: 'S'}, {name: 'M'}, {name: 'L'}, {name: 'XL'}, {name: 'XXL'}],
      utils: constructedDateUtils,
      formattedDate: null
    }
  },
  created() {
    this.getCategories()
  },
  watch: {
    'categoryValue': function (newVal, oldVal) {
      this.sizesValues = null
    }
  },
  methods: {
    getCategories() {
      this.categories = []
      axios({url: 'http://localhost:8888/product/getCategories', method: 'GET'})
          .then(resp => {
            this.categories = []
            for (var item of resp.data) {
              this.categories.push({name: item})
            }
          })
          .catch(err => {
            console.log(err.response)
          })
    },
    addProduct() {
      if (this.validate()) {
        var sizes = []
        for (var item of this.sizesValues) {
          sizes.push(item.name)
        }
        var year = this.formattedDate.slice(-4);
        const data = {
          ref: this.newProduct.ref,
          brand: this.newProduct.brand,
          colorway: this.newProduct.colorway,
          name: this.newProduct.name,
          model: this.newProduct.model,
          title: this.newProduct.title,
          year: year,
          url: this.newProduct.url,
          categoryType: this.categoryValue.name,
          gender: this.genderValue.name,
          retailPrice: this.newProduct.retailPrice,
          releaseDate: this.formattedDate,
          sizes: sizes
        }
        axios({url: 'http://localhost:8888/product/add', data: data, method: 'POST'})
            .then(resp => {
              this.$notify({
                group: 'err',
                title: 'Producto añadido',
                text: 'El producto ha sido correctamente guardado en la base de datos',
                type: 'success',
                duration: 5000,
              })
              this.resetForm()
            })
            .catch(err => {
              this.$notify({
                group: 'err',
                title: 'Error en el guardado',
                text: err.response.data,
                type: 'error',
                duration: 5000,
              })
            })
      } else {
        this.$notify({
          group: 'err',
          title: 'Error en el guardado',
          text: 'Alguno de los campos a modificar tiene un formato incorrecto',
          type: 'error',
          duration: 5000,
        })
      }

    },
    validate() {
      var p = this.newProduct
      this.formattedDate = this.utils.formatDate(new Date(p.releaseDate), this.format, es)
      if (p.ref !== "" && p.brand !== "" && p.colorway !== "" && p.name !== "" && p.model !== "" && p.url !== "" && p.title !== "" && p.retailPrice !== undefined && this.formattedDate !== undefined && this.categoryValue !== null && this.genderValue !== null && this.sizesValues !== null) {
        return true
      }
      return false
    },
    resetForm() {
      this.newProduct = {
        ref: '',
        brand: '',
        colorway: '',
        name: '',
        model: '',
        title: '',
        year: '',
        url: '',
        categoryType: '',
        gender: '',
        retailPrice: undefined,
        releaseDate: undefined,
        sizes: []
      }
      this.categoryValue = null
      this.genderValue = null
      this.formattedDate = null
      this.sizesValues = []
    }

  },
  computed: {
    sizes() {
      if (this.categoryValue == undefined) {
        return []
      }
      if (this.categoryValue.name === "Zapatillas") {
        return this.sneakerSizes
      } else if (this.categoryValue.name === "Ropa") {
        return this.clothingSizes
      } else {
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

.floating-input > input::placeholder {
  color: transparent;
}

.floating-input > input:focus,
.floating-input > input:not(:placeholder-shown) {
  @apply pt-8
}

.floating-input > input:focus ~ label,
.floating-input > input:not(:placeholder-shown) ~ label {
  @apply opacity-75 scale-75 -translate-y-3 translate-x-1;
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