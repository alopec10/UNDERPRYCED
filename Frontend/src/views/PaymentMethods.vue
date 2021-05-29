<template>
  <div class="my-10">
    <h1 class="text-5xl mb-10 font-semibold" style="font-family:'Quantico'">MIS TARJETAS DE CRÉDITO</h1>
    <div
        class="grid gap-3 grid-cols-1 my-6 mx-10" v-bind:class="{'sm: grid-cols-1': paymentMethods.length === 1, 'lg:grid-cols-2': paymentMethods.length === 2, 'xl:grid-cols-3': paymentMethods.length > 2}">
      <CreditCard class="mx-auto text-xl"
                  v-for="(item, index) in paymentMethods"
                  :id="item.idPayMethod"
                  :name="item.name"
                  :number="item.number"
                  :expMonth="item.expMonth"
                  :exp-year="item.expYear"
                  :default-method="item.defaultMethod"
                  v-on:delete="deleteAttempt"
      ></CreditCard>
    </div>
    <hr class="mt-14">
    <div class="grid gap-6 my-6 mx-auto items-center w-1/2">
      <form class="mt-5">
        <h1 class="text-5xl font-semibold mb-12" style="font-family:'Quantico'">AÑADIR UNA NUEVA TARJETA</h1>
        <div class="grid gap-6 grid-cols-2 my-10 mx-auto items-center">
          <div class="floating-input mb-5 relative">
            <input v-model="newPayment.name" type="text" id="name"
                   class="border-2 border-purple-500 focus:outline-none rounded-xl hover:bg-purple-100 focus:bg-purple-100 w-full p-3 h-16"
                   placeholder="Nombre" autocomplete="off" @blur="$v.newPayment.name.$touch()"/>
            <label for="name"
                   class="absolute top-0 left-0 px-3 py-5 h-full pointer-events-none transform origin-left transition-all duration-100 ease-in-out ">Nombre</label>
            <div v-if="nameErrors.length!==0" class="text-sm mt-2 text-red-400 absolute">
              {{ nameErrors[0] }}
            </div>
          </div>
          <div class="floating-input mb-5 relative">
            <input v-model="newPayment.number" type="text" id="num"
                   class="border-2 border-purple-500 focus:outline-none rounded-xl hover:bg-purple-100 focus:bg-purple-100 w-full p-3 h-16"
                   placeholder="Número" autocomplete="off" @blur="$v.newPayment.number.$touch()"/>
            <label for="num"
                   class="absolute top-0 left-0 px-3 py-5 h-full pointer-events-none transform origin-left transition-all duration-100 ease-in-out ">Número</label>
            <div v-if="numberErrors.length!==0" class="text-sm mt-2 text-red-400 absolute">
              {{ numberErrors[0] }}
            </div>
          </div>
          <div class="floating-input mb-5 relative">
            <input v-model="newPayment.cvv" type="text" id="cvv"
                   class="border-2 border-purple-500 focus:outline-none rounded-xl hover:bg-purple-100 focus:bg-purple-100 w-full p-3 h-16"
                   placeholder="CVV" autocomplete="off" @blur="$v.newPayment.cvv.$touch()"/>
            <label for="cvv"
                   class="absolute top-0 left-0 px-3 py-5 h-full pointer-events-none transform origin-left transition-all duration-100 ease-in-out ">CVV</label>
            <div v-if="cvvErrors.length!==0" class="text-sm mt-2 text-red-400 absolute">
              {{ cvvErrors[0] }}
            </div>
          </div>
          <div class="floating-input mb-5 relative">
            <input v-model="newPayment.expMonth" type="text" id="month"
                   class="border-2 border-purple-500 focus:outline-none rounded-xl hover:bg-purple-100 focus:bg-purple-100 w-full p-3 h-16"
                   placeholder="Mes" autocomplete="off" @blur="$v.newPayment.expMonth.$touch()"/>
            <label for="month"
                   class="absolute top-0 left-0 px-3 py-5 h-full pointer-events-none transform origin-left transition-all duration-100 ease-in-out ">Mes</label>
            <div v-if="monthErrors.length!==0" class="text-sm mt-2 text-red-400 absolute">
              {{ monthErrors[0] }}
            </div>
          </div>
          <div class="floating-input mb-5 relative">
            <input v-model="newPayment.expYear" type="text" id="year"
                   class="border-2 border-purple-500 focus:outline-none rounded-xl hover:bg-purple-100 focus:bg-purple-100 w-full p-3 h-16"
                   placeholder="Año" autocomplete="off" @blur="$v.newPayment.expYear.$touch()"/>
            <label for="year"
                   class="absolute top-0 left-0 px-3 py-5 h-full pointer-events-none transform origin-left transition-all duration-100 ease-in-out ">Año</label>
            <div v-if="yearErrors.length!==0" class="text-sm mt-2 text-red-400 absolute">
              {{ yearErrors[0] }}
            </div>
          </div>
          <div class="mb-5">
            <button @click="addPaymentMethod" type="button" style="font-family:'Quantico'"
                    class="bg-purple-500 text-xl p-3 px-5 rounded-xl text-white shadow-xl hover:shadow-inner focus:outline-none transition duration-500 ease-in-out  transform hover:-translate-x hover:scale-105">
              AÑADIR TARJETA
            </button>
          </div>
        </div>
      </form>
    </div>
    <modal ref="modalName">
      <template v-slot:header>
        <h1 class="text-4xl font-semibold mb-5" style="font-family: 'Quantico'">
          ¿ELIMINAR MÉTODO DE PAGO?
        </h1>
      </template>

      <template v-slot:body>
        <p>¿Estás seguro de que deseas eliminar tu único método de pago?</p>
        <p class="mt-3">Si lo haces, todas tus pujas y ofertas serán eliminadas</p>
        <p class="mt-3">¡Piénsalo!</p>
      </template>

      <template v-slot:footer>
        <div class="space-x-4">
          <button
              type="button"
              style="font-family: 'Quantico'"
              class="
              bg-purple-500
              text-xl
              p-3
              px-5
              rounded-xl
              text-white
              shadow-xl
              hover:shadow-inner
              focus:outline-none
              transition
              duration-500
              ease-in-out
              transform
              hover:-translate-x
              hover:scale-105
            "
              @click="
              deleteMethod();
              $refs.modalName.closeModal();
            "
          >
            ELIMINAR
          </button>
          <button
              type="button"
              style="font-family: 'Quantico'"
              class="
              bg-purple-500
              text-xl
              p-3
              px-5
              rounded-xl
              text-white
              shadow-xl
              hover:shadow-inner
              focus:outline-none
              transition
              duration-500
              ease-in-out
              transform
              hover:-translate-x
              hover:scale-105
            "
              @click="$refs.modalName.closeModal()"
          >
            CERRAR
          </button>
        </div>
      </template>
    </modal>
  </div>
</template>

<script>
const axios = require("axios");
import CreditCard from '@/components/CreditCard.vue'
import {validationMixin} from "vuelidate";
import {
  required
} from "vuelidate/lib/validators";
import Modal from '@/components/Modal';

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
  name: "PaymentMethods",
  components: {
    CreditCard,
    Modal
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
      removeId: null
    }
  },
  mounted() {
    this.getPaymentMethods()
  },
  methods: {
    getPaymentMethods() {
      axios({url: 'http://localhost:8888/payMethod/getAll', method: 'GET'})
          .then(resp => {
            this.paymentMethods = resp.data
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
            })
            .catch(err => {
              this.$notify({
                group: 'err',
                title: 'Error en el guardado',
                text: 'Alguno de los campos tiene un formato incorrecto',
                type: 'error',
                duration: 5000,
              })
            })
      }
      else{
        this.$notify({
          group: 'err',
          title: 'Error en el guardado',
          text: 'Alguno de los campos tiene un formato incorrecto',
          type: 'error',
          duration: 5000,
        })
      }
    },
    deleteAttempt(id){
      this.removeId = id
      if(this.paymentMethods.length === 1){
        this.$refs.modalName.openModal()
      }
      else{
        this.deleteMethod()
      }
    },
    deleteMethod(){
      const data= {
        idPaymentMethod: this.removeId
      }
      axios({url: 'http://localhost:8888/payMethod/delete', data: data, method: 'POST'})
          .then(resp => {
            this.getPaymentMethods()
          })
          .catch(err => {
            console.log(err)
          })
    }
  },
  computed: {
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
    }
  }
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

label { /* Chrome/Opera/Safari */
  font-family:'Quantico';
}

</style>