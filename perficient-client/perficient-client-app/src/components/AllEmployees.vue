<template>
<div>
     
    <h3>List of employees</h3>
    <div class="employeeCard" v-for="employee in employeeList" v-bind:key="employee.id"> 
    <router-link v-bind:to="{name: 'viewemployee',params: {id: employee.id}}">

    <li>{{employee.firstName}}   {{employee.lastName}}</li>        
    </router-link>      
    </div>

   <router-link v-bind:to="{name: 'create-employee'}">Add a New Employee</router-link>
    
</div>
  
</template>

<script>
import EmployeeServices from '../services/EmployeeServices.js'
export default {
    name: 'all-employees',
    components:{
        EmployeeServices
    },
    data(){
        return{
            employeeList: [],
            address: [],
            skills:[]

        }
    },
        
    created(){
        EmployeeServices.listAllEmployees().then(
            (response) =>{
                
                this.employeeList = response.data; 
                    
            }
        );
    } 
            
        
    
}
</script>

<style>
li{
    text-decoration: none;
    padding: 20px;
}

</style>