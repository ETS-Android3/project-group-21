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

export default {
    name: 'itemlist',
    data () {
      return {
        libraryitem: {
            barcode:'',
            title:'',
            type:'',
            isReservable:'',
            isReserved:'',
            loanPeriod:'',
        },

        libraryitems: [],
        errorLibraryItem: '',
        response: []
      }
    },

    created: function () {

        //Initializing citizens from backend
        AXIOS.get('/libraryitems')
        .then(response => {
        //JSON responses are automatically parsed
        this.libraryitems = response.data
        })
        .catch(e => {
        this.errorLibraryItem = e
        })
      },
    
    // methods: {
    //     createLibraryItem: function (barcode, type, title, isReservable, loanPeriod) {

    //         AXIOS.post('/libraryitems/'.concat(barcode),{},
    //             {params:{
    //                 type : type,
    //                 title : title,
    //                 isReservable : isReservable,
    //                 isReserved : false,
    //                 loanPeriod : loanPeriod
    //             }})
    //             .then(response => {
    //             this.libraryitems.push(response.data)
    //             this.barcode=''
    //             this.type=''
    //             this.title=''
    //             this.isReservable=''
    //             this.isReserved=''
    //             this.loanPeriod=''
    //             })
    //             .catch(e => {
    //               var errorMsg = e.response.data.message
    //               console.log(errorMsg)
    //               this.errorReservation = errorMsg
    //             })
    //       this.newLibraryItem = ''
    //     }
    // }
  }
