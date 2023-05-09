import { createApp } from 'vue'
import App from './App.vue'
import VueOverflowScroll from 'vue-overflow-scroll'
import router from './router'
import store from './store'

createApp(App).use(VueOverflowScroll).use(router).use(store).mount('#app')
