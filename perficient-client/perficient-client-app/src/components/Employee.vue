<template>
  <div class="employee">
    
      <h1>Employee Card</h1>
      <h2>{{employee.firstName}} {{employee.lastName}}</h2>
      <div class="address">
          <h3>
              Address: <br><br>
          </h3>          
          <h3>Street: {{address.street}} </h3>
          <h3>Suite: {{address.suite}}</h3> <br>
          <h3>City: {{address.city}}</h3>
          <h3>Region: {{address.region}}</h3>
          <h3>Postal: {{address.postal}}</h3>
          <h3>Country: {{address.country}}</h3>
      </div>
        <div >
             <h2>
             Skills: <br><br>
          </h2>
        <div class="skills" v-for="skill in skills" v-bind:key="skill.id">
           
            <h3>Skill Id: {{skill.id}}</h3>
            <div class="fields">
                 <h3>Field: {{skill.field.id}}</h3>
                <h3>Name: {{skill.field.name}}</h3>
                <h3>Type: {{skill.field.type}}</h3>
            </div>           
            <h3>Experience: {{skill.experience}}</h3>
            <h3>Summary: {{skill.experience}}</h3>
            <p>{{employee.skills.id}}</p>
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
    display:flex;
    border: 2px solid black;
    border-radius: 10px;
    width: auto;
    height: 150px;
    margin: 20px;
    
    
}
.address{
    display:flex;
    border: 2px solid black;
    border-radius: 10px;
    width: auto;
    height: auto;
    margin: 20px;
}
.fields{
    display: flex;
}

</style>