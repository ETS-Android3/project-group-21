import axios from 'axios'

var config = require('../../config')

var frontendUrl= 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl}
})

function CitizenDto(ID, fullname, address, username, password, isLocal, balance, onlineAccountActivated){
  this.ID=ID
  this.fullname=fullname
  this.address=address
  this.username=username
  this.password=password
  this.isLocal=isLocal
  this.balance=balance
  this.onlineAccountActivated=onlineAccountActivated
}

export default {
  name: 'citizen',
  data(){
    return{
      ID: '',
      name:'',
      address:'',
      userName:'',
      password: '',
      isLocal:'',
      balance:'',
      onlineAccountActivated: '',
      citizens: [],
      newCitizen: '',
      errorCitizen: '',
      response: []
    }
  },
  created: function() {
    //Initializing citizens from backend
    AXIOS.get('/citizens')
      .then(response => {
        //JSON responses are automatically parsed
        this.citizens = response.data
      })
      .catch(e => {
        this.errorCitizen = e
      })
    //Test data
    //const c1 = new CitizenDto(123,'Noshin', 'NewYork','kida','pass',true,0,true)
    //const c2 = new CitizenDto(124,'Bob', 'York','kidb','pass1',true,13,true)
    // Sample initial content
    //this.citizens = [c1,c2]
  },
  methods: {
    createCitizen: function (cardID, fullname, address, username, password, isLocal) {
      AXIOS.post('/citizens/'.concat(cardID),{},
        {params:{
            username: username,
            password: password,
            fullname: fullname,
            address: address,
            isLocal:isLocal,
            balance:0,
            onlineAccountActivated:true
          }})
        .then(response => {
          this.citizens.push(response.data)
          // this.ID=''
          // this.username=''
          // this.password=''
          // this.fullname=''
          // this.address=''
          // this.onlineAccountActivated=''
          // this.isLocal=''
          // this.balance=''
          this.newCitizen=''
          this.errorCitizen=''
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorCitizen = errorMsg
        })

      //create a new citizen and add it to the list of citizen
      //var c = new CitizenDto(ID, fullname, address, username, password, isLocal, 0, true)
      //this.citizens.push(c)
      //Reset the name field for new citizen
      this.newCitizen = ''
    }
  },
}
