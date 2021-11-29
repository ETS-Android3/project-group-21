<template>
    <div class="shiftview">
        <h2>Shifts</h2>

        <table class="tablestyle" align="center">
            <tr class="trstyle">
                <td> shiftCode </td>
                <td> startTime </td>
                <td> endTime </td>
                <td> day </td>
                <td> Associated user </td> <!---->
            </tr>
            <tr class="trstyle" v-for="shift in shifts" :key="shift.shiftCode">
                <td>{{ shift.shiftCode }}</td>
                <td>{{ shift.startTime }}</td>
                <td>{{ shift.endTime }}</td>
                <td>{{ shift.day }}</td>
                <td>{{ shift.applicationUser.name }}</td> <!---->
            </tr>
        </table>
        
        <span v-if="errorShift" style="color:red">{{errorShift}}</span>
    </div>
</template>

<script src="./ShiftViewBehaviour.js">
</script>

<style>
    #Shift {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }
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