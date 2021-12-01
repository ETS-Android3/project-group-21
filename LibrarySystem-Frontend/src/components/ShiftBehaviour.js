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
		    applicationUser: {
				cardID: '',
				name: ''
			},

			deleteShiftCode:'',
		    shifts:[],
	      	newShifts:'',
	      	errorShift:'',
	      	response:[]
        }
    },

    created: function() {
        //Initializing shift from backend
        AXIOS.get('/shifts')
          .then(response => {
            //JSON responses are automatically parsed
            this.shifts = response.data
          })
          .catch(e => {
            this.errorShift = e
          })
         //Test data
         const c1 = new ShiftDto (123, '09:30:00', '17:30:00', 'Monday', 123)
         const c2 = new ShiftDto (124, '13:30:00', '18:30:00', 'Friday', 124)
         // Sample initial content
         this.shifts = [c1,c2]
      },
    methods: {
	    createShift: function (shiftCode, startTime, endTime, day, cardID) {
			AXIOS.post('/shifts/'.concat(shiftCode), {}, 
			{params:{
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
			this.newShifts = ''
		},
		deleteShift: function (deleteShiftCode){
			AXIOS.delete('/shifts/'.concat(deleteShiftCode), {}, {})
			.then(response => {
				AXIOS.get('/shifts')
				  .then(response => {
				  //JSON responses are automatically parsed
				  this.shifts = response.data
				  this.deleteShiftCode=''
				  })
				  .catch(e => {
				  this.errorShift = e
				  })
				})
				.catch(e => {
					var errorMsg = e.response.data.message
					console.log(errorMsg)
					this.errorReservation = errorMsg
			})	
		},
		updateShift:function (shiftCode, startTime, endTime, day, cardID) {
			AXIOS.patch('/shifts/'.concat(shiftCode), {}, 
			{params:{
				shiftCode: shiftCode,
				startTime: startTime,
				endTime: endTime,
        		day: day,
        		cardID: cardID
			}})
			.then(response => {
				AXIOS.get('/shifts')
				.then(response => {
			  //JSON responses are automatically parsed
				this.shifts = response.data
			})
			.catch(e => {
				this.errorShift = e
			})
				this.newShifts=''
				this.errorShift=''
			  })
			  .catch(e => {
				var errorMsg = e.response.data.message
				console.log(errorMsg)
				this.errorShift = errorMsg
			  })
			this.newShifts = ''
		  }

	}
}
