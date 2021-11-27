import axios from 'axios'

var config = require('../../config')

var frontendUrl= 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl}
})

function LibrarianDto(cardID, fullname, address, username, password){
  this.cardID=cardID
  this.fullname=fullname
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
    createLibrarian: function (cardID, fullname, address, username, password) {
      AXIOS.post('/librarians/'.concat(cardID),{},
        {params:{
            username: username,
            password: password,
            fullname: fullname,
            address: address

          }})
        .then(response => {
          this.librarians.push(response.data)
          this.newLibrarian=''
          this.errorLibrarian=''
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorLibrarian = errorMsg
        })

      //create a new hl and add it to the list of hl
      // var c = new LibrarianDto(cardID, fullname, address, username, password)
      // this.librarians.push(c)
      //Reset the name field for new hl
      this.newLibrarian = ''
    }
  },
}
