<template>
  <div class="my-10">
    <h1 class="text-4xl mt-14">MIS TARJETAS DE CRÉDITO</h1>
    <div
        class="flex my-6 mx-10">
      <CreditCard class="mx-auto text-xl"
          v-for="(item, index) in paymentMethods"
                  :id="item.idPayMethod"
                  :name="item.name"
                  :number="item.number"
                  :expMonth="item.expMonth"
                  :exp-year="item.expYear"
                  :default-method="item.defaultMethod"
                  v-on:delete="getPaymentMethods"
      ></CreditCard>
    </div>
    <hr class="mt-14">
    <div>
      <form class="mt-10">
        <h1 class="text-4xl">Añadir una nueva tarjeta</h1>
        <div class="mx-auto mt-10">
          <div>
            <input v-model="newPayment.name" type="text" placeholder="Nombre"
                   class="mt-1 h-14 text-2xl mx-auto block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0">
          </div>
          <div class="mt-7">
            <input v-model="newPayment.number" type="text" placeholder="Número"
                   class="mt-1 h-14 text-2xl mx-auto block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0">
          </div>
          <div class="mt-7">
            <input v-model="newPayment.cvv" type="text" placeholder="CVV"
                   class="mt-1 mx-auto h-14 text-2xl block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0">
          </div>
          <div class="mt-7">
            <input v-model="newPayment.expMonth" type="text" placeholder="Mes"
                   class="mt-1 mx-auto h-14 text-2xl block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0">
          </div>
          <div class="mt-7">
            <input v-model="newPayment.expYear" type="text" placeholder="Año"
                   class="mt-1 mx-auto h-14 text-2xl block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0">
          </div>
          <div class="mt-7">
            <button @click="addPaymentMethod" type="button"
                    class="bg-purple-500 text-xl p-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
              Añadir tarjeta
            </button>
          </div>
        </div>

      </form>
    </div>
  </div>
</template>

<script>
const axios = require("axios");
import CreditCard from '@/components/CreditCard.vue'

export default {
  name: "PaymentMethods",
  components: {
    CreditCard
  },
  data() {
    return {
      paymentMethods: [],
      newPayment: {
        name: '',
        number: '',
        cvv: '',
        expMonth: '',
        expYear: '',
        defaultMethod: 1
      }
    }
  },
  mounted() {
    this.getPaymentMethods()
  },
  methods: {
    getPaymentMethods() {
      this.paymentMethods = []
      axios({url: 'http://localhost:8888/payMethod/getAll', method: 'GET'})
          .then(resp => {
            this.paymentMethods = resp.data
          })
          .catch(err => {
            console.log(err)
          })
    },
    addPaymentMethod() {
      if (this.validateForm()) {
        axios({url: 'http://localhost:8888/payMethod/add', data: this.newPayment, method: 'POST'})
            .then(resp => {
              console.log(resp)
              this.getPaymentMethods()
              this.newPayment = {
                name: '',
                number: '',
                cvv: '',
                expMonth: '',
                expYear: '',
                defaultMethod: 1
              }
            })
            .catch(err => {
              console.log(err)
            })
      }
    },
    validateForm() {
      return this.newPayment.name !== "" && this.newPayment.number !== "" && this.newPayment.cvv !== "" && this.newPayment.expMonth !== "" && this.newPayment.expYear !== "";

    }
  }
}
</script>

<style scoped>
</style>