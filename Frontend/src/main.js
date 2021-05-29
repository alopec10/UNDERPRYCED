import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import "@/assets/css/tailwind.css";
import Axios from 'axios'
import VueTailwindModal from "vue-tailwind-modal"
import Notifications from 'vue-notification'

Vue.component("VueTailwindModal", VueTailwindModal)
Vue.use(Notifications)


Vue.prototype.$http = Axios;
const token = localStorage.getItem('token')
if (token) {
  Vue.prototype.$http.defaults.headers.common['Authorization'] = token
}

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: function (h) { return h(App) }
}).$mount('#app')
