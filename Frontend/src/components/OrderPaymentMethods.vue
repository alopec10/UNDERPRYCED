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
          @click="previousPage" type="button" style="font-family:'Quantico'"
              class="bg-purple-500 text-xl p-3 px-5 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">

        ATRÁS
      </button>
      <button @click="addMethod = true" type="button" style="font-family:'Quantico'"
              class="bg-purple-500 text-xl p-3 px-5 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">

        AÑADIR MÉTODO PAGO
      </button>
      <button @click="nextPage" type="button" style="font-family:'Quantico'"
              class="bg-purple-500 text-xl p-3 px-5 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">

        SIGUIENTE
      </button>
    </div>
    <div v-else>
      <div>
        <form class="mt-5">
          <h1 class="text-4xl font-semibold mb-10" style="font-family:'Quantico'">AÑADIR UNA NUEVA TARJETA</h1>
          <div class="mx-auto mt-7 w-1/2">
            <div class="floating-input mb-10 relative">
              <input v-model="newPayment.name" type="text" id="name"
                     class="border-2 border-purple-500 focus:outline-none rounded-xl hover:bg-purple-100 focus:bg-purple-100 w-full p-3 h-16"
                     placeholder="Nombre" autocomplete="off" @blur="$v.newPayment.name.$touch()"/>
              <label for="name"
                     class="absolute top-0 left-0 px-3 py-5 h-full pointer-events-none transform origin-left transition-all duration-100 ease-in-out ">Nombre</label>
              <div v-if="nameErrors.length!==0" class="text-sm mt-2 text-red-400 absolute">
                {{ nameErrors[0] }}
              </div>
            </div>
            <div class="floating-input mb-10 relative">
              <input v-model="newPayment.number" type="text" id="num"
                     class="border-2 border-purple-500 focus:outline-none rounded-xl hover:bg-purple-100 focus:bg-purple-100 w-full p-3 h-16"
                     placeholder="Número" autocomplete="off" @blur="$v.newPayment.number.$touch()"/>
              <label for="num"
                     class="absolute top-0 left-0 px-3 py-5 h-full pointer-events-none transform origin-left transition-all duration-100 ease-in-out ">Número</label>
              <div v-if="numberErrors.length!==0" class="text-sm mt-2 text-red-400 absolute">
                {{ numberErrors[0] }}
              </div>
            </div>
            <div class="floating-input mb-10 relative">
              <input v-model="newPayment.cvv" type="text" id="cvv"
                     class="border-2 border-purple-500 focus:outline-none rounded-xl hover:bg-purple-100 focus:bg-purple-100 w-full p-3 h-16"
                     placeholder="CVV" autocomplete="off" @blur="$v.newPayment.cvv.$touch()"/>
              <label for="cvv"
                     class="absolute top-0 left-0 px-3 py-5 h-full pointer-events-none transform origin-left transition-all duration-100 ease-in-out ">CVV</label>
              <div v-if="cvvErrors.length!==0" class="text-sm mt-2 text-red-400 absolute">
                {{ cvvErrors[0] }}
              </div>
            </div>
            <div class="floating-input mb-10 relative">
              <input v-model="newPayment.expMonth" type="text" id="month"
                     class="border-2 border-purple-500 focus:outline-none rounded-xl hover:bg-purple-100 focus:bg-purple-100 w-full p-3 h-16"
                     placeholder="Mes" autocomplete="off" @blur="$v.newPayment.expMonth.$touch()"/>
              <label for="month"
                     class="absolute top-0 left-0 px-3 py-5 h-full pointer-events-none transform origin-left transition-all duration-100 ease-in-out ">Mes</label>
              <div v-if="monthErrors.length!==0" class="text-sm mt-2 text-red-400 absolute">
                {{ monthErrors[0] }}
              </div>
            </div>
            <div class="floating-input mb-10 relative">
              <input v-model="newPayment.expYear" type="text" id="year"
                     class="border-2 border-purple-500 focus:outline-none rounded-xl hover:bg-purple-100 focus:bg-purple-100 w-full p-3 h-16"
                     placeholder="Año" autocomplete="off" @blur="$v.newPayment.expYear.$touch()"/>
              <label for="year"
                     class="absolute top-0 left-0 px-3 py-5 h-full pointer-events-none transform origin-left transition-all duration-100 ease-in-out ">Año</label>
              <div v-if="yearErrors.length!==0" class="text-sm mt-2 text-red-400 absolute">
                {{ yearErrors[0] }}
              </div>
            </div>
            <div class="mt-7">
              <button v-if="fromPage==='buy'"
                      @click="previousPage" type="button" style="font-family:'Quantico'"
                      class="bg-purple-500 mr-5 text-xl p-3 px-5 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">

                ATRÁS
              </button>
              <button @click="addPaymentMethod" type="button" style="font-family:'Quantico'"
                      class="bg-purple-500 text-xl p-3 px-5 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
                AÑADIR TARJETA
              </button>
            </div>
            <div class="mt-7">
              <button v-if="paymentMethods.length!==0"
                      @click="addMethod = false" type="button" style="font-family:'Quantico'"
                      class="bg-purple-500 text-xl p-3 px-5 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">

                SELECCIONAR MÉTODO PAGO
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
      !this.$v.newPayment.name.required && errors.push("Campo requerido.");
      !this.$v.newPayment.name.validName &&
      errors.push("El nombre debe tener como máximo 50 caracteres.");
      return errors;
    },
    numberErrors() {
      const errors = [];
      if (!this.$v.newPayment.number.$dirty) return errors;
      !this.$v.newPayment.number.required && errors.push("Campo requerido.");
      !this.$v.newPayment.number.validNumber &&
      errors.push("Formato inválido");
      return errors;
    },
    cvvErrors() {
      const errors = [];
      if (!this.$v.newPayment.cvv.$dirty) return errors;
      !this.$v.newPayment.cvv.required && errors.push("Campo requerido.");
      !this.$v.newPayment.cvv.validCvv &&
      errors.push("Formato inválido");
      return errors;
    },
    monthErrors() {
      const errors = [];
      if (!this.$v.newPayment.expMonth.$dirty) return errors;
      !this.$v.newPayment.expMonth.required && errors.push("Campo requerido.");
      !this.$v.newPayment.expMonth.validMonth &&
      errors.push("Formato inválido");
      return errors;
    },
    yearErrors() {
      const errors = [];
      if (!this.$v.newPayment.expYear.$dirty) return errors;
      !this.$v.newPayment.expYear.required && errors.push("Campo requerido.");
      !this.$v.newPayment.expYear.validYear &&
      errors.push("Formato inválido");
      !this.$v.newPayment.expYear.yearExpired &&
      errors.push("Tarjeta no valida, está caducada");
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