const { createApp } = Vue
createApp({
    data() {
        return {
            cardNumber: "",
            description: "Easy buy",
            cvv: 000,
            amountBuy: 0,


        }
    },
    created() {




    },
    methods: {
        Pay() {
            console.log(this.cardNumber)
            console.log(this.description)
            console.log(this.cvv)
            console.log(this.amountBuy)
            axios.post('http://localhost:8080/api/clients/transaction/buy', {
                number: this.cardNumber,
                cvv: this.cvv,
                transactionAmount: this.amountBuy,
                description: this.description,
            },
                { headers: { 'content-type': 'application/json' } })
                .catch(response => {
                    console.error(error.response.error)
                })


        },




    },





}).mount("#app")