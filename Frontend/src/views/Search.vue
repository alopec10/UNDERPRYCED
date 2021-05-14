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
      items: []
    }
  },
  created() {
    this.getAll()
  },
  methods: {
    getAll() {
      const refs = {"specifications": [{
        "value": "",
        "key": "ref",
        "operator": "!="
      }]}
      axios({url: 'http://localhost:8888/product/specification', data: refs, method: 'POST'})
          .then(resp => {
            this.items = []
            this.items = resp.data
          })
          .catch(err => {

          })
    },
  }
}
</script>

<style scoped>

</style>