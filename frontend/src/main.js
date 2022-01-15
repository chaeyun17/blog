import Vue from 'vue'
import App from './App.vue'
import Axios from 'axios'
import router from './router/router'
// import VueAxios from 'vue-axios'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

const dotenv = require('dotenv')
dotenv.config()
console.log(process.env)
const axiosConfig = {
  baseURL: process.env.VUE_APP_HOST + '/api/v1',
  timeout: 30000,
};
Vue.prototype.$http = Axios.create(axiosConfig);

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)


