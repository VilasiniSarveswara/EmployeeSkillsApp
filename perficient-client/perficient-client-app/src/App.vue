
<template>

  <div id="app">  
   <h1> WELCOME TO PERFICIENT </h1>  
   <div id="nav"> 
      <router-link to="/Home" class="home"> Home </router-link>  
      <router-link to="/About" class="about"> About</router-link> 
      <router-link to="/employees" class="employees"> Employees</router-link> 
   
   </div>     
   
    <router-view/>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  background-color: lightcyan
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
.home,.about,.employees{ 
  padding: 20px;
  margin:20px;
}

</style>
