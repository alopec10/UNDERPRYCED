<template>
  <div class="border-2 border-purple-500 h-20 rounded-lg width mx-auto flex">
    <div class="mt-3">
      <h1 class="text-xl">{{alert.title}}</h1>
      <h1 class="text-md mt-3">{{alert.message}}</h1>
    </div>
    <div class="justify-center items-center text-purple-500">
      <i class="fas fa-check block text-xl mt-6 mr-1 cursor-pointer"></i>
    </div>

  </div>
</template>

<script>
export default {
  name: "Alert",
  props: {
    alert: {
      type: Object,
      required: true
    }
  }

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