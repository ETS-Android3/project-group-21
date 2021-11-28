import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Citizen from '@/components/Citizen'
import Reservation from '@/components/Reservation'
import LibraryItem from '@/components/LibraryItem'
import HeadLibrarian from '@/components/HeadLibrarian'
import Librarian from '@/components/Librarian'
import Login from '@/components/Login'
import OpeningHour from '@/components/OpeningHour'
import HeadLibrarianHome from '@/components/HeadLibrarianHome'
import LibrarianHome from '@/components/LibrarianHome'
import ItemList from '@/components/ItemList'

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
      path: '/itemlist',
      name: 'ItemList',
      component: ItemList
    },
    {
      path: '/libraryitem',
      name: 'LibraryItem',
      component: LibraryItem
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
    },
    {
      path: '/openinghour',
      name: 'OpeningHour',
      component: OpeningHour
    },
    {
      path: '/headlibrarianhome',
      name: 'HeadLibrarianHome',
      component: HeadLibrarianHome
    },
    {
      path: '/librarianhome',
      name: 'LibrarianHome',
      component: LibrarianHome
    }
  ]
})
