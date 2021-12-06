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
          errorLibrarian:'',
          errorLogin:'',
      }
    },
    methods: {
        login: function(cardID,password){
            this.errorLogin='';
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
                this.errorLogin = "User ID and password do not match!";
            }
            // function call
            loginVerification(cardID, password)

        }
    }



}
