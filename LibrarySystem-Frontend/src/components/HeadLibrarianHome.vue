<template>
<div class="headlibrarianhome">
    <div class="register-href">
        <router-link to="/librarian" class="link"> Librarian Register </router-link>
        <br>
    </div>
    <div class="register-href2">
        <a href="javascript:history.back()">Previous Page</a>
    </div>

    <h2>Headlibrarian Home Page</h2>
    <router-link to ="/reservation">
        <button class="btn btn-primary">Reserve</button>
    </router-link>
    <router-link to ="/openinghour">
        <button class="btn btn-primary">Modify Opening Hour</button>
    </router-link>
    <router-link to ="/libraryitem">
        <button class="btn btn-primary">Manage libraryitem</button>
    </router-link>
    <router-link to ="/shift">
        <button class="btn btn-primary">Manage Shifts</button>
    </router-link>
 
</div>
</template>
<script>
</script>
<style>
.btn-primary:disabled{
    padding: 10px 20px;
    font-size: 40px;
    border-radius: 10px;
    background-color: #42b983;
}
.btn-primary{
    padding: 10px 20px;
    font-size: 40px;
    border-radius: 10px;
    background-color: #42b983;
}
.register-href2 {
  padding: 20px 1px;
  font-size: 15px;
  position: absolute;
  top: 0;
  left: 0;
  width: 180px;
  text-align: center;
}
a {
  color: #42b983;
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
</style>