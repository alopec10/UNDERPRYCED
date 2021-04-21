import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import store from '../store/index'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: '/registro',
        name: 'Registro',
        component: function () {
            return import('../views/Registro.vue')
        },
        meta: {
            hideForAuth: true
        }
    },
    {
        path: '/iniciarsesion',
        name: 'IniciarSesion',
        component: function () {
            return import('../views/Login.vue')
        },
        meta: {
            hideForAuth: true
        }
    },
    {
        path: '/producto/:ref',
        name: 'Producto',
        component: function () {
            return import('../views/Product.vue')
        },
        meta: {
            requiresAuth: true
        }
    },
    {
        path: '/datosUsuario',
        name: 'DatosUsuario',
        component: function () {
            return import('../views/UserData.vue')
        },
        meta: {
            requiresAuth: true
        }
    },
    {
        path: '/metodosPago',
        name: 'MetodosPago',
        component: function () {
            return import('../views/PaymentMethods.vue')
        },
        meta: {
            requiresAuth: true
        }
    },
    {
        path: '/historial',
        name: 'Historial',
        component: function () {
            return import('../views/History.vue')
        },
        meta: {
            requiresAuth: true
        }

    },
    {
        path: '/comprar/:ref/:size',
        name: 'Comprar',
        component: function () {
            return import('../views/Buy.vue')
        },
        meta: {
            requiresAuth: true
        }
    },
    {
        path: '/vender/:ref/:size',
        name: 'Vender',
        component: function () {
            return import('../views/Sell.vue')
        },
        meta: {
            requiresAuth: true
        }
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!store.getters.isLoggedIn) {
      next({ path: '/iniciarsesion' });
    } else {
      next();
    }

  } else {
    next();
  }

  if (to.matched.some(record => record.meta.hideForAuth)) {
    if (store.getters.isLoggedIn) {
      next({ path: '/' });
    } else {
      next();
    }
  } else {
    next();
  }
})

export default router
