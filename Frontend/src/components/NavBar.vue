<template>
  <header class="lg:px-16 px-6 bg-white flex flex-wrap items-center border-b-2 border-purple-900 lg:py-0 py-2">
    <div class="flex-1 flex justify items-center ">

      <img src="@/assets/logo_underpryced.png" class="h-10 cursor-pointer" @click="$router.push('/')"/>

      <h1 class="text-3xl ml-4 cursor-pointer font-semibold" style="font-family:'Quantico'" @click="$router.push('/')">
        UNDERPRYCED
      </h1>
    </div>

    <div class="relative mt-1 mr-5" v-if="authRole === 'ROLE_USER'">
      <input type="text" id="" placeholder="Buscar..." v-model="searchString" v-on:keyup.enter="search"
             class="h-10 uppercase text-center w-64 pl-3 pr-10 py-2 border-b border-purple-200 hover:border-purple-200 focus:outline-none focus:border-purple-500 transition-colors">
      <button
          @click="search"
          class="block w-7 h-7 text-center text-xl leading-0 absolute top-1 right-3 text-purple-700 focus:outline-none hover:text-purple-900 transition-colors">
        <i class="fas fa-search"></i>
      </button>
    </div>

    <div class="hidden lg:flex lg:items-center lg:w-auto w-full" id="menu">
      <nav>
        <ul class="lg:flex items-center justify-between text-base pt-4 lg:pt-0">
          <li v-if="authRole"><a
              class="lg:p-4 lg:pt-5 py-3 pt-4 px-0 block border-b-2 border-transparent hover:border-purple-900"
              style="font-family:'Quantico'"
              href="/">INICIO</a></li>
          <li v-if="!authRole"><a
              class="lg:p-4 lg:pt-5 py-3 px-0 block border-b-2 border-transparent hover:border-purple-900"
              style="font-family:'Quantico'"
              href="/iniciarsesion">LOGIN</a></li>
          <li v-if="!authRole"><a
              class="lg:p-4 lg:pt-5 py-3 px-0 block border-b-2 border-transparent hover:border-purple-900"
              style="font-family:'Quantico'"
              href="/registro">REGISTRO</a>
          </li>
          <li v-if="authRole === 'ROLE_USER'"><a
              class="lg:p-4 lg:pt-5 py-3 px-0 block border-b-2 border-transparent hover:border-purple-900"
              style="font-family:'Quantico'"
              href="/datosUsuario">DATOS USUARIO</a></li>
          <li v-if="authRole === 'ROLE_USER'"><a
              class="lg:p-4 lg:pt-5 py-3 px-0 block border-b-2 border-transparent hover:border-purple-900"
              style="font-family:'Quantico'"
              href="/metodosPago">METODOS PAGO</a></li>
          <li v-if="authRole === 'ROLE_USER'"><a
              class="lg:p-4 lg:pt-5 py-3 px-0 block border-b-2 border-transparent hover:border-purple-900"
              style="font-family:'Quantico'"
              href="/historial">HISTORIAL</a>
          </li>
          <li v-if="authRole === 'ROLE_USER'"><a
              class="lg:p-4 lg:pt-5 py-3 px-0 block border-b-2 border-transparent hover:border-purple-900"
              style="font-family:'Quantico'"
              href="/alertas">ALERTAS</a>
          </li>
          <li v-if="authRole === 'ROLE_ADMIN'"><a
              class="lg:p-4 lg:pt-5 py-3 px-0 block border-b-2 border-transparent hover:border-purple-900"
              style="font-family:'Quantico'"
              href="/crearProducto">CREAR PRODUCTO</a>
          </li>
          <li v-if="authRole === 'ROLE_ADMIN'"><a
              class="lg:p-4 lg:pt-5 py-3 px-0 block border-b-2 border-transparent hover:border-purple-900"
              style="font-family:'Quantico'"
              href="/aprobarPedidos">APROBAR PEDIDOS</a>
          </li>
        </ul>
      </nav>
      <i v-if="authRole" @click='logout'
         class="flex items-center justify-start cursor-pointer text-2xl fas fa-power-off ml-4 text-purple-900">
      </i>


    </div>

  </header>
</template>

<script>

export default {
  name: "NavBar",
  data() {
    return {
      searchString: "",
    }
  },
  computed: {
    isLoggedIn() {
      return this.$store.getters.isLoggedIn
    },
    authRole() {
      return this.$store.getters.authRole
    }
  },
  methods: {
    logout: function () {
      this.$store.dispatch('logout')
          .then(() => {
            //this.$router.push('/iniciarsesion')
            this.$router.go(0)
          })
    },
    search() {
      if (this.isBlank(this.searchString)) {
        this.$router.push({
          name: "Buscar",
        });
      } else {
        this.$router.push({
          name: "Buscar",
          params: {
            title: this.searchString,
          }
        });
      }
      this.searchString = ""
    },
    isBlank(str) {
      return (!str || /^\s*$/.test(str));
    }
  },
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Quantico&display=swap');
</style>