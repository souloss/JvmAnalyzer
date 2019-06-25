import Vue from 'vue'
import App from './App.vue'
import './plugins/axios'
import './plugins/element.js'
import './plugins/icons.js'
import '@/styles/index.scss' // global css
import router from './router'

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

