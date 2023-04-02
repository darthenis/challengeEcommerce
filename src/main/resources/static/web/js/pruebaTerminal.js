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
            completed: false,
            isLoading: false,
            count: 3,
            isLogged : false
            


        }
    },
    created() {
        this.calculateTotal()
        this.checkIsLogged()

        let parameterUrl = location.search
        let parameters = new URLSearchParams(parameterUrl)
        this.orderId = parameters.get("order")

    },
    methods: {
        checkIsLogged() {

            axios("/api/clients/auth")
                .then(res => {
                    console.log(res)
                    this.isLogged = true;

                })
                .catch(err => {

                    location.href = "/web/index.html"

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

                if(product.discount){

                    this.total += ( product.price - ((product.price / 100) * product.discount))  * product.quantity

                } else {

                    this.total += product.price * product.quantity
                }

        

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

            setTimeout(() => {

                axios.post('https://numba-bank.up.railway.app/api/clients/transaction/buy', {
                    number: this.cardNumber,
                    cvv: this.cvv,
                    transactionAmount: this.total,
                    description: "Easy Buy - Debit"
    
                })
                    .then(res => {
                        this.finishPay()
    
                    }).catch(err => {
    
                        this.isLoading = false;
                        this.handleMessage("Payment could not be processed.", 3, true)
    
                    })

            }, 3000)

        },
        finishPay() {

            axios.post(`/api/client/current/orders/${this.orderId}/tickets`)
                .then(res => {
                    localStorage.clear()

                    this.isLoading = false; this.completed = true

                    setTimeout(() => location.href = "/web/purchases.html", 3000)

                    setInterval(() => this.count--, 1000)

                })
                .catch(err => {

                    this.handleMessageAlert("Error processing purchase, please contact support")
                    
                    console.error(err.data)
                
                })

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