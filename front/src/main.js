import Vue from 'vue'
import App from './App.vue'
// import axios from 'axios'
// import VueAxios from 'vue-axios'
import Meta from 'vue-meta';

Vue.use(Meta);


Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')
