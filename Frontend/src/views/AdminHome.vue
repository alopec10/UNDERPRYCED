<template>
  <div class="my-10 space-y-16">
    <div>
      <h1 class="mt-2 text-5xl font-semibold" style="font-family:'Quantico'">
        BIENVENIDO DE NUEVO
      </h1>
    </div>
    <div class="grid grid-cols-1 lg:grid-cols-2 place-items-center">

      <div class="block w-1/2 border-2 cursor-pointer border-purple-500 rounded-lg p-5" @click="pushToAddProduct">
        <h1 class="mt-2 text-4xl font-semibold" style="font-family:'Quantico'">
          CREAR PRODUCTO
        </h1>
        <img src="@/assets/content.png"/>
      </div>
      <div class="block w-1/2 border-2 cursor-pointer border-purple-500 rounded-lg p-5" @click="pushToAcceptProduct">
        <h1 class="mt-2 text-4xl font-semibold" style="font-family:'Quantico'">
          ACEPTAR PEDIDOS
        </h1>
        <img src="@/assets/delivery.png"/>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: "AdminHome",
  methods: {
    pushToAddProduct() {
      this.$router.push('\crearProducto')
    },
    pushToAcceptProduct() {
      this.$router.push('\aprobarPedidos')
    }
  }
}
</script>

<style scoped>

</style>