<template>
  <div>
  <div class="relative w-full sq h-64 rounded-md mx-auto overflow-hidden bg-cover bg-center text-left"
       :style="bgUrl">
    <div class="bg-gray-900 bg-opacity-70 flex items-center h-full">
      <div class="px-10 max-w-xl">
        <h2 class="text-white font-semibold text-3xl md:text-2xl 2xl:text-3xl">{{ brand }}</h2>
        <p class="mt-2 text-gray-400 hidden">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Tempore facere
          provident molestias ipsam sint voluptatum pariatur.</p>
        <button
            class="flex items-center mt-4 text-white text-sm uppercase font-medium rounded hover:underline focus:outline-none">
          <span>Shop Now</span>
          <svg class="h-5 w-5 mx-2" fill="none" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
               viewBox="0 0 24 24" stroke="currentColor">
            <path d="M17 8l4 4m0 0l-4 4m4-4H3"></path>
          </svg>
        </button>
      </div>
      <div class="absolute bottom-0 right-8 w-14 h-14 bg-white cent">
        <div class="">
          <img class="" :src="logoUrl"/>
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
export default {
  name: "BrandCard",
  props: {
    brand: {
      type: String,
      required: true
    },
    bgUrl: {
      type: String,
      required: true
    },
    logoUrl: {
      type: String,
      required: true
    }
  }
}
</script>

<style scoped>
@tailwind screens;

@screen lg{
  .sq {
    height:max(15vw, 200px);
  }
}
 .cent {
   display: flex;
   align-items: center;
   padding: 5px;
 }

</style>