<template>
  <div>
    <div class="relative w-full sq h-64 rounded-md mx-auto overflow-hidden bg-cover bg-center text-left cursor-pointer"
         :style="bgUrl"
         @click="pushBrand(brand)"
    >
      <div class="bg-gray-900 bg-opacity-70 flex items-center h-full">
        <div class="px-10 max-w-xl">
          <h2 class="text-white font-semibold text-4xl md:text-3xl 2xl:text-4xl" style="font-family:'Quantico'">{{ brand }}</h2>

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
  },
  methods: {
    pushBrand(br) {
      console.log(br)
      this.$router.push({
        name: "Buscar",
        params: {
          title: br,
        }
      })
    }
  }
}
</script>

<style scoped>
@tailwind screens;

@screen lg {
  .sq {
    height: max(15vw, 200px);
  }
}
.cent {
  display: flex;
  align-items: center;
  padding: 5px;
}

</style>