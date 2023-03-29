const { createApp } = Vue
createApp({
    data() {
        return {
            selectImg: "",
            dataProduct: null,
            radioPayments: null,
            bag: JSON.parse(localStorage.getItem("bag")) || [],
            active: null,
            totalCart: 0,
            totalCartQuantity: 0


        }
    },
    created() {
        this.loadData()
        let parameterUrl = location.search
        let parameters = new URLSearchParams(parameterUrl)
        this.id = parameters.get("id")

    },


    methods: {
        loadData() {
            axios.get("/api/products/6")
                .then(response => {
                    this.dataProduct = response.data
                    console.log(this.dataProduct)
                    this.selectImg = this.dataProduct.imgsUrls[0]
                    this.priceTotalCart()
                })
        },

        /*---------CAMBIO DE IMAGEN DE PRODUCTO-----------------*/

        changeImg(img) {
            this.selectImg = img
        },

        /*----------------PARAMS ID PRODUCTO-------------------*/
        params() {

            axios.get("/api/products/" + this.id)
                .then(res => {
                    this.accountSelect = res.data
                    this.transactionFilter = this.accountSelect.transactions

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
        /*---------------AGREGAR AL CARRITO Y CANTIDAD----------------*/
        saveBag(object) {
            if (this.bag.find(item => item.id == object.id)) {
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
            this.quantityTotalCart()
            this.priceTotalCart()
        },

        /* -------------QUITAR CANTIDAD DEL CARRITO -------------*/
        outProductBag(object) {
            if (this.bag.find(item => item.id == object.id)) {
                object.quantity--
                if (object.quantity === 0) {
                    let index = this.bag.indexOf(this.bag.find(prod => prod.id === object))
                    this.bag.splice(index, 1)
                }
            }
            localStorage.setItem("bag", JSON.stringify(this.bag))
            this.quantityTotalCart()
            this.priceTotalCart()
        },

        /*--------------QUITAR POR COMPLETO DEL CARRITO-------------*/

        removeItem(object) {
            if (this.bag.find(item => item.id == object.id)) {
                let index = this.bag.indexOf(this.bag.find(prod => prod.id === object))
                this.bag.splice(index, 1)
            }
            localStorage.setItem("bag", JSON.stringify(this.bag))
            this.quantityTotalCart()
            this.priceTotalCart()
        },


        /*--------------CALCULO DE TOTAL CARRITO--------------*/
        priceTotalCart() {
            let totalCount = 0
            this.bag.forEach(object => {
                totalCount += object.price * object.quantity
            })
            this.totalCart = totalCount
        },

        /*-----------------CANTIDAD DE PRODUCTOS-----------------*/
        quantityTotalCart() {
            let totalCount = 0
            this.bag.forEach(object => {
                totalCount += object.quantity
            })
            this.totalCartQuantity = totalCount
        },


        /*--------------TOGGLE CART--------------*/
        toggleCart() {

            if (this.active == null) {
                this.active = true;
            } else {
                this.active = !this.active;
            }

        },




    },

}).mount("#app")