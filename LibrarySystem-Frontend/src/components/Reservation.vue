<template>
    <div id="reservation">
        <br>
        <h2>Reserve Library Item</h2><br>
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
                <select class="select_style" v-model="libraryItem.barcode">
                    <option disabled value="">Item barcode - title</option>
                    <option v-for="libraryitem in libraryitems" >
                    {{ libraryitem.barcode }} - {{libraryitem.title}}
                    </option>
                </select>
            </tr>

            <p style="line-height:0em;"> &nbsp; </p>
            <tr>
                <button class="btn btn-primary" v-bind:disabled="(!reservationID || !applicationUser.cardID || !libraryItem.barcode)" 
                @click="createReservation(reservationID, libraryItem.barcode, applicationUser.cardID)">Reserve</button>
            </tr>
        </table>

        <span v-if="errorReservation" style="color:red">Error: {{errorReservation}} </span>

        <br><br>

        <h2>Cancel Reservation</h2><br>
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

        <br><br>

        <h2>Exisiting Reservations</h2><br>
        <table class="tablestyle" align="center">
            <tr class="trstyle">
                <th class="thstyle"> Reservation ID </th>
                <th class="thstyle"> User Name </th>
                <th class="thstyle"> Item Title </th>
            </tr>
            <tr  class="trstyle" v-for="reservation in reservations" :key="reservation.reservationID">
                <td  class="tdstyle">{{reservation.reservationID}}</td>
                <td  class="tdstyle">{{reservation.applicationUser.name}}</td>
                <td  class="tdstyle">{{reservation.libraryItem.title}}</td>
            </tr>
        </table>

        <br><br><br>

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
.select_style {
  background: #ddf0e5;
  border: none;
  color: #394066;
  text-transform: none;
  font-variant: normal;
}
</style>