const { createApp } = Vue
createApp({
    data() {
        return {
            selectImg: "",
            dataProduct: null,
            radioPayments: null,
            bag: JSON.parse(localStorage.getItem("bag")) || [],
            active: null,
            totalCart: 0
        }
    },
    created() {
        let parameterUrl = location.search
        let parameters = new URLSearchParams(parameterUrl)
        this.id = parameters.get("id")
        this.loadData()
    },

    beforeMount() {
        this.priceTotalCart()
    },


    methods: {
        loadData() {
            axios.get("/api/products/6")
                .then(response => {
                    this.dataProduct = response.data
                    console.log(this.dataProduct)
                    this.params
                    this.selectImg = this.dataProduct.imgsUrls[0]
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

        saveBag(object) {
            if (this.bag.find(item => item.id == object.id)) {
                console.log("hola")
                this.bag = this.bag.map(item => {
                    if (item.id == object.id && object.stock > 0 && object.stock > item.quantity) {
                        return { ...item, quantity: (item.quantity + 1) }
                    } else {
                        return item;
                    }
                })
            } else {

                let product = { ...object, quantity: 1 }
                this.bag.push(product)
            }
            localStorage.setItem("bag", JSON.stringify(this.bag))

        },

        outProductBag(object) {
            if (this.bag.find(item => item.id == object.id)) {
                this.bag = this.bag.map(item => {
                    if (item.id == object.id && object.quantity > 0) {
                        console.log("hola")
                        return { ...item, quantity: (item.quantity - 1) }
                    } else {
                        this.bag.pop(item);
                    }
                    localStorage.setItem("bag", JSON.stringify(this.bag))
                })
            }
        },

        priceTotalCart() {
            let total = 0
            this.bag.foreach(object => {
                if (object.quantity > 0) {
                    total += object.price * object.quantity
                } else {
                    total += object.price
                }
            })
            this.TotalCart = total
        },


        toggleCar() {

            if (this.active == null) {
                this.active = true;
            } else {
                this.active = !this.active;
            }

        },




    },

}).mount("#app")