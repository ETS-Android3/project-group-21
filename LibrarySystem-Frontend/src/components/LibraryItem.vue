<template>
    <div id="libraryitem">
        <h2>Library Items</h2>

        <table>
            <tr>
                <td> Barcode </td>
                <td> Title </td>
                <td> Type </td>
                <td> Reservable </td>
                <td> Reserved </td>
                <td> loanPeriod </td>
            </tr>
            <tr v-for="libraryitem in libraryitems" :key="libraryitem.barcode">
                <td>{{libraryitem.barcode}}</td>
                <td>{{libraryitem.title}}</td>
                <td>{{libraryitem.type}}</td>
                <td>{{libraryitem.isReservable}}</td>
                <td>{{libraryitem.isReserved}}</td>
                <td>{{libraryitem.loanPeriod}}</td>
            </tr>
        </table>

        <table>
        <tr>
            <td>Barcode</td>
            <td>Title</td>
            <td>Type</td>
        </tr>
        <tr>
            <td><input type="text" v-model="barcode" placeholder="Item Barcode"/></td>
            <td><input type="text" v-model="title" placeholder="Item Title"></td>
            <td><input type="text" v-model="type" placeholder="Item Type"></td>
        </tr>
        <tr>
            <td>Is Reservable</td>
            <td>Is Reserved</td>
            <td>Loan Period</td>
        </tr>
        <tr>
            <td><input type="text" v-model="isReservable" placeholder="true if the item is reservable"/></td>
            <td><input type="text" v-model="isReserved" placeholder="true if the item is reserved"></td>
            <td><input type="text" v-model="loanPeriod" placeholder="Loan period"></td>
        </tr>
        <tr>
        <td></td>
            <td><button v-bind:disabled="(!barcode || !title || !type || !isReservable || !isReserved || !loanPeriod)"
            @click="createLibraryItem(barcode, type, title, isReservable, isReserved, loanPeriod)">Add Library Item</button></td>
        </tr>
        </table>
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
</style>