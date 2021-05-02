<template>
  <div>
    <div v-if="showMethods">
      <h1 class="text-4xl">ELIGE TU MÉTODO DE PAGO</h1>
      <div
          class="flex my-6 mx-10">
        <OrderCreditCard class="mx-auto text-xl my-5"
                    v-for="(item, index) in paymentMethods"
                    :id="item.idPayMethod"
                    :name="item.name"
                    :number="item.number"
                    :expMonth="item.expMonth"
                    :exp-year="item.expYear"
                    :default-method="item.defaultMethod"
                         :selected="item.selected"
                    v-on:cardClicked="cardClicked"
        ></OrderCreditCard>
      </div>
      <button v-if="fromPage==='buy'"
          @click="previousPage"
              class="bg-purple-500 text-xl p-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">

        ATRÁS
      </button>
      <button @click="addMethod = true"
              class="bg-purple-500 text-xl p-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">

        AÑADIR MÉTODO PAGO
      </button>
      <button @click="nextPage"
              class="bg-purple-500 text-xl p-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">

        SIGUIENTE
      </button>
    </div>
    <div v-else>
      <div>
        <form class="mt-10">
          <h1 class="text-4xl">Añadir una nueva tarjeta</h1>
          <div class="mx-auto mt-10">
            <div>
              <input v-model="newPayment.name" type="text" placeholder="Nombre"
                     class="mt-1 h-14 text-2xl mx-auto block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0"
                     @blur="$v.newPayment.name.$touch()">
            </div>
            <div v-if="nameErrors.length!==0">
              {{ nameErrors }}
            </div>
            <div class="mt-7">
              <input v-model="newPayment.number" type="text" placeholder="Número"
                     class="mt-1 h-14 text-2xl mx-auto block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0"
                     @blur="$v.newPayment.number.$touch()">
            </div>
            <div v-if="numberErrors.length!==0">
              {{ numberErrors }}
            </div>
            <div class="mt-7">
              <input v-model="newPayment.cvv" type="text" placeholder="CVV"
                     class="mt-1 mx-auto h-14 text-2xl block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0"
                     @blur="$v.newPayment.cvv.$touch()">
            </div>
            <div v-if="cvvErrors.length!==0">
              {{ cvvErrors }}
            </div>
            <div class="mt-7">
              <input v-model="newPayment.expMonth" type="text" placeholder="Mes"
                     class="mt-1 mx-auto h-14 text-2xl block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0"
                     @blur="$v.newPayment.expMonth.$touch()">
            </div>
            <div v-if="monthErrors.length!==0">
              {{ monthErrors }}
            </div>
            <div class="mt-7">
              <input v-model="newPayment.expYear" type="text" placeholder="Año"
                     class="mt-1 mx-auto h-14 text-2xl block text-center border-2 border-purple-500 h-11 rounded-xl shadow-lg hover:bg-purple-100 focus:bg-purple-100 focus:ring-0"
                     @blur="$v.newPayment.expYear.$touch()">
            </div>
            <div v-if="yearErrors.length!==0">
              {{ yearErrors }}
            </div>
            <div class="mt-7">
              <button v-if="fromPage==='buy'"
                      @click="previousPage"
                      class="bg-purple-500 text-xl p-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">

                ATRÁS
              </button>
              <button v-if="paymentMethods.length!==0"
                  @click="addMethod = false"
                      class="bg-purple-500 text-xl p-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">

                SELECCIONAR MÉTODO PAGO
              </button>
              <button @click="addPaymentMethod" type="button"
                      class="bg-purple-500 text-xl p-3 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
                Añadir tarjeta
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
const axios = require("axios");
import OrderCreditCard from '@/components/OrderCreditCard.vue'
import {validationMixin} from "vuelidate";
import {
  required
} from "vuelidate/lib/validators";

const validName = value => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return false
  }
  return /^.{1,50}$/.test(value)
}

const validNumber = value => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return false
  }
  return /^4[0-9]{12}(?:[0-9]{3})?$|^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$|^3[47][0-9]{13}$|^3(?:0[0-5]|[68][0-9])[0-9]{11}$|^6(?:011|5[0-9]{2})[0-9]{12}$|^(?:2131|1800|35\d{3})\d{11}$/.test(value)
}

const validCvv = value => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return false
  }
  return /^[0-9]{3,4}$/.test(value)
}

const validMonth = value => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return false
  }
  return /^(0[1-9]|1[0-2])$/.test(value)
}

const validYear = value => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return false
  }
  return /^[0-9]{2}$/.test(value)
}

const yearExpired = value => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return false
  }
  const currentTime = new Date();
  let year = currentTime.getFullYear();
  let input = "20" + value
  let integer_input = parseInt(input, 10);
  return integer_input >= year
}

export default {
  name: "OrderPaymentMethods",
  components: {
    OrderCreditCard
  },
  mixins: [validationMixin],
  validations: {
    newPayment: {
      name: {
        required, validName
      },
      number: {
        required, validNumber
      },
      cvv: {
        required, validCvv
      },
      expMonth: {
        required, validMonth
      },
      expYear: {
        required, validYear, yearExpired
      }
    }
  },
  props: {
    fromPage: {
      type: String,
      required: true
    }
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
      },
      addMethod: false
    }
  },
  mounted() {
    this.getPaymentMethods()
  },
  computed: {
    showMethods() {
      if(this.paymentMethods.length === 0){
        return false
      }
      else{
        return !this.addMethod
      }
    },
    nameErrors() {
      const errors = [];
      if (!this.$v.newPayment.name.$dirty) return errors;
      !this.$v.newPayment.name.validName &&
      errors.push("El nombre debe tener como máximo 50 caracteres.");
      !this.$v.newPayment.name.required && errors.push("Campo requerido.");
      return errors;
    },
    numberErrors() {
      const errors = [];
      if (!this.$v.newPayment.number.$dirty) return errors;
      !this.$v.newPayment.number.validNumber &&
      errors.push("Formato inválido");
      !this.$v.newPayment.number.required && errors.push("Campo requerido.");
      return errors;
    },
    cvvErrors() {
      const errors = [];
      if (!this.$v.newPayment.cvv.$dirty) return errors;
      !this.$v.newPayment.cvv.validCvv &&
      errors.push("Formato inválido");
      !this.$v.newPayment.cvv.required && errors.push("Campo requerido.");
      return errors;
    },
    monthErrors() {
      const errors = [];
      if (!this.$v.newPayment.expMonth.$dirty) return errors;
      !this.$v.newPayment.expMonth.validMonth &&
      errors.push("Formato inválido");
      !this.$v.newPayment.expMonth.required && errors.push("Campo requerido.");
      return errors;
    },
    yearErrors() {
      const errors = [];
      if (!this.$v.newPayment.expYear.$dirty) return errors;
      !this.$v.newPayment.expYear.validYear &&
      errors.push("Formato inválido");
      !this.$v.newPayment.expYear.yearExpired &&
      errors.push("Tarjeta no valida, está caducada");
      !this.$v.newPayment.expYear.required && errors.push("Campo requerido.");
      return errors;

    },
  },
  methods: {
    getPaymentMethods() {
      this.paymentMethods = []
      axios({url: 'http://localhost:8888/payMethod/getAll', method: 'GET'})
          .then(resp => {
            let ps = this.paymentMethods
            let self = this
            resp.data.forEach(function (item, index) {
              item.selected = index === 0;
              if(index === 0){
                self.$emit('cardClicked', item)
              }
              ps.push(item)
            });
            this.paymentMethods = ps
          })
          .catch(err => {
            console.log(err)
          })
    },
    addPaymentMethod() {
      this.$v.$touch();
      if (!this.$v.$invalid) {
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
              this.$v.$reset();
              this.addMethod = false
            })
            .catch(err => {
              console.log(err)
            })
      }
    },
    nextPage(){
      if(this.paymentMethods.length !== 0){
        this.$emit('paymentNext')
      }
    },
    previousPage(){
      this.$emit('paymentPrev')
    },
    cardClicked(card){
      this.$emit('cardClicked', card)
      this.paymentMethods.forEach(function (item, index) {
        item.selected = item.idPayMethod === card.id;
      });
    }
  },

}
</script>

<style scoped>

</style>