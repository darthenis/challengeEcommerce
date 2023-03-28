const { createApp } = Vue
createApp({
    data() {
        return {
            product: {
                img: ["https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6514/6514490_sd.jpg", "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6514/6514490cv12d.jpg;maxHeight=2000;maxWidth=2000", "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6514/6514490cv16d.jpg;maxHeight=2000;maxWidth=2000"]
            },
            selectImg: "",
            dataProduct: null

        }
    },
    created() {
        /* let parameterUrl = location.search
        let parameters = new URLSearchParams(parameterUrl)
        this.id = parameters.get("id") */
        /*   this.loadData() */
        this.selectImg = this.product.img[0]


    },
    methods: {
        loadData() {
            axios.get("api/products/6")
                .then(response => {
                    this.dataProduct = response.data
                    console.log(this.dataProduct)
                    this.params
                })
        },

        changeImg(img) {
            this.selectImg = img
        },

        params() {

            axios.get("/api/accounts/" + this.id)
                .then(res => {
                    this.accountSelect = res.data
                    this.transactionFilter = this.accountSelect.transactions

                })
        },

    },





}).mount("#app")