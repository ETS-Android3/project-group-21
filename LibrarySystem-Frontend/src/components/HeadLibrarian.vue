<template>
    <div class="headlibrarian">
    
        <h2>Headlibrarian</h2>
        <h5>Exisiting HeadLibrarians(for testing purpose):</h5>
        <table class="tablestyle" align="center">
            <tr class="trstyle">
                <th class="thstyle">cardID</th>
                <th class="thstyle">fullname</th>
                <th class="thstyle">address</th>
                <th class="thstyle">username</th>
                <th class="thstyle">password</th>
            <tr class="trstyle" v-for="headlibrarian in headlibrarians" :key="headlibrarian.cardID">
                <td class="tdstyle">{{headlibrarian.cardID}}</td>
                <td class="tdstyle">{{headlibrarian.fullname}}</td>
                <td class="tdstyle">{{headlibrarian.address}}</td>
                <td class="tdstyle">{{headlibrarian.username}}</td>
                <td class="tdstyle">{{headlibrarian.password}}</td>
            </tr>
        </table>
        <br>
        <table align="center">
            <tr> <input type="text" v-model="cardID" placeholder="User ID"> </tr>
            <p style="line-height:0em;"> &nbsp; </p>
            <tr> <input type="text" v-model="fullname" placeholder="Headlibrarian Name"> </tr>
            <p style="line-height:0em;"> &nbsp; </p>
            <tr> <input type="text" v-model="address" placeholder="Address"></tr>
            <p style="line-height:0em;"> &nbsp; </p>
            <tr> <input type="text" v-model="username" placeholder="Username"></tr>
            <p style="line-height:0em;"> &nbsp; </p>
            <tr> <input type="text" v-model="password" placeholder="Password"></tr>
            <p style="line-height:0em;"> &nbsp; </p>
            <button class="btn btn-primary" v-bind:disabled="(!cardID||!fullname||!address||!username||!password)" @click="createHeadLibrarian(cardID,fullname,address,username,password)">Create Headlibrarian</button>
            &nbsp;
            <router-link to ="/login">
            <button class="btn btn-primary">Cancle</button>
            </router-link>
        </table>
        <span v-if="errorHeadLibrarian" style="color:red">Error: {{errorHeadLibrarian}} </span>
       
    </div>

</template>

<script src="./HeadLibrarianBehaviour.js">
</script>

<style>
.btn-primary{
    padding: 10px 20px;
    font-size: 12px;
    border-radius: 10px;
}
.headlibrarian {
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