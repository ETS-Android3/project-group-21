<template>
    <div class="librarian">
    
        <h2>Librarian</h2>
        <h5>Exisiting Librarians(for testing purpose):</h5>
        <table class="tablestyle" align="center">
            <tr class="trstyle">
                <th class="thstyle">cardID</th>
                <th class="thstyle">name</th>
                <th class="thstyle">address</th>
                <th class="thstyle">username</th>
                <th class="thstyle">password</th>
            <tr class="trstyle" v-for="librarian in librarians" :key="librarian.cardID">
                <td class="tdstyle">{{librarian.id}}</td>
                <td class="tdstyle">{{librarian.name}}</td>
                <td class="tdstyle">{{librarian.address}}</td>
                <td class="tdstyle">{{librarian.userName}}</td>
                <td class="tdstyle">{{librarian.password}}</td>
            </tr>
        </table>
        <br>
        <table align="center">
            <tr> <input type="text" v-model="cardID" placeholder="User ID"> </tr>
            <p style="line-height:0em;"> &nbsp; </p>
            <tr> <input type="text" v-model="name" placeholder="Librarian Name"> </tr>
            <p style="line-height:0em;"> &nbsp; </p>
            <tr> <input type="text" v-model="address" placeholder="Address"></tr>
            <p style="line-height:0em;"> &nbsp; </p>
            <tr> <input type="text" v-model="username" placeholder="Username"></tr>
            <p style="line-height:0em;"> &nbsp; </p>
            <tr> <input type="text" v-model="password" placeholder="Password"></tr>
            <p style="line-height:0em;"> &nbsp; </p>
            <button class="btn btn-primary" v-bind:disabled="(!cardID||!name||!address||!username||!password)" @click="createLibrarian(cardID,name,address,username,password)">Create Librarian</button>
            &nbsp;
            <router-link to ="/login">
            <button class="btn btn-primary">Cancle</button>
            </router-link>
            &nbsp;
            <button class="btn btn-primary" v-bind:disabled="!cardID" 
            @click="updateLibrarian(cardID,name,address,username,password)">Update Librarian</button>
            &nbsp;
            <button class="btn btn-primary" v-bind:disabled="!cardID" 
            @click="deleteLibrarian(cardID)">Delete Librarian</button>
        </table>
        <span v-if="errorLibrarian" style="color:red">Error: {{errorLibrarian}} </span>
       
    </div>

</template>

<script src="./LibrarianBehaviour.js">
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