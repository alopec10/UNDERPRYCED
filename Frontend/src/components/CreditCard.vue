<template>
<!--  <div class="card">
    <p>Número: {{modifiedNumber}}</p>
    <p>Nombre titular: {{name}} </p>
    <p>Fecha caducidad: {{expMonth}}/{{expYear}}</p>
    <button @click="remove" type="button">Eliminar</button>
  </div>-->
  <div class="flex justify-center items-center">
    <div class="space-y-16">
      <div class="w-96 h-56 m-auto bg-red-100 rounded-xl relative text-white shadow-md transition-transform transform hover:scale-105 hover:shadow-2xl">

        <img class="relative object-cover w-full h-full rounded-xl" src="@/assets/credit_card_dark.png">

        <div class="w-full px-8 absolute top-8">
          <div class="flex justify-between">
            <div class="">
              <p class="font-light flex justify-start">
                Nombre
                </p>
              <p class="font-bold tracking-widest font-mono flex justify-start">
                {{ name }}
              </p>
            </div>
            <img class="w-14" src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Mastercard-logo.svg/1000px-Mastercard-logo.svg.png"/>
          </div>
          <div class="pt-3 block">
            <p class="font-light flex justify-start">
              Num.
              </p>
            <p class="font-medium tracking-more-wider font-mono flex justify-start pt-2">
              <span class="rounded-full py-1 text-xs">●●●● ●●●● ●●●●</span> <strong class="ml-2">{{ number }}</strong>
            </p>

          </div>
          <div class="pt-4 pr-6">
            <div class="flex justify-between">

              <div class="block">
                <p class="font-light text-xs text-xs flex justify-start">
                  Expira en
                  </p>
                <p class="font-medium tracking-wider text-sm font-mono flex justify-start">
                  {{expMonth}}/{{expYear}}
                </p>
              </div>

              <div class="">
                <p class="font-semibold text-sm cursor-pointer" @click="remove">
                  ELIMINAR
                  </p>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
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
      this.$emit('delete', this.id)
    }
  }
}
</script>

<style scoped>

</style>