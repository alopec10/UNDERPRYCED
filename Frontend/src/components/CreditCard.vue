<template>
  <div class="card">
    <p>Número: {{modifiedNumber}}</p>
    <p>Nombre titular: {{name}} </p>
    <p>Fecha caducidad: {{expMonth}}/{{expYear}}</p>
    <button @click="remove" type="button">Eliminar</button>
  </div>
</template>

<script>
const axios = require("axios");

export default {
  name: "CreditCard",
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
    }
  },
  computed: {
    modifiedNumber(){
      let pad = "************"
      return pad + this.number
    }
  },
  methods: {
    remove(){
      const data= {
        idPaymentMethod: this.id
      }
      axios({url: 'http://localhost:8888/payMethod/delete', data: data, method: 'POST'})
          .then(resp => {
            this.$emit("delete");
          })
          .catch(err => {
            console.log(err)
          })
    }
  }
}
</script>

<style scoped>

</style>