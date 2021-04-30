<template>
  <div class="border-2 border-purple-500 rounded-lg width mx-auto flex" v-bind:class="{ 'bg-purple-100' : alert.read }">
    <div class="my-3 mx-3 w-11/12">
      <h1 class="text-2xl">{{alert.title}}</h1>
      <h1 class="text-md mt-3" v-for="(msg, index) in msgs" :key="index">
        {{msg}}
      </h1>
    </div>
    <div class="flex justify-center items-center align-middle text-purple-500 w-1/12">
      <i v-if="!alert.read" class="fas fa-check block text-2xl mr-1 cursor-pointer" @click="markAsRead"></i>
      <i v-else class="fas fa-eye block text-2xl mr-1"></i>

    </div>
  </div>
</template>

<script>
const axios = require("axios");
export default {
  name: "Alert",
  props: {
    alert: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      msgs: []
    }
  },
  created() {
    this.msgs = this.alert.message.split("\n")
  },
  methods: {
    markAsRead() {
      let url = "http://localhost:8888/alert/markAsRead?id="+this.alert.idAlert

      axios({url: url, method: 'GET'})
          .then(resp => {
            this.$emit("markAsRead", this.alert.idAlert)
          })
          .catch(err => {
          })

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