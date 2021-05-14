<template>
  <div>
    <h1 @click="pushToAddProduct">
      AÑADIR PRODUCTO
    </h1>
  </div>
</template>

<script>
export default {
  name: "AdminHome",
  methods: {
    pushToAddProduct(){
      this.$router.push('\crearProducto')
    }
  }
}
</script>

<style scoped>

</style>