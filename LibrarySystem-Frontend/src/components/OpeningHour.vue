<template>
    <div id="openinghour">
        <h2>Opening Hours</h2>

        <table>
            <tr>
                <td> Day </td>
                <td> startTime </td>
                <td> endTime </td>
            </tr>
            <tr v-for="openinghour in openinghours" :key="openinghour.day">
                <td>{{ openinghour.day }}</td>
                <td>{{ openinghour.startTime }}</td>
                <td>{{ openinghour.endTime }}</td>
            </tr>
        </table>

        <table>
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
            <tr>
                <button v-bind:disabled="(!day || !startTime || !endTime)" 
                @click="createOpeningHour(day, startTime, endTime)">Add Opening Hour</button>
            </tr>
        </table>
     
        <span v-if="errorOpeningHour" style="color:red">{{errorOpeningHour}}</span>
    </div>
    
</template>

<script src="./OpeningHourBehaviour.js">

</script>

<style>
  #openinghour {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }
</style>