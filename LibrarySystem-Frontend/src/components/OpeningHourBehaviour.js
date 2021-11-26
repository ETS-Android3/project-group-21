import axios from 'axios'

var config = require('../../config')

var frontendUrl= 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl}
})

/*
var backendConfigurer = function(){
  switch(process.env.NODE_ENV){
      case 'development':
          return 'http://' + config.dev.backendHost + ':' + config.dev.backendPort;
      case 'production':
          return 'https://' + config.build.backendHost + ':' + config.build.backendPort ;
  }
};


var backendUrl = backendConfigurer();
*/

function OpeningHourDto (day, startTime, endTime){
	this.day = day
	this.startTime = startTime
	this.endTime = endTime
}

export default {
	name: 'openinghour',
	data() {
		return{
			day:'',
			startTime:'',
			endTime:'',
			
			openinghours:[],
			newOpeningHour:'',
			errorOpeningHour:'',
			response:[]
		}
	},
	
	created: function (){
		 //Initializing citizens from backend
        AXIOS.get('/openinghour')
        .then(response => {
        //JSON responses are automatically parsed
        	this.openinghours = response.data
        })
        .catch(e => {
        this.errorOpeningHour = e
        })
		
		// Test data
		const o1 = new OpeningHourDto('Monday', '10:30:00', '11:30:00')
		const o2 = new OpeningHourDto('Tuesday', '10:30:00', '11:30:00')
		// Sample initial content
		this.openinghours = [o1, o2]
	},
	
	methods: {
	    createOpeningHour: function (day, startTime, endTime) {
			AXIOS.post('/openinghour/'.concat(day), {}, 
			{params:{
				day: day,
				startTime: startTime,
				endTime: endTime
			}})
			
			.then(response => {
				// JSON responses are automatically parsed.
				this.openinghours.push(response.data)
				this.newOpeningHour = ''
				this.errorOpeningHour = ''
				})
				.catch(e => {
					var errorMsg = e.response.data.message
					console.log(errorMsg)
					this.errorOpeningHour = errorMsg
					})
			// Create a new openinghour and add it to the list of openinghours
			var o = new OpeningHourDto(day, startTime, endTime)
			this.openinghours.push(o)
			// Reset the name field for new openinghours
			this.newOpeningHour = ''
			}
		}
}