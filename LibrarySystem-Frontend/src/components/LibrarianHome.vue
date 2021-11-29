<template>
<div class="librarianhome">
    <h2>Headlibrarian Home Page</h2>
    <router-link to ="/reservation">
        <button class="btn btn-primary">Reserve</button>
    </router-link>

    <router-link to ="/libraryitem">
        <button class="btn btn-primary">Manage libraryitem</button>
    </router-link>

    <router-link to ="/shiftview">
        <button class="btn btn-primary">View Shifts</button>
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
</style>