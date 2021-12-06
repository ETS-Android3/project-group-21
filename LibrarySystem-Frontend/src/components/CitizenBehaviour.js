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
//comment
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
//this is a coment
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
      this.errorCitizen = '';
      AXIOS.get('/citizens')
      .then(response => {
        //JSON responses are automatically parsed
        this.citizens = response.data
      })
      .catch(e => {
        this.errorCitizen = e
      })
      for (const citizen of this.citizens){
        if (cardID.localeCompare(citizen.id)==0) {
            this.errorCitizen = "Cannot create due to duplicate ID!";
        }
    }
    if(this.errorCitizen.localeCompare("")==0){
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
          this.ID=''
          this.userName=''
          this.password=''
          this.name=''
          this.address=''
          this.onlineAccountActivated=''
          this.isLocal=''
          this.balance=''
          this.newCitizen=''
          this.errorCitizen=''
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorCitizen = errorMsg
        })
        this.newCitizen = '';
      }
    },
    updateCitizen: function (ID,name,address,userName,password,isLocal) {
      AXIOS.patch('/citizens/'.concat(ID),{},
        {params:{
            name: name,
            address: address,
            userName: userName,
            password: password,
            isLocal: isLocal
        }})
        .then(response => {
          AXIOS.get('/citizens')
          .then(response => {
        //JSON responses are automatically parsed
          this.citizens = response.data
      })
          .catch(e => {
          this.errorCitizen = e
      })
          this.newCitizen=''
          this.errorCitizen=''
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorCitizen = errorMsg
        })
      this.newCitizen = ''
    },
    deleteCitizen: function (ID) {
      AXIOS.delete('/citizens/'.concat(ID),{},{})
        .then(response => {
          AXIOS.get('/citizens')
          .then(response => {
        //JSON responses are automatically parsed
          this.citizens = response.data
      })
          .catch(e => {
          this.errorCitizen = e
      })
          this.newCitizen=''
          this.errorCitizen=''
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorCitizen = errorMsg
        })
      this.newCitizen = ''
    }
  },
}
