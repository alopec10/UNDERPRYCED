<template>
  <div class="card" @click="cardClicked" v-bind:class="{ 'bg-purple-100' : selected }">
    <p>Número: {{modifiedNumber}}</p>
    <p>Nombre titular: {{name}} </p>
    <p>Fecha caducidad: {{expMonth}}/{{expYear}}</p>
  </div>
</template>

<script>
const axios = require("axios");

export default {
  name: "OrderCreditCard",
  props: {
    id: {
      type: Number,
      required: true
    },
    name: {
      type: String,
      required: true
    },
    number: {
      type: String,
      required: true
    },
    expMonth: {
      type: String,
      required: true
    },
    expYear: {
      type: String,
      required: true
    },
    defaultMethod: {
      type: Boolean,
      required: true
    },
    selected: {
      type: Boolean,
      required: true
    }
  },
  computed: {
    modifiedNumber(){
      let pad = "************"
      return pad + this.number
    }
  },
  methods: {
    cardClicked(){
      let card = {
        id: this.id,
        number: this.number,
        expMonth: this.expMonth,
        expYear: this.expYear
      }
      this.$emit('cardClicked', card)
    }
  }
}
</script>

<style scoped>

</style>