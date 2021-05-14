<template>

  <div>
    <SmallCard v-for="(item, index) in items" :key="index"
               :title="item.title"
               :url="item.url"
               :price="item.retailPrice"
               :refer="item.ref"
    ></SmallCard>
  </div>

</template>

<script>
import SmallCard from '@/components/SmallCard.vue'

const axios = require("axios");

export default {
  name: "Search",
  components: {
    SmallCard
  },
  data() {
    return {
      items: [],
      sizes: [],
      brands: [],
      categories: [],
      years: [],
      search_name: "",
      search_brand: "",
      search_category: "",
      search_size: "",
      search_year: "",

    }
  },
  created() {
    this.getFiltered()
    this.getAllBrands()
    this.getAllCategories()
    this.getAllSizes()
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
      if (this.search_name !== "" && this.search_name !== null && this.search_name !== undefined) {
        filters.push({
          "value": this.search_name,
          "key": "title",
          "operator": "match"
        })
      }
      if (this.search_brand !== "" && this.search_brand !== null && this.search_brand !== undefined) {
        filters.push({
          "value": this.search_brand,
          "key": "brand",
          "operator": "match"
        })
      }
      if (this.search_year !== "" && this.search_year !== null && this.search_year !== undefined) {
        filters.push({
          "value": this.search_year,
          "key": "year",
          "operator": "match"
        })
      }
      if (filters.length === 0 && (this.search_category === null || this.search_category === "" || this.search_category === undefined)) {
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
              if (this.search_category === null || this.search_category === "" || this.search_category === undefined) {
                this.items = resp.data
              } else {
                for (let item of resp.data) {
                  if (item.categoryType === this.search_category) {
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