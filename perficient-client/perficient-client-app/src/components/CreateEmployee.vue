<template>
  <div>
    <h1>Add a New Employee</h1>
    <form v-on:submit.prevent="createEmployee">
      <label for="firstname">Firstname*:</label>
       <input type="text" id="firstname" v-model="employee.firstName" /><br /><br />

       <label for="lastname">Lastname*:</label>
       <input type="text" id="lastname" v-model="employee.lastName" /> <br /><br />
      <div id="address">
        Address: <br />
        Street*: <input type="text" v-model="employee.address.street" />
        <br /><br />
        Suite: <input type="text" v-model="employee.address.suite" />
        <br /><br />
        City: <input type="text" v-model="employee.address.city" /> <br /><br />
        Region:[2 character]
        <input type="text" v-model="employee.address.region" /> <br /><br />
        Postal: <input type="text" v-model="employee.address.postal" />
        <br /><br />
        Country:[2 character]
        <input type="text" v-model="employee.address.country" /> <br /><br />
      </div>
      Contact Email: <input type="text" v-model="employee.contactEmail" />
      <br /><br />
      Company Email: <input type="text" v-model="employee.companyEmail" />
      <br /><br />
      Birthdate:[yyyy-mm--dd]
      <input type="text" v-model="employee.birthDate" /> <br /><br />
      Hired Date: [yyyy-mm--dd]<input
        type="text"
        v-model="employee.hiredDate"
      />
      <br /><br />
      Role:['Technical Consultant','Project Manager']
      <input type="text" v-model="employee.role" /> <br /><br />
      Business Unit: <input type="text" v-model="employee.businessUnit" />
      <br /><br />

      Assigned To: <input type="text" v-model="employee.assignedTo" />
      <br /><br />

      <div class="skills">
        <h2>Skills Section</h2>

        <h2>Fields</h2>
        <div id="fields">
          <select class="form-control" @change="changeTitle($event)">
            <option value="" selected disabled>Choose a Field</option>
            <option
              v-for="field in FieldTitles"
              :value="field.id"
              :key="field.id"
            >
              {{ field.name }}
            </option>
          </select>
          <br /><br />
          <p>
            <span>Selected Field: {{ selectedField }}</span> <br /><br />            
          </p>         
          <p v-for="field in addedSkills" v-bind:key="field.id">{{ FieldTitles[field].name }}</p>
        </div>

        Experience(in months):
        <input type="text" v-model="experience" /> Summary:
        <input type="text" v-model="summary" />
        <button @click.prevent="addSkill($event)">Add Skill</button>
      </div>

      <button class="button">Create Employee</button>
    </form>
  </div>
</template>

<script>
import EmployeeServices from "../services/EmployeeServices.js";
export default {
  name: "create-employee",
  result: "false",

  data() {
    return {
      experience: "",
      summary: "",
      skill:{
        field: '',
        experience: '',
        summary:''
      },
      skills:[],

      currentField:{},
      employee: {
        address: {},
        skills: [],        
      },
      FieldTitles: [
        { name: "Vue", id: 'b87ffe6b-f774-4955-8c2b-b7c7351e7eb2', type: 'Front End Development' },
        {  name: "Java", id: '4298540b-2bb6-4efb-be0a-d4d2f0961a69', type: 'Software Development'},
        {  name: "JavaScript", id: '80d4c4d9-e0c4-4cba-8c5f-608deeb8d47d', type: 'Software Development'},
        {  name: "Selenium", id: '999aed0c-e9cf-45cc-8ee7-9a043b54673c', type: 'Software Testing' },
      ],
      selectedField: "",
      addedSkills: [],
      selectedIndex: "",
    };
  },
  methods: {  
    addSkill(event) {
      if(this.selectedIndex === "" || this.selectedIndex > this.FieldTitles.length){
        alert("Please choose a field to add!")
      }
      
      if (this.addedSkills.length > 0) {
        if (this.addedSkills.length != this.FieldTitles.length) {
          this.result = false;
          for (let i = 0; i < this.addedSkills.length; i++) {
            if (this.addedSkills[i] == this.selectedIndex - 1) {
              this.result = "true";
              alert("You have already added this skill!");
              this.selectedIndex = "";
              break;
            }
          }
        } else {
          this.result = "true";
          alert("You have reached the MAX skills you can add! ");
        }
      }
      if (this.result !== "true") {
        this.result = false;    
        this.addedSkills.push(this.selectedIndex - 1);
        this.currentField = this.FieldTitles[this.selectedIndex - 1]
        this.skill.field = this.currentField
        this.skill.experience = this.experience
        this.skill.summary = this.summary
        this.skills.push(this.skill)
        this.employee.skills.push(this.skills[0])

        

        //this.addedSkills.push(this.FieldTitles[this.selectedIndex - 1].toString());
        //this.addedSkills.push(skillobj);
        console.log("Field is " + this.FieldTitles[this.selectedIndex - 1].toString());
        console.log(this.addedSkills);

      }
      
    },
    changeTitle(event) {
      this.selectedIndex = event.target.options.selectedIndex;
      this.selectedField =
        event.target.options[event.target.options.selectedIndex].text;
        
    },
    createEmployee() {
      EmployeeServices.createEmployee(this.employee).then((response) => {


        this.$router.push({ name: "all-employees" });
      });
    },
  },
};
</script>

<style>
.button{
  margin:20px;
}
.skills{
  height:300px;
}
</style>