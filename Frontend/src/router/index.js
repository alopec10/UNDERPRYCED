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
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
