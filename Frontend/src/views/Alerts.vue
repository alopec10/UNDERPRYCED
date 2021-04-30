<template>
  <div class="">
    <h1 class="text-5xl mt-6">
      NOTIFICACIONES
    </h1>
    <div class="my-10 mx-auto">
      <div v-if="alerts.length===0">
        <h1 class="text-2xl">¡No tienes notificaciones!</h1>
        <h1 class="text-xl mt-5">Explora nuestros productos</h1>
      </div>
      <div v-else>
        <Alert v-for="(al, index) in alerts" :key="al.idAlert"
               :alert="al"
               class="my-4"
        v-on:markAsRead="markAsRead">
        </Alert>

      </div>

    </div>
  </div>

</template>

<script>
import Alert from "@/components/Alert.vue"

const axios = require("axios");

export default {
  name: "Alerts",
  components: {
    Alert
  },
  data() {
    return {
      alerts: []
    }
  },
  created() {
    this.getAlerts()
  },
  methods: {
    getAlerts() {
      axios({url: 'http://localhost:8888/alert/getAll', method: 'GET'})
          .then(resp => {
            console.log(resp.data)
            this.alerts = resp.data
          })
          .catch(err => {
          })

    },
    markAsRead(id) {
      for(let al of this.alerts) {
        if (al.idAlert === id) {
          al.read = true
        }
      }
    }
  }
}
</script>

<style scoped>

</style>