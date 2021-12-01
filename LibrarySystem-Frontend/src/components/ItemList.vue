<template>
    <div id="itemlist">

        <div class="register-href2">
            <a href="javascript:history.back()">Previous Page</a>
        </div>

        <h2>Existing Library Items</h2><br>

        <table class="tablestyle" align="center">
            <tr class="trstyle">
                <th class="thstyle"> Barcode </th>
                <th class="thstyle"> Title </th>
                <th class="thstyle"> Type </th>
                <th class="thstyle"> Reservable </th>
                <th class="thstyle"> Reserved </th>
                <th class="thstyle"> loanPeriod </th>
            </tr>
            <tr class="trstyle" v-for="libraryitem in libraryitems" :key="libraryitem.barcode">
                <td class="tdstyle">{{libraryitem.barcode}}</td>
                <td class="tdstyle">{{libraryitem.title}}</td>
                <td class="tdstyle">{{libraryitem.type}}</td>
                <td class="tdstyle">{{libraryitem.isReservable}}</td>
                <td class="tdstyle">{{libraryitem.isReserved}}</td>
                <td class="tdstyle">{{libraryitem.loanPeriod}}</td>
            </tr>
        </table>

        <br>

        <router-link to ="/libraryitem">
            <button class="btn btn-primary">Add Library Item</button>
        </router-link>

    </div>
</template>


<script src="./ItemListBehaviour.js">
</script>

<style>
#itemlist{
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