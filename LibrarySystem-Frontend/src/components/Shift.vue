<template>
    <div class="shift">

        <div class="register-href2">
            <a href="javascript:history.back()">Previous Page</a>
        </div>

        <h2>Shifts</h2>

        <table class="tablestyle" align="center">
            <tr class="trstyle">
                <td class="tdstyle"> shiftCode </td>
                <td class="tdstyle"> startTime </td>
                <td class="tdstyle"> endTime </td>
                <td class="tdstyle"> day </td>
                <td class="tdstyle"> Associated user </td> <!---->
            </tr>
            <tr class="trstyle" v-for="shift in shifts" :key="shift.shiftCode">
                <td class="tystyle">{{ shift.shiftCode }}</td>
                <td class="tystyle">{{ shift.startTime }}</td>
                <td class="tystyle">{{ shift.endTime }}</td>
                <td class="tystyle">{{ shift.day }}</td>
                <td class="tystyle">{{ shift.applicationUser.name }}</td> <!---->
            </tr>
        </table>
        <table align="center">
            <tr >
                <th><label>Shift Code:</label></th>
                <td class="tystyle"><input type="text" v-model="shiftCode" placeholder="Shift code"></td>
            </tr>
            <tr >
                <th><label>Start Time:</label></th>
                <td class="tystyle"><input type="text" v-model="startTime" placeholder="HH:mm:ss"></td>
            </tr>
            <tr >
                <th><label>End Time:</label></th>
                <td class="tystyle"><input type="text" v-model="endTime" placeholder="HH:mm:ss"></td>
            </tr>
            <tr >
                <th><label>Day:</label></th>
                <td class="tystyle"><input type="text" v-model="day" placeholder="DayOfWeek Day"></td>
            </tr>
            <tr >
                <th><label>Associated Employee:</label></th>
                <td class="tystyle"><input type="text" v-model="applicationUser.cardID" placeholder="User Id"></td>
            </tr>
        </table>
        <table align="center">
            <tr >
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
    .shift {
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
</style>