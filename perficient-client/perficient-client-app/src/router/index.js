import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import AllEmployees from '../components/AllEmployees.vue'
import Employee from '../components/Employee.vue'
import UpdateEmployee from '../components/UpdateEmployee.vue'
import CreateEmployee from '../components/CreateEmployee.vue'



Vue.use(VueRouter)

const routes = [
  {
    path: '/home',
    name: 'Home',
    component: Home
  }, 
  {
    path: '/employees',
    name: 'all-employees',
    component: AllEmployees
  },
  {
    path: '/employees',
    name: 'create-employee',
    component: CreateEmployee
  },  

  {
    path: '/employees/:id',
    name: 'viewemployee',
    component: Employee
  },
  {
    path: '/employees/:id',
    name: 'update',
    component: UpdateEmployee 
  },
  
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: About
  },
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
