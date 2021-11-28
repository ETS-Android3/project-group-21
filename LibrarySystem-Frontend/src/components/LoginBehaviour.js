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
          citizens: [],
          headlibrarian: [],
          librarians: [],
          errorCitizen: '',
          errorHeadLibrarian: '',
          errorLibrarian:''
      }
    },
    methods: {
        login: function(cardID,password){
            AXIOS.get('/citizens')
            .then(response => {
                this.citizens = response.data
            })
            .catch(e => {
                this.errorCitizen = e
            })
            AXIOS.get('/headlibrarian')
            .then(response => {
                this.headlibrarian = response.data
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

            const loginVerification = (cardID, password) => {
                for (const citizen of this.citizens){
                    if ( cardID.localeCompare(citizen.id)==0 && password.localeCompare(citizen.password)==0) {
                        this.$router.push('/reservation')
                    }
                }
                for (const librarian of this.librarians){
                    if (cardID.localeCompare(librarian.id) == 0 && password.localeCompare(librarian.password) == 0) {
                        this.$router.push('/librarianhome')
                    }
                }
                for (const aheadlibrarian of this.headlibrarian){
                    if (cardID.localeCompare(aheadlibrarian.id) == 0 && password.localeCompare(aheadlibrarian.password) == 0) {
                        this.$router.push('/headlibrarianhome')
                    }
                }
            }
            // function call
            loginVerification(cardID, password)

        }
    }



}
