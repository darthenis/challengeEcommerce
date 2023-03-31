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
            orderId : null


        }
    },
    created() {

        this.calculateTotal();

        this.reserved();    


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

        calculateTotal(){

            for(product of this.bag){

                this.total += product.price * product.quantity

            }

        },

        getProducts(){

            let products = [];

            for(product of this.bag){

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

            axios.post('http://localhost:8080/api/transactions/pay',{...this.data, amount : parseInt(this.data.amount)})
              .then(res => {

                  this.clearForm();
                  this.handleMessage("Paid succesfully", 3, false)
                  this.isLoading = false;

              }).catch(err => {

                console.log(err)
                this.isLoading = false;
                this.handleMessage(err.response.data, 3, true)

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