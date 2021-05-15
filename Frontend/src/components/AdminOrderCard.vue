<template>
  <div class="cont flex border-2 border-purple-500 rounded-lg cursor-pointer" @click="openInfo">
    <div class="flex contImg justify-center items-center">
      <img class="mx-auto" :src="op.url"/>
    </div>

    <div class="inline contText pr-2">
      <h1 class="text-lg mt-4  leading-tight line-clamp-1">
        {{ op.title }}
      </h1>
      <div class="flex mt-2  justify-center space-x-7">
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
        <div class="">
          <h1>Ref. Pedido</h1>
          <h1 class="text-xl">{{ op.orderRef }}</h1>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "AdminOrderCard",
  props: {
    op: {
      type: Object,
      required: true
    },
  },
  methods: {
    openInfo() {
      this.$router.push({
        name: "AdminPedido",
        params: {
          ref: this.op.orderRef
        }
      });
    }
  }
}
</script>

<style scoped>
.cont {
  width: 630px !important
}

.contImg {
  height: 120px;
  width: 120px;
}

.contText {
  height: 120px;
  width: 490px;
}

img {
  max-width: 100px;
  max-height: 100px
}
</style>