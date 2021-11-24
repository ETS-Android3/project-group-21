
<!--This is a template from the tutorial, plz do not just copy paste it for the other vue -->
<template>
    <div id="citizen">
        <h2>Citizen</h2>
        <table>
           <tr v-for="citizen in citizens" :key="citizen.ID">
              <td>{{citizen.ID}}</td>
              <td>{{citizen.fullname}}</td>
              <td>{{citizen.address}}</td>
              <td>{{citizen.username}}</td>
              <td>{{citizen.password}}</td>
              <td>{{citizen.isLocal}}</td>
              <td>{{citizen.balance}}</td>
              <td>{{citizen.onlineAccountActivated}}</td>
           </tr>
        </table>
        <table>
        <tr>
          <td>
            <input type="text" v-model="ID" placeholder="User ID">
            <input type="text" v-model="fullname" placeholder="Citizen Name">
            <input type="text" v-model="address" placeholder="Address">
            <input type="text" v-model="username" placeholder="Username">
            <input type="text" v-model="password" placeholder="Password">
            <input type="text" v-model="isLocal" placeholder="Enter true if the user is local">
       </td>
          <td>
            <button v-bind:disabled="!ID" @click="createCitizen(ID,fullname,address,username,password,isLocal)">Create Citizen</button>
          </td>
        </tr>
        </table>
        <span v-if="errorCitizen" style="color:red">Error: {{errorCitizen}} </span>
    </div>
</template>

<script src="./CitizenBehaviour.js">
</script>]

<style>
    #citizen {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        color: #2c3e50;
        background: #f2ece8;
    }
</style>
