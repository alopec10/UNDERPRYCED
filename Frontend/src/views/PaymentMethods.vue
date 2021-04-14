<template>
  <div>
    <h1 class="text-3xl mt-14">MIS TARJETAS DE CRÉDITO</h1>
    <div
        class="grid gap-6 grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-4 2xl:grid-cols-5 my-6 mx-10">
      <CreditCard v-for="(item, index) in paymentMethods"
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
        <h1>Añadir una nueva tarjeta</h1>
        <div>
          <input v-model="newPayment.name" type="text" placeholder="Nombre" class="mt-1 block w-full border-none bg-gray-100 h-11 rounded-xl shadow-lg hover:bg-blue-100 focus:bg-blue-100 focus:ring-0">
        </div>
        <div class="mt-7">
          <input v-model="newPayment.number" type="text" placeholder="Número" class="mt-1 block w-full border-none bg-gray-100 h-11 rounded-xl shadow-lg hover:bg-blue-100 focus:bg-blue-100 focus:ring-0">
        </div>
        <div class="mt-7">
          <input v-model="newPayment.cvv" type="text" placeholder="CVV" class="mt-1 block w-full border-none bg-gray-100 h-11 rounded-xl shadow-lg hover:bg-blue-100 focus:bg-blue-100 focus:ring-0">
        </div>
        <div class="mt-7">
          <input v-model="newPayment.expMonth" type="text" placeholder="Mes" class="mt-1 block w-full border-none bg-gray-100 h-11 rounded-xl shadow-lg hover:bg-blue-100 focus:bg-blue-100 focus:ring-0">
        </div>
        <div class="mt-7">
          <input v-model="newPayment.expYear" type="text" placeholder="Año" class="mt-1 block w-full border-none bg-gray-100 h-11 rounded-xl shadow-lg hover:bg-blue-100 focus:bg-blue-100 focus:ring-0">
        </div>
        <div class="mt-7">
          <button @click="addPaymentMethod" type="button" class="bg-blue-500 w-full py-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
            Añadir tarjeta
          </button>
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
  data(){
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
  mounted(){
    this.getPaymentMethods()
  },
  methods: {
    getPaymentMethods(){
      this.paymentMethods = []
      axios({url: 'http://localhost:8888/payMethod/getAll', method: 'GET'})
          .then(resp => {
            this.paymentMethods = resp.data
          })
          .catch(err => {
            console.log(err)
          })
    },
    addPaymentMethod(){
      if(this.validateForm()){
        axios({url: 'http://localhost:8888/payMethod/add', data:this.newPayment, method: 'POST'})
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
    validateForm(){
      return this.newPayment.name !== "" && this.newPayment.number !== "" && this.newPayment.cvv !== "" && this.newPayment.expMonth !== "" && this.newPayment.expYear !== "";

    }
  }
}
</script>

<style scoped>

</style>