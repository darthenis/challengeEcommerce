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
            orderId: null,
            messageAlert: {
                message: "",
                isError: false

            },
            completed: false


        }
    },
    created() {
        this.calculateTotal()
        this.reserved()
        this.checkIsLogged().then(res => {
            if (this.isLogged) {
                this.calculateTotal();
                ;
            } else {

                /*  location.href = "/web/index.html" */

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
                .then(res => {
                    this.orderId = res.data
                    console.log(res.data)
                })
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
        calculateTotal() {

            for (product of this.bag) {

                this.total += product.price * product.quantity

            }

        },

        getProducts() {

            let products = [];

            for (let product of this.bag) {

                products.push({
                    idProduct: product.id,
                    price: product.price,
                    quantity: product.quantity
                })

            }

            return products;

        },
        handlerPay() {

            this.isLoading = true;

            axios.post('https://numba-bank.up.railway.app/api/clients/transaction/buy', {
                number: this.cardNumber,
                cvv: this.cvv,
                transactionAmount: this.total,
                description: "Easy Buy - Debit"

            })
                .then(res => {
                    this.finishPay()

                }).catch(err => {

                    console.log(err)
                    this.isLoading = false;
                    this.handleMessage(err, 3, true)

                })

        },
        finishPay() {

            axios.post(`/api/client/current/orders/${this.orderId}/tickets`)
                .then(res => {
                    localStorage.clear()
                    location.href = "/web/purchases.html"

                })
                .catch(err =>
                    console.error(err.data))

        },

        /*------------------FORMATEO A MONEDA TIPO DOLAR US--------------*/
        formatDollar(price) {
            let USDollar = new Intl.NumberFormat('en-US', {
                style: 'currency',
                currency: 'USD',
            });
            return USDollar.format(price)
        },

        resetPayments() {
            this.radioPayments = null
        },

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