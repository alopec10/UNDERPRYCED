<template>
  <header class="lg:px-16 px-6 bg-white flex flex-wrap items-center lg:py-0 py-2">
    <div class="flex-1 flex justify-between items-center">
      <a href="#">
        <svg width="32" height="36" viewBox="0 0 32 36" xmlns="http://www.w3.org/2000/svg">
          <path
              d="M15.922 35.798c-.946 0-1.852-.228-2.549-.638l-10.825-6.379c-1.428-.843-2.549-2.82-2.549-4.501v-12.762c0-1.681 1.12-3.663 2.549-4.501l10.825-6.379c.696-.41 1.602-.638 2.549-.638.946 0 1.852.228 2.549.638l10.825 6.379c1.428.843 2.549 2.82 2.549 4.501v12.762c0 1.681-1.12 3.663-2.549 4.501l-10.825 6.379c-.696.41-1.602.638-2.549.638zm0-33.474c-.545 0-1.058.118-1.406.323l-10.825 6.383c-.737.433-1.406 1.617-1.406 2.488v12.762c0 .866.67 2.05 1.406 2.488l10.825 6.379c.348.205.862.323 1.406.323.545 0 1.058-.118 1.406-.323l10.825-6.383c.737-.433 1.406-1.617 1.406-2.488v-12.757c0-.866-.67-2.05-1.406-2.488l-10.825-6.379c-.348-.21-.862-.328-1.406-.328zM26.024 13.104l-7.205 13.258-3.053-5.777-3.071 5.777-7.187-13.258h4.343l2.803 5.189 3.107-5.832 3.089 5.832 2.821-5.189h4.352z"></path>
        </svg>
      </a>
    </div>

    <div class="relative mt-1" v-if="authRole === 'ROLE_USER'">
      <input type="text" id="" placeholder="Buscar..." v-model="searchString" v-on:keyup.enter="search"
             class="h-12 uppercase text-center w-full pl-3 pr-10 py-2 border-2 border-gray-200 rounded-xl hover:border-purple-200 focus:outline-none focus:border-purple-500 transition-colors">
      <button
          @click="search"
          class="block w-7 h-7 text-center text-xl leading-0 absolute top-2.5 right-3 text-purple-400 focus:outline-none hover:text-purple-900 transition-colors">
        <i class="fas fa-search"></i>
      </button>
    </div>

    <div class="hidden lg:flex lg:items-center lg:w-auto w-full" id="menu">
      <nav>
        <ul class="lg:flex items-center justify-between text-base text-gray-700 pt-4 lg:pt-0">
          <li v-if="authRole"><a class="lg:p-4 py-3 px-0 block border-b-2 border-transparent hover:border-indigo-400"
                 href="/">Inicio</a></li>
          <li v-if="!authRole"><a class="lg:p-4 py-3 px-0 block border-b-2 border-transparent hover:border-indigo-400"
                 href="/iniciarsesion">Login</a></li>
          <li v-if="!authRole"><a class="lg:p-4 py-3 px-0 block border-b-2 border-transparent hover:border-indigo-400" href="/registro">Registro</a>
          </li>
          <li v-if="authRole === 'ROLE_USER'"><a class="lg:p-4 py-3 px-0 block border-b-2 border-transparent hover:border-indigo-400"
                 href="/datosUsuario">Datos usuario</a></li>
          <li v-if="authRole === 'ROLE_USER'"><a class="lg:p-4 py-3 px-0 block border-b-2 border-transparent hover:border-indigo-400"
                 href="/metodosPago">Metodos pago</a></li>
          <li v-if="authRole === 'ROLE_USER'"><a class="lg:p-4 py-3 px-0 block border-b-2 border-transparent hover:border-indigo-400" href="/historial">Historial</a>
          </li>
          <li v-if="authRole === 'ROLE_USER'"><a class="lg:p-4 py-3 px-0 block border-b-2 border-transparent hover:border-indigo-400" href="/alertas">Alertas</a>
          </li>
          <li v-if="authRole === 'ROLE_ADMIN'"><a class="lg:p-4 py-3 px-0 block border-b-2 border-transparent hover:border-indigo-400" href="/crearProducto">Crear producto</a>
          </li>
          <li v-if="authRole === 'ROLE_ADMIN'"><a class="lg:p-4 py-3 px-0 block border-b-2 border-transparent hover:border-indigo-400" href="/aprobarPedidos">Aprobar pedidos</a>
          </li>
        </ul>
      </nav>
      <a v-if="authRole" @click='logout' href="#" class="lg:ml-4 flex items-center justify-start lg:mb-0 mb-4 pointer-cursor">
        <img class="rounded-full w-10 h-10 border-2 border-transparent hover:border-indigo-400"
             src="https://www.personality-database.com/profile_images/226754.png?id=139034" alt="Andy Leverenz">
      </a>

    </div>

  </header>
</template>

<script>

export default {
  name: "NavBar",
  data() {
    return {
      searchString: ""
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
      this.$router.go(0);
    },
    isBlank(str) {
      return (!str || /^\s*$/.test(str));
    }
  },
}
</script>

<style scoped>

</style>