import axios from 'axios'

var config = require('../../config')

var backendConfigurer = function () {
  switch (process.env.NODE_ENV) {
    case 'development':
      return 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
    case 'production':
      return 'https://' + config.build.backendHost + ':' + config.build.backendPort
  }
}

var frontendConfigurer = function () {
  switch (process.env.NODE_ENV) {
    case 'development':
      return 'http://' + config.dev.host + ':' + config.dev.port
    case 'production':
      return 'https://' + config.build.host + ':' + config.build.port
  }
}

var backendUrl = backendConfigurer()
var frontendUrl = frontendConfigurer()

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl}
})

function ReservationDto(reservationID, barcode, cardID){
    this.reservationID = reservationID
    this.barcode = barcode
    this.cardID = cardID
}

export default {
    name: 'reservation',
    data () {
      return {
        libraryitems:[],
        reservationID:'',

        libraryItem: {
          barcode: '',
          title: ''
        },

        applicationUser: {
          cardID: '',
          name: ''
        },

        deleteReservationID:'',
        reservations: [],
        newReservation: '',
        errorReservation: '',
        result:false,
        response: []
      }
    },
    created: function () {
        //Initializing citizens from backend
        AXIOS.get('/reservations')
        .then(response => {
        //JSON responses are automatically parsed
        this.reservations = response.data
        })
        .catch(e => {
        this.errorReservation = e
        })

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
        deleteReservation: function (deleteReservationID) {
          AXIOS.delete('/reservations/'.concat(deleteReservationID), {}, {})
          .then(response => {
            AXIOS.get('/reservations')
              .then(response => {
              //JSON responses are automatically parsed
              this.reservations = response.data
              this.deleteReservationID=''
              })
              .catch(e => {
              this.errorReservation = e
              })
            })
            .catch(e => {
              var errorMsg = e.response.data.message
              console.log(errorMsg)
              this.errorReservation = errorMsg
            })
        },
        
        createReservation: function (reservationID, barcode, cardID) {
            const myArray = barcode.split("-");
            var firsttoken = myArray[0];
            AXIOS.post('/reservations/'.concat(reservationID),{},
                {params:{
                    barcode : firsttoken,
                    cardID : cardID}})
                .then(response => {
                this.reservations.push(response.data)
                this.reservationID=''
                this.libraryItem.barcode=''
                this.applicationUser.cardID=''
                })
                .catch(e => {
                  var errorMsg = e.response.data.message
                  console.log(errorMsg)
                  this.errorReservation = errorMsg
                })

            this.newReservation = ''
        }
    }
  }