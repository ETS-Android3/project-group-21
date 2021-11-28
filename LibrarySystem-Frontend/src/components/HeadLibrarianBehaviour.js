import axios from 'axios'

var config = require('../../config')

var frontendUrl= 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl}
})

function HeadLibrarianDto(cardID, name, address, username, password){
  this.cardID=cardID
  this.name=name
  this.address=address
  this.username=username
  this.password=password

}

export default {
  name: 'headlibrarian',
  data(){
    return{
        cardID: '',
        name:'',
        address:'',
        username:'',
        password: '',
        headlibrarians: [],
        newHeadLibrarian: '',
        errorHeadLibrarian: '',
        response: []
    }
  },
  created: function() {
    //Initializing headlibrarians from backend
    AXIOS.get('/headlibrarian')
      .then(response => {
        //JSON responses are automatically parsed
        this.headlibrarians = response.data
      })
      .catch(e => {
        this.errorHeadLibrarian = e
      })
    // //Test data
    // const c1 = new HeadLibrarianDto(123,'Noshin', 'NewYork','kida','pass')
    // const c2 = new HeadLibrarianDto(124,'Bob', 'York','kidb','pass1')
    // // Sample initial content
    // this.headlibrarians = [c1,c2]
  },
  methods: {
    createHeadLibrarian: function (cardID, name, address, username, password) {
      AXIOS.post('/headlibrarian/'.concat(cardID),{},
        {params:{
            name: name,
            address: address,
            username: username,
            password: password
        }})
        .then(response => {
          this.headlibrarians.push(response.data)
          this.newHeadLibrarian=''
          this.errorHeadLibrarian=''
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorHeadLibrarian = errorMsg
        })

      // //create a new hl and add it to the list of hl
      // var c = new HeadLibrarianDto(cardID, name, address, username, password)
      // this.headlibrarians.push(c)
      //Reset the name field for new hl
      this.newHeadLibrarian = ''
    },
    updateHeadLibrarian: function (cardID, name, address, username, password) {
      AXIOS.patch('/headlibrarian/'.concat(cardID),{},
        {params:{
            name: name,
            address: address,
            username: username,
            password: password
        }})
        .then(response => {
          AXIOS.get('/headlibrarian')
          .then(response => {
        //JSON responses are automatically parsed
          this.headlibrarians = response.data
      })
          .catch(e => {
          this.errorHeadLibrarian = e
      })
          this.newHeadLibrarian=''
          this.errorHeadLibrarian=''
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorHeadLibrarian = errorMsg
        })
      this.newHeadLibrarian = ''
    },
    deleteHeadLibrarian: function (cardID) {
      AXIOS.delete('/headlibrarian/'.concat(cardID),{},{})
        .then(response => {
          AXIOS.get('/headlibrarian')
          .then(response => {
        //JSON responses are automatically parsed
          this.headlibrarians = response.data
      })
          .catch(e => {
          this.errorHeadLibrarian = e
      })
          this.newHeadLibrarian=''
          this.errorHeadLibrarian=''
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorHeadLibrarian = errorMsg
        })
      this.newHeadLibrarian = ''
    }
  },
}
