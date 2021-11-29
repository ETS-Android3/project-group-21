<template>
    <div class="citizen">
      <h2>Citizen</h2>
      <h5>Existing citizen(for testing purpose):</h5>
      <table class = "tablestyle" align="center">
        <tr class="trstyle">
          <th class="thstyle">ID</th>
          <th class="thstyle">fullname</th>
          <th class="thstyle">address</th>
          <th class="thstyle">username</th>
          <th class="thstyle">password</th>
          <th class="thstyle">isLocal</th>
          <th class="thstyle">balance</th>

        <tr class = "trstyle" v-for="citizen in citizens":key = "citizen.id">
          <td class="tdstyle">{{citizen.id}}</td>
          <td class="tdstyle">{{citizen.name}}</td>
          <td class="tdstyle">{{citizen.address}}</td>
          <td class="tdstyle">{{citizen.userName}}</td>
          <td class="tdstyle">{{citizen.password}}</td>
          <td class="tdstyle">{{citizen.isLocal}}</td>
          <td class="tdstyle">{{citizen.balance}}</td>
      </tr>
      </table>
      <br>
      <table align = "center">
        <tr> <input type="text" v-model="ID" placeholder="ID"></tr>
        <p style="line-height:0em;"> </p>
        <tr> <input type="text" v-model="name" placeholder="user fullname"></tr>
        <p style="line-height:0em;"> </p>
        <tr> <input type="text" v-model="address" placeholder="user address"></tr>
        <p style="line-height:0em;"> </p>
        <tr> <input type="text" v-model="userName" placeholder="username"></tr>
        <p style="line-height:0em;"> </p>
        <tr> <input type="text" v-model="password" placeholder="password"></tr>
        <p style="line-height:0em;"> </p>
        <tr> <input type="text" v-model="isLocal" placeholder="is Local"></tr>
        <p style="line-height:0em;"> </p>

        <button class="btn btn-primary" v-bind:disabled="(!ID||!name||!address||!userName||!password||!isLocal)" @click="createCitizen(ID,name,address,userName,password,isLocal,balance,onlineAccountActivated)">Create Cirizen</button>
        &nbsp;
        <router-link to ="/login">
          <button class="btn btn-primary">Cancel</button>
        </router-link>
      </table>
      <span v-if="errorCitizen" style="color:red">Error: {{errorCitizen}} </span>

    </div>
</template>
<script src="./CitizenBehaviour.js">
</script>

<style>
.btn-primary:disabled{
  padding: 10px 20px;
  font-size: 12px;
  border-radius: 10px;
  background-color: #42b983;
}
.btn-primary{
  padding: 10px 20px;
  font-size: 12px;
  border-radius: 10px;
  background-color: #42b983;
}
.librarian {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  color: #2c3e50;
  background: #f2ece8;

}
.tablestyle {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 70%;
}

.tdstyle, .thstyle {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

.trstyle:nth-child(even) {
  background-color: #dddddd;
}
</style>
