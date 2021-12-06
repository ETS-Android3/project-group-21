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
      }
}