import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Citizen from '@/components/Citizen'
import Reservation from '@/components/Reservation'
import HeadLibrarian from '@/components/HeadLibrarian'
import Librarian from '@/components/Librarian'
import Login from '@/components/Login'

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
      path: '/headlibrarian',
      name: 'HeadLibrarian',
      component: HeadLibrarian
    },
    {
      path: '/librarian',
      name: 'Librarian',
      component: Librarian
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
})
