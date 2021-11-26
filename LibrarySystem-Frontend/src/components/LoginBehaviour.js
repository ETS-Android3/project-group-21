import axios from 'axios'

var config = require('../../config')

var frontendUrl= 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl}
})

export default{
    name: 'login',
    data(){
      return{
          cardID: '',
          password: '',

      }
    },
    methods: {
        login: function(cardID,password){
            AXIOS.get('/citizens')
            .then(response => {
                this.events = response.data
            })
            .catch(e => {
                this.errorCitizen = e
            })
            AXIOS.get('/headlibrarians')
            .then(response => {
                this.headlibrarians = response.data
            })
            .catch(e => {
                this.errorHeadLibrarian = e
            })
            AXIOS.get('/librarians')
            .then(response => {
                this.librarians = response.data
            })
            .catch(e => {
                this.errorlibrarian = e
            })
            

        }
    }



}