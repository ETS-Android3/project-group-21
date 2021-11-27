import axios from 'axios'

var config = require('../../config')

var frontendUrl= 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl}
})

function ShiftDto ( shiftCode,  startTime,  endTime,  day,  cardID) {
        this.day = day;
		    this.startTime = startTime;
		    this.endTime = endTime;
        this.shiftCode = shiftCode;
        this.cardID = cardID;
}

export default{
    name: 'shift',
    data(){
      return{
        shiftCode:'',
		    startTime:'',
		    endTime:'',
        day:'',
		    cardID:'',

		    shifts:[],
	      newShifts:'',
	      errorShift:'',
	      response:[]
        }
    },

    created: function() {
        //Initializing shift from backend
        AXIOS.get('/shift')
          .then(response => {
            //JSON responses are automatically parsed
            this.shifts = response.data
          })
          .catch(e => {
            this.errorShift = e
          })
         //Test data
         const c1 = new ShiftDto (123, '09:30:00', '17:30:00', 'Monday', 123)
         const c2 = new ShiftDto (123, '13:30:00', '18:30:00', 'Friday', 124)
         // Sample initial content
         this.shifts = [c1,c2]
      },
      methods: {
	    createShift: function (shiftCode, startTime, endTime, day, cardID) {
			AXIOS.post('/shift/'.concat(ShiftCode), {}, 
			{params:{
				shiftCode: shiftCode,
			  startTime: startTime,
			  endTime: endTime,
        day: day,
        cardID: cardID
			}})
			
			.then(response => {
				// JSON responses are automatically parsed.
				this.shifts.push(response.data)
				this.newShifts = ''
				this.errorShift = ''
				})
				.catch(e => {
					var errorMsg = e.response.data.message
					console.log(errorMsg)
					this.errorShift = errorMsg
					})
			// Create a new openinghour and add it to the list of openinghours
			var o = new ShiftDto(shiftCode, startTime, endTime, day, cardID)
			this.shifts.push(o)
			// Reset the name field for new openinghours
			this.newShifts = ''
			}
		}
}