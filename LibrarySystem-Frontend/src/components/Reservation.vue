<template>
    <div id="reservation">
        <h2>Reserve Library Item</h2>
        <table align="center">
            <tr>
                <th><label>Reservation ID:</label></th>
                <td><input type="text" v-model="reservationID" placeholder="Reservation ID"></td>
            </tr>
            <p style="line-height:0em;"> &nbsp; </p>
            <tr>
                <th><label>User ID:</label></th>
                <td><input type="text" v-model="applicationUser.cardID" placeholder="User ID"></td>
            </tr>
            <p style="line-height:0em;"> &nbsp; </p>

            <tr>
                <th><label>Library item barcode:</label></th>
                <td><input type="text" v-model="libraryItem.barcode" placeholder="Library item barcode"></td>
            </tr>
            <p style="line-height:0em;"> &nbsp; </p>
            <tr>
                <button class="btn btn-primary" v-bind:disabled="(!reservationID || !applicationUser.cardID || !libraryItem.barcode)" 
                @click="createReservation(reservationID, libraryItem.barcode, applicationUser.cardID)">Reserve</button>
            </tr>
        </table>

        <span v-if="errorReservation" style="color:red">Error: {{errorReservation}} </span>

        <br>

        <h2>Cancel Reservation</h2>
        <table align="center">
            <tr>
                <th><label>Reservation ID:</label></th>
                <td><input type="text" v-model="deleteReservationID" placeholder="Reservation ID"></td>
            </tr>         
            <p style="line-height:0em;"> &nbsp; </p>
            <tr>
                <button class="btn btn-primary" v-bind:disabled="!deleteReservationID" 
                @click="deleteReservation(deleteReservationID)">Cancel Reservation</button>
            </tr>
        </table>

        <br>

        <h2>Exisiting Reservations</h2>
        <table class="tablestyle" align="center">
            <tr class="trstyle">
                <th class="trstyle"> Reservation ID </th>
                <th class="trstyle"> User Name </th>
                <th class="trstyle"> Item Title </th>
            </tr>
            <tr  class="trstyle" v-for="reservation in reservations" :key="reservation.reservationID">
                <td  class="trstyle">{{reservation.reservationID}}</td>
                <td  class="trstyle">{{reservation.applicationUser.name}}</td>
                <td  class="trstyle">{{reservation.libraryItem.title}}</td>
            </tr>
        </table>

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
</style>