<template>
  <div class="employee">
    
      <h1>Employee Card</h1>
      <h1>{{employee.firstName}} {{employee.lastName}}</h1>
      <div class="address">
          <h3> Address:</h3>          
          <h3 class="street">Street: {{address.street}} </h3>
          <h3 class="suite">Suite: {{address.suite}}</h3> <br>
          <h3 class="city">City: {{address.city}}</h3>
          <h3 class="region">Region: {{address.region}}</h3>
          <h3 class="postal">Postal: {{address.postal}}</h3>
          <h3 class="country">Country: {{address.country}}</h3>
      </div>
        <div >
             <h2 v-if="this.skillsLength > 0">
             Skills: <br><br>                
          </h2>
          <h2 v-else>
              No skills to display!
          </h2>
        <div class="skills" v-for="skill in skills" v-bind:key="skill.id">            
            <h3>Skill Id: {{skill.id}}</h3> 
            <h3>Field: {{skill.field.id }}</h3>                 
            <h3>Name: {{ skill.field.name }} </h3>
            <h3>Type: {{ skill.field.type }}</h3>                   
            <h3>Experience: {{ skill.experience }} </h3>
            <h3> {{ skill.summary }}</h3>
            
        </div>             
    </div>

      <router-link v-bind:to="{name:'update',params: {id: employee.id}}">Update Employee</router-link>
  
  </div>
</template>

<script>
import EmployeeServices from '../services/EmployeeServices.js'
export default {
    
   
    data(){
        return{
            skillsLength: '',
            employee: {},
            address: {},
            skills:[{}]           
            
        }
    },
    created(){
        const employeeId = this.$route.params.id
        EmployeeServices.listEmployee(employeeId).then(response =>{
            this.employee = response.data
            this.address = response.data.address
            this.skills = response.data.skills   
            this.skillsLength = this.skills.length       
            
            
        })
    }   

}
</script>

<style>
.card{
    border: 2px solid black;
    border-radius: 10px;
    width: 250px;
    height: 1000px;
    margin: 20px;
}

.skills{
    
    border: 2px solid black;
    border-radius: 10px;
    width: auto;
    height: 150px;
    margin: 10px;   
    padding:20px;

}
.address{
    display:flex;
    border: 2px solid black;
    border-radius: 10px;
    width: auto;
    height: auto;
    margin: 20px;
}

.street,.suite,.city,.region,.postal,.country{
    padding: 70px
}

</style>