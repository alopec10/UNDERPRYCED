<template>
  <div class="inline items-center justify-center w-7/12" >
    <h1 class="text-5xl my-4 cursor-pointer uppercase" style="font-family:'Quantico'" @click="pushToProduct">
      {{ product.title }}
    </h1>
    <div class="mx-auto flex justify-center w-11/12">
      <img :src="product.url" class="my-6 mx-10 p-5  cursor-pointer border-2 rounded-lg shadow-xl border-purple-300 " @click="pushToProduct"/>
    </div>
  </div>
</template>

<script>
export default {
  name: "ConfirmOrderProduct",
  props: {
    product: {
      type: Object,
      required: true
    }
  },
  methods: {
    pushToProduct() {
      this.$router.push({
        name: "Producto",
        params: {
          ref: this.product.ref,
        }
      })
    }
  }
}
</script>

<style scoped>

</style>