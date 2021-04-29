<template>
  <div class="border-2 border-purple-500 h-20 rounded-lg width mx-auto">
    <h1>a</h1>
  </div>
</template>

<script>
export default {
  name: "Alert"
}
</script>

<style scoped>
.width {
  width: 400px
}

@screen md {
  .width {
    width: 600px
  }
}

@screen lg {
  .width {
    width: 800px
  }
}

</style>