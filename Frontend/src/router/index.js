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
            requiresAuth: true,
            userAuth: true
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
            requiresAuth: true,
            userAuth: true
        }
    },
    {
        path: '/datosUsuario',
        name: 'DatosUsuario',
        component: function () {
            return import('../views/UserData.vue')
        },
        meta: {
            requiresAuth: true,
            userAuth: true
        }
    },
    {
        path: '/metodosPago',
        name: 'MetodosPago',
        component: function () {
            return import('../views/PaymentMethods.vue')
        },
        meta: {
            requiresAuth: true,
            userAuth: true
        }
    },
    {
        path: '/alertas',
        name: 'Alertas',
        component: function () {
            return import('../views/Alerts.vue')
        },
        meta: {
            requiresAuth: true,
            userAuth: true
        }
    },
    {
        path: '/historial',
        name: 'Historial',
        component: function () {
            return import('../views/History.vue')
        },
        meta: {
            requiresAuth: true,
            userAuth: true
        }
    },
    {
        path: '/pedido/:ref',
        name: 'Pedido',
        component: function () {
            return import('../views/OrderInfo.vue')
        },
        meta: {
            requiresAuth: true,
            userAuth: true
        }
    },
    {
        path: '/adminPedido/:ref',
        name: 'AdminPedido',
        component: function () {
            return import('../views/AdminOrderInfo.vue')
        },
        meta: {
            requiresAuth: true,
            adminAuth: true
        }
    },
    {
        path: '/aprobarPedidos',
        name: 'AprobarPedidos',
        component: function () {
            return import('../views/CheckOrder.vue')
        },
        meta: {
            requiresAuth: true,
            adminAuth: true
        }
    },
    {
        path: '/buscar/:title?',
        name: 'Buscar',
        component: function () {
            return import('../views/Search.vue')
        },
        meta: {
            requiresAuth: true,
            userAuth: true
        }
    },
    {
        path: '/comprar/:ref/:size',
        name: 'Comprar',
        component: function () {
            return import('../views/Buy.vue')
        },
        meta: {
            requiresAuth: true,
            userAuth: true
        }
    },
    {
        path: '/vender/:ref/:size',
        name: 'Vender',
        component: function () {
            return import('../views/Sell.vue')
        },
        meta: {
            requiresAuth: true,
            userAuth: true
        }
    },
    {
        path: '/confirmarCompra/:ref/:size',
        name: 'ConfirmarCompra',
        component: function () {
            return import('../views/ConfirmBuy.vue')
        },
        meta: {
            requiresAuth: true,
            userAuth: true
        }
    },
    {
        path: '/confirmarVenta/:ref/:size',
        name: 'ConfirmarVenta',
        component: function () {
            return import('../views/ConfirmSell.vue')
        },
        meta: {
            requiresAuth: true,
            userAuth: true
        }
    },
    {
        path: '/inicioAdmin',
        name: 'InicioAdmin',
        component: function () {
            return import('../views/AdminHome.vue')
        },
        meta: {
            requiresAuth: true,
            adminAuth: true
        }
    },
    {
        path: '/crearProducto',
        name: 'CrearProducto',
        component: function () {
            return import('../views/AddProduct.vue')
        },
        meta: {
            requiresAuth: true,
            adminAuth: true
        }
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    if(to.meta.requiresAuth) {
        if(!store.getters.isLoggedIn) {
            next("/iniciarSesion")
        }
        else if(to.meta.userAuth) {
            var role = store.getters.authRole;
            if(role === "ROLE_USER") {
                next()
            }else {
                next('/inicioAdmin')
            }
        } else if(to.meta.adminAuth) {
            const role = store.getters.authRole;
            if(role === 'ROLE_ADMIN') {
                next()
            }else {
                next('/')
            }
        }
        else{
            next()
        }
    }
    else if(to.meta.hideForAuth){
        if(!store.getters.isLoggedIn) {
            next()
        }
        else{
            const role = store.getters.authRole
            if(role === "ROLE_ADMIN"){
                next('/inicioAdmin')
            }
            else if(role === "ROLE_USER"){
                next("/")
            }
            else{
                next()
            }
        }
    }
    else {
        next()
    }
})

export default router
