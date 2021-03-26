<template>
  <div id="app">
    <NavBar></NavBar>
<!--    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link> |
      <router-link to="/registro">Registro</router-link>
    </div>-->

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
</style>

<script>
import Footer from '@/components/Footer.vue'
import NavBar from '@/components/NavBar.vue'
export default {
  name: "App",
  components: {
    Footer,
    NavBar
  }
}
</script>