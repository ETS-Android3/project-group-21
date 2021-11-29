import axios from 'axios'

var config = require('../../config')

var frontendUrl= 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl}
})

function LibraryItemDto (barcode, type, title, isReservable, isReserved, loanPeriod){
    this.barcode = barcode
    this.type = type
    this.title = title
    this.isReservable = isReservable
    this.isReserved = isReserved
    this.loanPeriod = loanPeriod
}

export default {
    name: 'libraryitem',
    data () {
      return {
        barcode:'',
        title:'',
        type:'',
        isReservable:'',
        isReserved:'',
        loanPeriod:'',
        deleteBarcode:'',
        libraryitems: [],
        newLibraryItem: '',
        errorLibraryItem: '',
        response: []
      }
    },

    created: function () {

        //Initializing citizens from backend
        AXIOS.get('/libraryitems')
        .then(response => {
        //JSON responses are automatically parsed
        this.libraryitems = response.data
        })
        .catch(e => {
        this.errorLibraryItem = e
        })
      },
    
    methods: {
      updateLibraryItem: function (barcode, type, title, isReservable, isReserved, loanPeriod) {
        AXIOS.patch('/libraryitems/'.concat(barcode), {}, 
        {params:{
          type : type,
          title : title,
          isReservable : isReservable,
          isReserved : isReserved,
          loanPeriod : loanPeriod
        }})
        .then(response => {
          AXIOS.get('/libraryitems')
            .then(response => {
            //JSON responses are automatically parsed
            this.libraryitems = response.data
            })
            .catch(e => {
            this.errorLibraryItem = e
            })
            this.barcode=''
            this.type=''
            this.title=''
            this.isReservable=''
            this.isReserved=''
            this.loanPeriod=''
          })
          .catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorReservation = errorMsg
          })
      },
      deleteLibraryItem: function (deleteBarcode) {
        AXIOS.delete('/libraryitems/'.concat(deleteBarcode), {}, {})
        .then(response => {
          AXIOS.get('/libraryitems')
            .then(response => {
            //JSON responses are automatically parsed
            this.libraryitems = response.data
            this.deleteBarcode=''
            })
            .catch(e => {
            this.errorLibraryItem = e
            })
          })
          .catch(e => {
          this.errorLibraryItem = e
          })
      },

      createLibraryItem: function (barcode, type, title, isReservable, loanPeriod) {
          AXIOS.post('/libraryitems/'.concat(barcode),{},
              {params:{
                  type : type,
                  title : title,
                  isReservable : isReservable,
                  isReserved : false,
                  loanPeriod : loanPeriod
              }})
              .then(response => {
              this.libraryitems.push(response.data)
              this.barcode=''
              this.type=''
              this.title=''
              this.isReservable=''
              this.isReserved=''
              this.loanPeriod=''
              })
              .catch(e => {
                var errorMsg = e.response.data.message
                console.log(errorMsg)
                this.errorReservation = errorMsg
              })

          // // Create a new person and add it to the list of people
          // var li = new LibraryItemDto(barcode, type, title, isReservable, false, loanPeriod)
          // this.libraryitems.push(li)
          // // Reset the name field for new people
          this.newLibraryItem = ''
        }
      }
  }
