<template>
    <div id="openinghour">

        <div class="register-href2">
            <a href="javascript:history.back()">Previous Page</a>
        </div>
        
        <h2>Opening Hours</h2>
		<h5>Exisiting Opening Hours(for testing purpose):</h5>
        <table class="tablestyle" align="center">
            <tr class="trstyle">
                <th class="thstyle"> Day </th>
                <th class="thstyle"> Start Time </th>
                <th class="thstyle"> End Time </th>
            <tr class="trstyle" v-for="openinghour in openinghours" :key="openinghour.day">
                <td class="tdstyle">{{ openinghour.day }}</td>
                <td class="tdstyle">{{ openinghour.startTime }}</td>
                <td class="tdstyle">{{ openinghour.endTime }}</td>
            </tr>
        </table>
		<br>
        <table align="center">
            <tr>
                <th><label>Day:</label></th>
                <td><input type="text" v-model="day" placeholder="DayOfWeek Day"></td>
            </tr>
            <tr>
                <th><label>Start Time:</label></th>
                <td><input type="text" v-model="startTime" placeholder="HH:mm:ss"></td>
            </tr>
            <tr>
                <th><label>End Time:</label></th>
                <td><input type="text" v-model="endTime" placeholder="HH:mm:ss"></td>
            </tr>
        </table>
        <table align="center">
            <tr>
                <button class="btn btn-primary" v-bind:disabled="(!day||!startTime||!endTime)" 
                @click="createOpeningHour(day,startTime,endTime)">Add Opening Hour</button>
                &nbsp;
                <button class="btn btn-primary" v-bind:disabled="(!day||!startTime||!endTime)" 
            	@click="updateOpeningHour(day,startTime,endTime)">Update Opening Hour</button>
                &nbsp;
                <button class="btn btn-primary" v-bind:disabled="!day" 
            	@click="deleteOpeningHour(day)">Delete Opening Hour</button>
            </tr>
        
        </table>
     
        <span v-if="errorOpeningHour" style="color:red">{{errorOpeningHour}}</span>
    </div>
    
</template>

<script src="./OpeningHourBehaviour.js">

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
.openinghour {
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