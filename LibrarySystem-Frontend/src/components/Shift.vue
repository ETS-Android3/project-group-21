<template>
    <div class="shift">
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
        <table align="center">
            <tr>
                <th><label>Shift Code:</label></th>
                <td><input type="text" v-model="shiftCode" placeholder="Shift code"></td>
            </tr>
            <tr>
                <th><label>Start Time:</label></th>
                <td><input type="text" v-model="startTime" placeholder="HH:mm:ss"></td>
            </tr>
            <tr>
                <th><label>End Time:</label></th>
                <td><input type="text" v-model="endTime" placeholder="HH:mm:ss"></td>
            </tr>
            <tr>
                <th><label>Day:</label></th>
                <td><input type="text" v-model="day" placeholder="DayOfWeek Day"></td>
            </tr>
            <tr>
                <th><label>Associated Employee:</label></th>
                <td><input type="text" v-model="applicationUser.cardID" placeholder="User Id"></td>
            </tr>
        </table>
        <table align="center">
            <tr>
                <button class="btn btn-primary" v-bind:disabled="(!shiftCode || !startTime || !endTime || !day || !applicationUser.cardID)" 
                @click="createShift(shiftCode,  startTime,  endTime,  day,  applicationUser.cardID)">Add New Shift</button>
                &nbsp;
                <button class="btn btn-primary" v-bind:disabled="(!shiftCode || !startTime || !endTime || !day || !applicationUser.cardID)" 
                @click="updateShift(shiftCode,  startTime,  endTime,  day,  applicationUser.cardID)">Update a shift</button>
                &nbsp;
                <button class="btn btn-primary" v-bind:disabled="!shiftCode" 
                @click="deleteShift(shiftCode)">Remove Shift</button>
            </tr>
        
        </table>

        <span v-if="errorShift" style="color:red">{{errorShift}}</span>
    </div>
</template>

<script src="./ShiftBehaviour.js">
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