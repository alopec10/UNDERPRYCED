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
    path: '/producto/:ref',
    name: 'Producto',
    component: function () {
      return import('../views/Product.vue')
    },
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

  },
  {
    path: '/comprar/:ref/:size',
    name: 'Comprar',
    component: function () {
      return import('../views/Buy.vue')
    },
  },
  {
    path: '/vender/:ref/:size',
    name: 'Vender',
    component: function () {
      return import('../views/Sell.vue')
    },

  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
