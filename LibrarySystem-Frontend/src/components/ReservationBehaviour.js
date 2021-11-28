import axios from 'axios'

var config = require('../../config')

var frontendUrl= 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

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
            AXIOS.post('/reservations/'.concat(reservationID),{},
                {params:{
                    barcode : barcode,
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