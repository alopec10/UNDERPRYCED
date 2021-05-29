<template>
  <div class="cont flex border-2 border-purple-500 rounded-lg">
    <div class="flex contImg justify-center items-center">
      <img class="mx-auto" :src="op.url"/>
    </div>
    <div class="inline contText pr-2">
      <h1 class="text-lg mt-4  leading-tight line-clamp-1">
        {{ op.title }}
      </h1>
      <div class="flex mt-2 space-x-7 justify-center">
        <div class="">
          <h1>Precio</h1>
          <h1 class="text-xl">{{ op.price }}€</h1>
        </div>
        <div class="">
          <h1>Talla</h1>
          <h1 class="text-xl">{{ op.size }}</h1>
        </div>
        <div class="">
          <h1>Fecha</h1>
          <h1 class="text-xl">{{ op.date }}</h1>
        </div>
      </div>
    </div>
    <div v-if="typeOp!=='sp'" class="contIcons justify-center items-center text-purple-500">
      <i class="fas fa-edit block text-xl mt-6 mr-1 cursor-pointer" @click="redirectOp"></i>
      <i class="fas fa-trash-alt block text-xl mt-4 mr-2 cursor-pointer" @click="deleteOperation"></i>
    </div>
  </div>
</template>

<script>
const axios = require("axios");
export default {
  name: "OperationCard",
  props: {
    op: {
      type: Object,
      required: true
    },
    typeOp: {
      type: String,
      required: true
    }
  },
  methods: {
    deleteOperation() {
      const dat = {
        "idProductDetails": this.op.idProductDetails
      }
      axios({url: 'http://localhost:8888/' + this.typeOp + '/delete', data: dat, method: 'POST'})
          .then(resp => {
            this.$emit("delete");
            this.$notify({
              group: 'err',
              title: 'Borrado efectuado',
              text: 'El borrado se ha realizado de forma correcta',
              type: 'success',
              duration: 5000,
            })
          })
          .catch(err => {
          })
    },
    redirectOp() {
      if (this.typeOp == "ask") {
        this.$router.push({
          name: "Vender",
          params: {
            ref: this.op.ref,
            size: this.op.size
          }
        });
      }
      if (this.typeOp == "bid") {
        this.$router.push({
          name: "Comprar",
          params: {
            ref: this.op.ref,
            size: this.op.size
          }
        });
      }

    }
  }
}
</script>

<style scoped>
.cont {
  width: 420px !important
}

.contImg {
  height: 120px;
  width: 120px;
}

.contText {
  height: 120px;
  width: 280px;
}

.contIcons {
  height: 120px;
  width: 40px;
}

img {
  max-width: 100px;
  max-height: 100px
}

</style>