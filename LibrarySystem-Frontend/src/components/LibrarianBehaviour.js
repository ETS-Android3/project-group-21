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

function LibrarianDto(cardID, name, address, username, password){
  this.cardID=cardID
  this.name=name
  this.address=address
  this.username=username
  this.password=password

}

export default {
  name: 'librarian',
  data(){
    return{
        cardID: '',
        name:'',
        address:'',
        username:'',
        password: '',
        librarians: [],
        newLibrarian: '',
        errorLibrarian: '',
        response: []
   
    }
  },
  created: function() {
    //Initializing librarians from backend
    AXIOS.get('/librarians')
      .then(response => {
        //JSON responses are automatically parsed
        this.librarians = response.data
      })
      .catch(e => {
        this.errorLibrarian = e
      })
    //Test data
    // const c1 = new LibrarianDto(123,'Noshin', 'NewYork','kida','pass')
    // const c2 = new LibrarianDto(124,'Bob', 'York','kidb','pass1')
    // Sample initial content
    // this.librarians = [c1,c2]
  },
  methods: {
    createLibrarian: function (cardID, name, address, username, password) {
      AXIOS.post('/librarians/'.concat(cardID),{},
        {params:{
            username: username,
            password: password,
            name: name,
            address: address

          }})
        .then(response => {
          this.librarians.push(response.data)
          this.cardID=''
          this.username=''
          this.password=''
          this.name=''
          this.address=''
          this.newLibrarian=''
          this.errorLibrarian=''
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorLibrarian = errorMsg
        })

      //create a new hl and add it to the list of hl
      // var c = new LibrarianDto(cardID, name, address, username, password)
      // this.librarians.push(c)
      //Reset the name field for new hl
      this.newLibrarian = ''
    },
    updateLibrarian: function (cardID, name, address, username, password) {
      AXIOS.patch('/librarians/'.concat(cardID),{},
        {params:{
            name: name,
            address: address,
            username: username,
            password: password
        }})
        .then(response => {
          AXIOS.get('/librarians')
          .then(response => {
        //JSON responses are automatically parsed
          this.librarians = response.data
      })
          .catch(e => {
          this.errorLibrarian = e
      })
          this.newLibrarian=''
          this.errorLibrarian=''
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorLibrarian = errorMsg
        })
      this.newLibrarian = ''
    },
    deleteLibrarian: function (cardID) {
      AXIOS.delete('/librarians/'.concat(cardID),{},{})
        .then(response => {
          AXIOS.get('/librarians')
          .then(response => {
        //JSON responses are automatically parsed
          this.librarians = response.data
      })
          .catch(e => {
          this.errorLibrarian = e
      })
          this.newLibrarian=''
          this.errorLibrarian=''
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorLibrarian = errorMsg
        })
      this.newLibrarian = ''
    }
  },
}
