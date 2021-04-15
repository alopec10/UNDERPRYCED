<template>
  <div class="cursor-pointer w-full sq mx-auto rounded-md shadow-lg overflow-hidden border border-gray-300"
  @click="pushProduct">

    <img class="" style="max-width:90%;max-height:62%; margin: 4px auto" :src="url"/>
    <div class="px-5">
      <h1 class="text-gray-900 uppercase leading-tight line-clamp-2" :title="title" style="font-size: max(1.5vw, 15px)">{{ title }}</h1>
      <span class="text-gray-500 mt-2">${{ price }}</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "SmallCard",
  props: {
    title: {
      type: String,
      required: true
    },
    url: {
      type: String,
      required: true
    },
    price: {
      type: Number,
      required: true
    },
    refer: {
      type: String,
      required: true
    },
  },
  mounted() {
    console.log(this.refer)
  },
  methods: {
    pushProduct() {
      console.log("a")
      this.$router.push({
        name: "Producto",
        params:{
          ref: this.refer,
        }
      });
    }
  }

}

</script>

<style scoped>
@tailwind screens;

@screen sm{
  .sq {
    width: 285px; height: 285px;
  }
}

@screen md{
  .sq {
    width: min(30vw, 250px); height:min(30vw, 250px);
  }
}

@screen lg{
  .sq {
    width: max(18vw, 230px); height:max(18vw, 230px);
  }
}

@screen xl{
  .sq {
    width: max(16vw, 230px); height:max(16vw, 230px);
  }
}

@screen 2xl{
  .sq {
    width: max(15vw, 250px); height:max(15vw, 250px);
  }
}

</style>