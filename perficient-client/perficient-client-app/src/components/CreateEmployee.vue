<template>
  <div>
    <h1>Add a New Employee</h1>
    <form>
      Firstname*: <input type="text" v-model="employee.firstName" /><br /><br />
      Lastname*: <input type="text" v-model="employee.lastName" /> <br /><br />
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
        <input type="text" v-model="employee.address.region" /> <br /><br />
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

      <div>
        <h2>Skills</h2>

        <h2>Fields</h2>
        <div id="fields">
          <select class="form-control" @change="changeJobTitle($event)">
            <option value="" selected disabled>Choose</option>
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
            <button @click.prevent="addSkill($event)">Add Skill</button>
          </p>
          <p v-for="field in addedSkills" v-bind:key="field.id">{{ field }}</p>
        </div>

        Experience(in months):
        <input type="text" v-model="employee.skills.experience" /> Summary:
        <input type="text" v-model="employee.skills.summary" />
      </div>

      <input type="submit" v-on:submit.prevent="createEmployee" />
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
      employee: {
        address: {},
        skills: [],
      },
      FieldTitles: [
        { name: "Product designer", id: 1 },
        { name: "Full-stack developer", id: 2 },
        { name: "Product manager", id: 3 },
        { name: "Senior front-end developer", id: 4 },
      ],
      selectedField: "",
      addedSkills: [],
      selectedIndex: "",
    };
  },
  methods: {
    addSkill(event) {
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
        console.log(this.addedSkills);
      }
      
    },
    changeJobTitle(event) {
      this.selectedIndex = event.target.options.selectedIndex;
      this.selectedField =
        event.target.options[event.target.options.selectedIndex].text;
    },
    createEmployee() {
      EmployeeServices.createEmployee(employee).then((response) => {
       // this.$router.push({ name: "all-employees" });
      });
    },
  },
};
</script>

<style>
</style>