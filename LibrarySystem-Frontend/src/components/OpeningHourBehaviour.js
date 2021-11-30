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
			
			deleteDay:'',
			openinghours:[],
			newOpeningHour:'',
			errorOpeningHour:'',
			response:[]
		}
	},
	
	created: function (){
		 //Initializing citizens from backend
        AXIOS.get('/openinghours')
        .then(response => {
        //JSON responses are automatically parsed
        	this.openinghours = response.data
        })
        .catch(e => {
        this.errorOpeningHour = e
        })
		
	},
	
	methods: {
		/*
		 * @Author: Dania Pennimpede
		 * create an Opening Hour
		 * @param day
		 * @param startTime
		 * @param endTime
		 */
		
		createOpeningHour: function (day, startTime, endTime) {
			AXIOS.post('/openinghours/'.concat(day), {}, 
			{params:{
				day: day,
				startTime: startTime,
				endTime: endTime
			}})
			
			.then(response => {
				// JSON responses are automatically parsed.
				this.openinghours.push(response.data)
				this.day=''
				this.startTime=''
				this.endTime=''
				})
				.catch(e => {
					var errorMsg = e.response.data.message
					console.log(errorMsg)
					this.errorOpeningHour = errorMsg
					})

			this.newOpeningHour = ''
			},
		/*
		 * @Author: Dania Pennimpede
		 * Update an Exisiting Opening Hour
		 * @param day
		 * @param startTime
		 * @param endTime
		 */
	    updateOpeningHour: function(day, startTime, endTime){
			AXIOS.patch('/openinghours/'.concat(day), {},
			 {params:{
				day: day,
				startTime: startTime,
				endTime: endTime
			}})
			.then(response => {
				AXIOS.get('/openinghours')
					.then(response =>{
					//JSON responses are automatically parsed
					this.openinghours = response.data
					})
					.catch(e => {
					this.errorOpeningHour = e
					})
					this.day=''
					this.startTime=''
					this.endTime=''
				})
				.catch(e =>{
					var errorMsg = e.response.data.message
            		console.log(errorMsg)
            		this.errorOpeningHour = errorMsg
				})
		},
		
		/*
		 * @Author: Dania Pennimpede
		 * Delete Exisiting Opening Hour
		 * @param deleteDay
		 */
		deleteOpeningHour: function(deleteDay){
			AXIOS.delete('/openinghours/'.concat(deleteDay), {}, {})
				.then(response => {
					AXIOS.get('/openinghours')
					.then(response => {
						//JSON responses are automatically parsed
						this.openinghours = response.data
						this.day=''
						this.startTime=''
						this.endTime=''
						this.deleteDay=''
					})
					.catch(e =>{
					this.errorOpeningHour = e
					})
				})
				.catch(e => {
				this.errorOpeningHour=e
        	})
		}
		
	    
		}
}