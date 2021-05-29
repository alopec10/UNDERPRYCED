<template>
  <div id="app" class="flex flex-col h-screen justify-between">
    <NavBar></NavBar>
<!--    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link> |
      <router-link to="/registro">Registro</router-link>
    </div>-->
    <notifications group="err"
                   position="top center"
                   :speed="500"
                   :ignoreDuplicates="true"/>
    <router-view/>
    <Footer></Footer>
  </div>
</template>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}

#footer {
  position: fixed;
  bottom:0%;
  width:100%;
}

.vue-notification {
  border-radius: 5px;
}

.notification-title {
  font-family: 'Quantico' !important;
  text-transform: uppercase !important;
  font-size: 1.5em !important
}

.notification-content {
  font-family: 'Quantico' !important;
  text-transform: uppercase !important;
  font-size: 1.2em !important
}

</style>

<script>
import Footer from '@/components/Footer.vue'
import NavBar from '@/components/NavBar.vue'

import { mapActions } from 'vuex'
export default {
  name: "App",
  components: {
    Footer,
    NavBar
  },
  created() {
    let self = this
    this.$http.interceptors.response.use(undefined, function (err) {
      return new Promise(function (resolve, reject) {
        if (err.response.status === 403 && err.config && !err.config.__isRetryRequest) {
          self.logout()
          self.$router.push('/iniciarsesion')
        }
        throw err;
      });
    });
  },
  methods: {
    ...mapActions(['logout'])
  }
}
</script>