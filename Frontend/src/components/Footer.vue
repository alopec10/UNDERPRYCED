<template>
  <div class="w-full bg-purple-500 text-white h-56" style="font-family:'Quantico'">

    <div class="inline-flex w-1/2 items-center justify-center my-6 space-x-16">
      <div class="flex items-center justify-center">
        <img src="@/assets/logo_underpryced_white.png" class="h-10 cursor-pointer" @click="$router.push('/')"/>
        <h1 class="text-3xl ml-4 cursor-pointer font-semibold"
            @click="$router.push('/')">
          UNDERPRYCED
        </h1>
      </div>
      <div class="block">
        <p class="opacity-60 block mt-2 cursor-pointer">Sobre nosotros</p>
        <a class="opacity-60 block mt-2 cursor-pointer">Contacto</a>
        <a class="opacity-60 block mt-2 cursor-pointer">Información sobre envíos</a>
        <a class="opacity-60 block mt-2 cursor-pointer">Política de Privacidad</a>
        <div class="flex flex-row text-lg space-x-4 justify-center mt-5">
          <i class="fab fa-facebook-f cursor-pointer"></i>
          <i class="fab fa-twitter cursor-pointer"></i>
          <i class="fab fa-instagram cursor-pointer"></i>
          <i class="fas fa-envelope cursor-pointer"></i>
          <i class="fas fa-phone-alt cursor-pointer"></i>
        </div>
      </div>
    </div>


  </div>
</template>

<script>
export default {
  name: "Footer"
}
</script>

<style scoped>

</style>