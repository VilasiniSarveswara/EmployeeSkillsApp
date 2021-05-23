<template>
  <div>
    <h1>Fields</h1>
    <div id="app">
      <select class="form-control" @change="changeJobTitle($event)">
        <option value="" selected disabled>Choose</option>
        <option
          v-for="jobTitle in jobTitles"
          :value="jobTitle.id"
          :key="jobTitle.id"
        >
          {{ jobTitle.name }}
        </option>
      </select>
      <br /><br />
      <p>
        <span>Selected Field: {{ selectedField }}</span> <br /><br />
        <button v-on:click="addSkill($event)">Add Skill</button>
      </p>
      <p v-for="field in addedSkills" v-bind:key="field.id">{{ field }}</p>
    </div>

    <router-link v-bind:to="{name: 'create-employee',params:{skills: 'addedSkills'}}">Go back to Employee Creation Page</router-link>

  </div>
</template>

<script>
export default {
  name: "fields",
  props: ["fieldIds", "row"],
    result: 'false',

  data() {
    return {
      Titles: [
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
        if(this.addedSkills.length > 0){
            if(this.addedSkills.length != this.jobTitles.length){
                this.result = false
                for(let i=0; i < this.addedSkills.length;i++){
                    if(this.addedSkills[i] == (this.selectedIndex-1)){
                        this.result = 'true'
                        alert("You have already added this skill!")  
                        this.selectedIndex = ""                      
                        break;
                    }
                }            
            } 
            else{
                    
                this.result= 'true'
                alert("You have reached the MAX skills you can add! ");
            }     
        } 
        if(this.result !== 'true'){
            this.result = false
            this.addedSkills.push(this.selectedIndex - 1);
            console.log(this.addedSkills);
        }   
       
     
    },
    changeJobTitle(event) {
      this.selectedIndex = event.target.options.selectedIndex;      
      this.selectedField = event.target.options[event.target.options.selectedIndex].text;
      
    },
  },
};
</script>

<style>
</style>