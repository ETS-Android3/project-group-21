import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Citizen from '@/components/Citizen'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/citizen',
      name: 'Citizen',
      component: Citizen
    }
  ]
})
