import axios from 'axios'

var config = require('../../config')

var frontendUrl= 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl}
})


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
		 //Initializing opening hour from backend
        AXIOS.get('/openinghours')
        .then(response => {
        //JSON responses are automatically parsed
        	this.openinghours = response.data
        })
        .catch(e => {
        this.errorOpeningHour = e
        })
		
		// Test data
		//const o1 = new OpeningHourDto('Monday', '10:30:00', '11:30:00')
		//const o2 = new OpeningHourDto('Tuesday', '10:30:00', '11:30:00')
		// Sample initial content
		//this.openinghours = [o1, o2]
	}
}