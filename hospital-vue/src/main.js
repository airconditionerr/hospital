// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import Axios from 'axios'
import App from './App'
import router from './router'
import 'element-ui/lib/theme-chalk/index.css'

Vue.config.productionTip = false

Vue.use(ElementUI)
Vue.prototype.$axios = Axios

let protocol = window.location.protocol
let host = window.location.host
let reg = /^localhost+/
if (reg.test(host)) {
  Axios.defaults.baseURL = protocol + '//' + host + '/api'
} else {
  Axios.defaults.baseURL = protocol + '//' + host
}
Vue.prototype.baseUrl = Axios.defaults.baseURL
Axios.defaults.withCredentials = true // 让ajax携带cookie

// Axios.interceptors.request.use(config => {
//   if (store.state.token) {
//     config.headers.common['/loginByUsername'] = store.state.token
//   }
//   return config
// })

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  // store,
  components: {App},
  template: '<App/>'
})
