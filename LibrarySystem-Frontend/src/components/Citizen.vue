
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
            <input type="text" v-model="newCitizen" placeholder="Citizen Name">
          </td>
          <td>
            <button v-bind:disabled="!newCitizen" @click="createCitizen(newCitizen)">Create Citizen</button>
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
