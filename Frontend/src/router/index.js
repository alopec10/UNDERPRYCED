import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: function () {
      return import('../views/About.vue')
    }
  },
  {
    path: '/registro',
    name: 'Registro',
    component: function () {
      return import('../views/Registro.vue')
    }
  },
  {
    path: '/iniciarsesion',
    name: 'IniciarSesion',
    component: function () {
      return import('../views/Login.vue')
    }
  },
  {
    path: '/producto',
    name: 'Producto',
    component: function () {
      return import('../views/Product.vue')
    },
    props: {
      title: "LEBRON XVIII CHRISTMAS PARTY",
      url: "https://images.stockx.com/images/Nike-Lebron-18-X-Mas-in-LA.png?fit=fill&bg=FFFFFF&w=300&h=214&auto=format,compress&trim=color&q=90&dpr=2&updated_at=1609203421",
      rel_date: "27/05/2021",
      retail_price: '177$'
    }
  },
  {
    path: '/datosUsuario',
    name: 'DatosUsuario',
    component: function () {
      return import('../views/UserData.vue')
    }
  },
  {
    path: '/metodosPago',
    name: 'MetodosPago',
    component: function () {
      return import('../views/PaymentMethods.vue')
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
