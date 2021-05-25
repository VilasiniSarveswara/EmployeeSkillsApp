<template>
  <div>
    <h1>List of employees</h1>
    <div
      class="employeeCard"
      v-for="employee in employeeList"
      v-bind:key="employee.id"
    >
      <div class="employeeLink">
        <router-link
          v-bind:to="{ name: 'viewemployee', params: { id: employee.id } }"
        >
          {{ employee.firstName }} {{ employee.lastName }}
        </router-link>
        <div class="contactEmail">
                 Email ID: {{employee.contactEmail}}
        </div>
        <div class="companyEmail">
                 Email ID: {{employee.companyEmail}}
        </div>
       
      </div>
    </div>
    <div class = "addButton"></div>
    <router-link v-bind:to="{ name: 'create-employee' }"
      >Add a New Employee</router-link
    >
  </div>
</template>

<script>
import EmployeeServices from "../services/EmployeeServices.js";
export default {
  name: "all-employees",
  components: {
    EmployeeServices,
  },
  data() {
    return {
      employeeList: [],
      address: [],
      skills: [],
    };
  },

  created() {
    EmployeeServices.listAllEmployees().then((response) => {
      this.employeeList = response.data;
    }) .catch(error =>{
      if(error.response){
        this.errorMsg = "Error fetching all employees. Please try again. "
      }
      if(error.request){
                 this.errorMsg = "Server couold not be reached! Please try again."                
            }

    })
  },
};
</script>

<style>
.addButton,.employeeLink {

  text-decoration: none;
  padding: 10px;
  margin: 20px
}


.employeeLink{
    grid-area: employee-link-grid;
    padding:20px;
}

.contactEmail{
    grid-area: contactEmail-grid;
    padding:20px;
}

.companyEmail{
    grid-area: companyEmail-grid;
}

</style>