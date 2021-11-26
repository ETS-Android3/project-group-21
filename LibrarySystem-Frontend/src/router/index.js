import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Citizen from '@/components/Citizen'
import Reservation from '@/components/Reservation'
import LibraryItem from '@/components/LibraryItem'

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
    },
    {
      path: '/reservation',
      name: 'Reservation',
      component: Reservation
    },
    {
      path: '/libraryitem',
      name: 'LibraryItem',
      component: LibraryItem
    }
  ]
})
