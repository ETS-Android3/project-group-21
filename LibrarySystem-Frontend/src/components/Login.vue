<template>
    <div class="container">
        <div class="register-href3">
            <router-link to="/openinghourview" class="link"> Library Opening Hours </router-link>
        </div>

        <div class="register-href">
            <router-link to="/citizen" class="link"> Citizen Register </router-link>
            <br>
            <router-link to="/librarian" class="link"> Librarian Register </router-link>
            <br>
            <router-link to="/headlibrarian" class="link"> HeadLibrarian Register </router-link>
            
        </div>
        <div class="login-block">
      
            <h2>Log In</h2>
            <h5>Using your User ID and Password</h5>
            <br>
            <input type="text" v-model="cardID" placeholder="User ID">
            <p style="line-height:0em;"> &nbsp; </p>
            <input type="text" v-model="password" placeholder="Password">
            <br><br>
            <button class="btn btn-primary" v-bind:disabled="(!cardID||!password)" 
                @click="login(cardID, password)">Log in</button>
            &nbsp;
            <router-link to ="/">
            <button class="btn btn-primary">Cancel</button>
            </router-link>
        </div>
    </div>
</template>

<script src = "./LoginBehaviour.js">
</script>

<style scoped>
.link{
    color:#42b983
}
.link:hover{
    color: #42b983;
}

.btn-primary{
  padding: 10px 20px;
  font-size: 12px;
  border-radius: 10px;
background-color: #42b983;
}

.register-href {
  padding: 20px 1px;
  font-size: 15px;
  position: absolute;
  top: 0;
  right: 0;
  width: 180px;
  text-align: center;
}
.container {
    background: #f2ece8;
}
.register-href3 {
  padding: 20px 1px;
  font-size: 15px;
  position: absolute;
  top: 0;
  left: 0;
  width: 220px;
  text-align: center;
}

</style>