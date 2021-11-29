<template>
    <div id="libraryitem">
        <div class="register-href">
            <router-link to="/itemlist" class="link"> Existing Library Items </router-link>
        </div>


        <h2>Add Library Item</h2><br>
        <table align="center">
            <tr>
                <th>Barcode</th>
                <th>Title</th>
                <th>Type</th>
            </tr>
            <tr>
                <td><input type="text" v-model="barcode" placeholder="Item Barcode"/></td>
                <td><input type="text" v-model="title" placeholder="Item Title"></td>
                <td><input type="text" v-model="type" placeholder="Item Type"></td>
            </tr>
            <tr>
                <th>Is Reservable</th>
                <th>Is Reserved</th>
                <th>Loan Period</th>
            </tr>
            <tr>
                <td><input type="text" v-model="isReservable" placeholder="true if reservable"/></td>
                <td><input type="text" v-model="isReserved" placeholder="default false when adding"></td>
                <td><input type="text" v-model="loanPeriod" placeholder="Loan period"></td>
            </tr>
        </table>

        <br>
        <table align="center">
            <tr>
                <td><button class="btn btn-primary" v-bind:disabled="(!barcode || !title || !type || !isReservable || !loanPeriod)"
                @click="createLibraryItem(barcode, type, title, isReservable, loanPeriod)">Add Library Item</button></td>
                &nbsp;
                <button class="btn btn-primary" v-bind:disabled="!barcode" 
                @click="updateLibraryItem(barcode, type, title, isReservable, isReserved, loanPeriod)">Update Library Item</button>

            </tr>
        </table>

        <br><br>

        <h2>Remove Library Item</h2><br>
        <table align="center">
            <tr>
                <th><label>Library item barcode:</label></th>
                <td><input type="text" v-model="deleteBarcode" placeholder="Barcode"></td>
            </tr>         
        </table>
        <br>
        <table align="center">
            <tr>
                <button class="btn btn-primary" v-bind:disabled="!deleteBarcode" 
                @click="deleteLibraryItem(deleteBarcode)">Remove Library Item</button>
            </tr>
        </table>

        <br><br>

        <p>
        <span style="color:red">{{errorLibraryItem}}</span>
        </p>
    </div>
</template>

<script src='./LibraryItemBehaviour.js'>

</script>

<style>
  #libraryitem {
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
.register-href {
  padding: 20px 1px;
  font-size: 15px;
  position: absolute;
  top: 0;
  right: 0;
  width: 180px;
  text-align: center;
}
.link{
    color:#42b983
}
.link:hover{
    color: #42b983;
}
</style>