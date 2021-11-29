<template>
    <div id="openinghour">
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
            <tr> <input type="text" v-model="day" placeholder="DayOfWeek Day"> </tr>
            <p style="line-height:0em;"> &nbsp; </p>
            <tr> <input type="text" v-model="startTime" placeholder="Start Time (HH:mm:ss)"> </tr>
            <p style="line-height:0em;"> &nbsp; </p>
            <tr> <input type="text" v-model="endTime" placeholder="End Time (HH:mm:ss)"></tr>
            <p style="line-height:0em;"> &nbsp; </p>
            <button class="btn btn-primary" v-bind:disabled="(!day||!startTime||!endTime)" @click="createOpeningHour(day,startTime,endTime)">Add Opening Hour</button>
            &nbsp;
            <button class="btn btn-primary" v-bind:disabled="(!day||!startTime||!endTime)" 
            @click="updateOpeningHour(day,startTime,endTime)">Update Opening Hour</button>
            &nbsp;
            <button class="btn btn-primary" v-bind:disabled="!day" 
            @click="deleteOpeningHour(day)">Delete Opening Hour</button>
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
</style>