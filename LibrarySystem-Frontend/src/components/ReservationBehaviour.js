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
        cardID:'',
        barcode:'',
        reservations: [],
        newReservation: '',
        errorReservation: '',
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
        // Test data
        const r1 = new ReservationDto(1357, 2468, 12345)
        const r2 = new ReservationDto(35791, 68462, 98765)
        // Sample initial content
        this.reservations = [r1, r2]
      },
    methods: {
        createReservation: function (reservationID, barcode, cardID) {
            AXIOS.post('/reservations/'.concat(reservationID),{},
                {params:{
                    barcode : barcode,
                    cardID : cardID}})
                .then(response => {
                this.reservations.push(response.data)
                this.reservationID=''
                this.barcode=''
                this.cardID=''
                })
                .catch(e => {
                  var errorMsg = e.response.data.message
                  console.log(errorMsg)
                  this.errorReservation = errorMsg
                })
            // Create a new person and add it to the list of people
            var r = new ReservationDto(reservationID, barcode, cardID)
            this.reservations.push(r)
            // Reset the name field for new people
            this.newReservation = ''
        }
    }
  }