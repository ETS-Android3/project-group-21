
<!--This is a template from the tutorial, plz do not just copy paste it for the other vue -->
<template>
    <div id="librarysystem">
        <h2>Citizen</h2>
        <table>
            <tr>
                <td>Bob</td>
                <td>Reservation</td>
            </tr>
            <tr>
                <td>
                    <input libraryitem="item" applicationuser="User Name">
                </td>
                <td>
                    <button>Create</button>
                </td>
            </tr>
        </table>
        <p>
            <span style="color:red">Error: Message text comes here </span>
        </p>
    </div> 
</template>
<script>
</script>
<style>
    #librarysystem{
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        color: #2c3e50;
        background: #f2ece8;
    }
</style>