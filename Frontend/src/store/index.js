import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import createPersistedState from "vuex-persistedstate";


Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        status: '',
        token: localStorage.getItem('token') || '',
        role: undefined,
        user: {}
    },
    mutations: {
        auth_request(state) {
            state.status = 'loading'
        },
        auth_success(state, payload) {
            state.status = 'success'
            state.token = payload.token
            state.role = payload.role
        },
        auth_error(state) {
            state.status = 'error'
        },
        logout(state) {
            state.status = ''
            state.token = ''
            state.role = undefined
        },
    },
    actions: {
        login({commit}, user) {
            return new Promise((resolve, reject) => {
                commit('auth_request')
                axios({url: 'http://localhost:8888/user/authenticate', data: user, method: 'POST'})
                    .then(resp => {
                        const token = 'Bearer ' + resp.data.jwt
                        const payload = {
                            token: 'Bearer ' + resp.data.jwt,
                            role : resp.data.role
                        }
                        localStorage.setItem('token', token)
                        axios.defaults.headers.common['Authorization'] = token
                        commit('auth_success', payload)
                        resolve(resp)
                    })
                    .catch(err => {
                        commit('auth_error')
                        localStorage.removeItem('token')
                        reject(err)
                    })
            })
        },
        register({commit}, user) {
            return new Promise((resolve, reject) => {
                commit('auth_request')
                axios({url: 'http://localhost:8888/user/register', data: user, method: 'POST'})
                    .then(resp => {
                        const token = 'Bearer ' + resp.data.jwt
                        const payload = {
                            token: 'Bearer ' + resp.data.jwt,
                            role : resp.data.role
                        }
                        localStorage.setItem('token', token)
                        axios.defaults.headers.common['Authorization'] = token
                        commit('auth_success', payload)
                        resolve(resp)
                    })
                    .catch(err => {
                        commit('auth_error', err)
                        localStorage.removeItem('token')
                        reject(err)
                    })
            })
        },
        logout({commit}) {
            return new Promise((resolve, reject) => {
                commit('logout')
                localStorage.removeItem('token')
                delete axios.defaults.headers.common['Authorization']
                resolve()
            })
        }
    },
    modules: {},
    getters: {
        isLoggedIn: state => !!state.token,
        authStatus: state => state.status,
        authRole: state => state.role,
    },
    plugins: [createPersistedState()],
})
