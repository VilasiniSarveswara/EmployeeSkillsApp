<template>
  <div class="updateEmployee">

      <h1>Update Employee</h1>
      <div>
          <img src="../assets/ping_pong_loader.gif" v-if="isLoading" >
      </div>
     

      <form v-on:submit.prevent="updateEmployee">         
          Employee ID: {{employee.id}} <br><br>
          Firstname*: <input type="text" v-model="employee.firstName"/><br><br>
          Lastname*: <input type="text" v-model="employee.lastName"/>  <br><br>
           <div id="address">
               Address: <br>
          Street*: <input type="text" v-model="employee.address.street" /> <br><br>
          Suite: <input type="text" v-model="employee.address.suite" /> <br><br>
          City: <input type="text" v-model="employee.address.city" /> <br><br>
          Region: <input type="text" v-model="employee.address.region" /> <br><br>
          Postal: <input type="text" v-model="employee.address.postal" /> <br><br>
          Country: <input type="text" v-model="employee.address.region" /> <br><br>
          </div>
          Contact Email: <input type="text" v-model="employee.contactEmail" /> <br><br>
          Company Email: <input type="text" v-model="employee.companyEmail" /> <br><br>
          Birthdate: <input type="text" v-model="employee.birthDate" /> <br><br>
          Hired Date: <input type="text" v-model="employee.hiredDate" /> <br><br>
          Role: <input type="text" v-model="employee.role" /> <br><br>
          Business Unit: <input type="text" v-model="employee.businessUnit" /> <br><br>
          
          Assigned To: <input type="text" v-model="employee.assignedTo" /> <br><br>  
          
          Skills: <br><br>
          <div class="skills" v-for="skill in skills" v-bind:key="skill.id">
            Skill Id: {{skills.id}}
            <div class="fields">
                 Field: {{skills.field.id}}
                Name: {{skill.field.name}}
                Type: {{skill.field.type}}
            </div>           
            <input type="text" v-model="skill.experience">
            <input v-model="skill.summary">
        </div>
        
 
        <input type="submit">
      </form>
    <button v-on:click.prevent="deleteEmployee">Delete Employee</button>
  </div>
</template>

<script>
import EmployeeServices from '../services/EmployeeServices.js'
export default {
    isLoading: true,
    data(){
        return{
            employee: {},
            address: {},
            skills:[]
            
        }
    },

    created(){ 
        this.isLoading = false
        const employeeId = this.$route.params.id
        EmployeeServices.listEmployee(employeeId).then(response => {
            this.employee = response.data
           // this.address = response.data.address
            this.skills = response.data.skills
        })

    },
     methods:{
         updateEmployee(){
             EmployeeServices.updateEmployee(employeeId,this.employee).then(response =>{
                 this.$router.push('/employee/id')
             })
         },
         
        deleteEmployee(){
            const employeeId = this.$route.params.id
            EmployeeServices.deleteEmployee(employeeId,this.employee).then(response =>{
                this.$router.push('/employees/id')
            })
        }
    }

}
</script>

<style>


</style>