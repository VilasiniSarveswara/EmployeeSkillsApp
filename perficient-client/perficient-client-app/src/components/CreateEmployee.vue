<template>
  <div>
    <h1>Add a New Employee</h1>
    <form >
      <div class="important">
        Choose you skills first and then add other details! Scroll to the bottom of the page. <br><br>
      </div>
      
     Firstname*: <input type="text" v-model="employee.firstName"/><br><br>
          Lastname*: <input type="text" v-model="employee.lastName"/>  <br><br>
           <div id="address"> 
               Address: <br>
          Street*: <input type="text" v-model="employee.address.street" /> <br><br>
          Suite: <input type="text" v-model="employee.address.suite" /> <br><br>
          City: <input type="text" v-model="employee.address.city" /> <br><br>
          Region:[2 character] <input type="text" v-model="employee.address.region" /> <br><br>
          Postal: <input type="text" v-model="employee.address.postal" /> <br><br>
          Country:[2 character] <input type="text" v-model="employee.address.region" /> <br><br>
          </div>
          Contact Email: <input type="text" v-model="employee.contactEmail" /> <br><br>
          Company Email: <input type="text" v-model="employee.companyEmail" /> <br><br>
          Birthdate:[yyyy-mm--dd] <input type="text" v-model="employee.birthDate" /> <br><br>
          Hired Date: [yyyy-mm--dd]<input type="text" v-model="employee.hiredDate" /> <br><br>
          Role:['Technical Consultant','Project Manager'] <input type="text" v-model="employee.role" /> <br><br>
          Business Unit: <input type="text" v-model="employee.businessUnit" /> <br><br>
          
          Assigned To: <input type="text" v-model="employee.assignedTo" /> <br><br>  
          
          Skills:
          <router-link v-bind:to="{name: 'fields'}">Choose Your fields of experience</router-link>
          Field Id: <input type="text" v-model="employee.skills.id">
          Experience(in months): <input type="text" v-model="employee.skills.experience">
          Summary: <input type="text" v-model="employee.skills.summary">
          
        <input type="submit" v-on:subit.prevent="createEmployee">
    </form>
  </div>
 
</template>

<script>
import EmployeeServices from '../services/EmployeeServices.js'
export default {  
  name:'create-employee',
  props:{
    skills:[]
  },
  data(){
    return {
      employee:{
        "address": {},
        "skills":[]
      }
    }
  },
  methods:{
    createEmployee(){
      EmployeeServices.createEmployee(employee).then(response =>{
        this.$router.push({name: 'all-employees'})
      })
    }

  }
  

}
</script>

<style>
.important{
  color: red;
}
</style>