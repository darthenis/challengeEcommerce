const { createApp } = Vue
createApp({
    data() {
        return {
            cardNumber: "",
            description: "Easy buy",
            cvv: 000,
            amountBuy: 0,
            bag: JSON.parse(localStorage.getItem("bag")) || [],
            total: 0,
            orderId : null,
            messageAlert: {
                message: "",
                isError: false

            },
            completed: false


        }
    },
    created() {

        this.checkIsLogged().then(res => {

           if(this.isLogged){

            this.calculateTotal();

            this.reserved(); 


           } else {

            location.href = "/web/index.html"

           }
         
        })

          


    },
    methods: {
        reserved() {
            axios.post('/api/client/current/orders', {
                dateTime: new Date(),
                amount: this.total,
                products: [...this.getProducts()]
            },
                { headers: { 'content-type': 'application/json' } })
                .then(res => this.orderId = res.data)
                .catch(err => {
                    console.error(err.response.data)
                })
        },
        async checkIsLogged() {

            axios("/api/clients/auth")
                .then(res => {

                    this.isLogged = true;

                })
                .catch(err => {

                    console.log(err)

                    this.isLogged = false
                })

        },
        handleMessageAlert(message, seconds, isError) {

            this.messageAlert = {
                message,
                isError
            }

            setTimeout(() => this.messageAlert.message = "", seconds * 1000)
        },
        calculateTotal(){

            for(product of this.bag){

                this.total += product.price * product.quantity

            }

        },

        getProducts(){

            let products = [];

            for(let product of this.bag){

                products.push({
                                idProduct: product.id,
                                price: product.price,
                                quantity: product.quantity
                            })

            }

            return products;

        },
        handlerPay(){

            this.isLoading = true;

            axios.post('https://mindhub-huborange.up.railway.app/api/transactions/pay',{    cardNumber : this.cardNumber,
                                                                                                cvv : this.cvv,
                                                                                                amount : this.total})
              .then(res => {

                  this.clearForm();
                  this.handleMessage("Paid succesfully", 3, false)
                  this.isLoading = false;

              }).catch(err => {

                console.log(err)
                this.isLoading = false;
                this.handleMessage(err.response.data, 3, true)

              })

          },
          finishPay(){

            axios.post(`/client/current/orders/${orderId}/tickets`)
                    .then(res => {


                        this.handleMessageAlert("Purchase completed succesfully", 3, false)

                        this.completed = true;

                    })

          }




    },





}).mount("#app")

/*

{
    "dateTime": "2023-03-28T16:12:40",
    "amount": 200.0,
    "products": [
        {
            "idProduct": 1,
            "price": 200.0,
            "quantity": 2
        }
    ]
}


*/