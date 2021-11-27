<template>
    <div id="reservation">
        <h2>Reservations</h2>
        <table>
            <tr>
                <td> Reservation ID </td>
                <td> User Name </td>
                <td> Item Title </td>
            </tr>
            <tr v-for="reservation in reservations" :key="reservation.reservationID">
                <td>{{reservation.reservationID}}</td>
                <td>{{reservation.applicationUser.name}}</td>
                <td>{{reservation.libraryItem.title}}</td>
            </tr>
        </table>

        <table>
            <tr>
                <th><label>Reservation ID:</label></th>
                <td><input type="text" v-model="reservationID" placeholder="Reservation ID"></td>
            </tr>
            <tr>
                <th><label>User ID:</label></th>
                <td><input type="text" v-model="cardID" placeholder="User ID"></td>
            </tr>
            <tr>
                <th><label>Library item barcode:</label></th>
                <td><input type="text" v-model="barcode" placeholder="Library item barcode"></td>
            </tr>
            <tr>
                <button v-bind:disabled="(!reservationID || !cardID || !barcode)" 
                @click="createReservation(reservationID, barcode, cardID)">Reserve</button>
            </tr>
        </table>

        <span v-if="errorReservation" style="color:red">Error: {{errorReservation}} </span>
    </div>
    
</template>

<script src="./ReservationBehaviour.js">

</script>

<style>
    #reservation{
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        color: #2c3e50;
        background: #f2ece8;
    }
</style>