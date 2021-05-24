<template>
  <div class="updateEmployee">

      <h1>Update Employee</h1>
      <div class="employeeId">
          <img src="../assets/ping_pong_loader.gif" v-if="isLoading" >
          Employee ID: {{employee.id}} <br><br>
      </div>
     

      <form class="employee" v-on:submit.prevent="updateEmployee">         
          
          Firstname*: <input type="text" v-model="employee.firstName"/><br><br>
          Lastname*: <input type="text" v-model="employee.lastName"/>  <br><br>
           <div id="address">
               Address: <br>
          Street*: <input type="text" v-model="address.street" /> <br><br>
          Suite: <input type="text" v-model="address.suite" /> <br><br>
          City: <input type="text" v-model="address.city" /> <br><br>
          Region: <input type="text" v-model="address.region" /> <br><br>
          Postal: <input type="text" v-model="address.postal" /> <br><br>
          Country: <input type="text" v-model="address.country" /> <br><br>
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
               <p>Field: {{skill.field.id}}</p> 
                <p>Name: {{skill.field.name}}</p>
                <p>Type: {{skill.field.type}}</p>
            </div> 
            <p>Experience:<input type="text" v-model="skill.experience"></p>    
            <p> Summary: <input type="textarea" v-model="skill.summary"></p>
            
        </div>
        
 
        <input class="submit" type="submit"><br>
      </form>
      <br>
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
            this.address = response.data.address
            this.skills = response.data.skills
        })

    },
     methods:{
         updateEmployee(){
             const employeeId = this.$route.params.id
             EmployeeServices.updateEmployee(employeeId,this.employee).then(response =>{
                 this.$router.push('/employees')
             })
         },
         
        deleteEmployee(){
            const employeeId = this.$route.params.id
            EmployeeServices.deleteEmployee(employeeId,this.employee).then(response =>{
                this.$router.push('/employees')
            })
        }
    }

}
</script>

<style>

.employee{
    border: 2px solid black;
    border-radius: 10px;
    padding:10px
}

.employeeId{
    font-weight: bold;
    color: red
}

</style>