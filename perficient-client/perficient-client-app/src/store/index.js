import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    allEmployees: [],
    employee:{
      id: '',
      firstName:'',
      lastname:'',
      address:{
        id: '',
        street: '',
        suite: '',
        city: '',
        region:'',
        postal:'',
        country:''
      },
      contactEmail: '',
      comaonyEmail: '',
      

    }
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
