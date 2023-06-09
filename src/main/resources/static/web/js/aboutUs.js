const {createApp} = Vue;

createApp({
    data(){
        return{
            navActive : null,
            active : null,
            bag: JSON.parse(localStorage.getItem("bag")) || [],
            totalCartQuantity: 0,
            totalCart: 0,
            isLogged: false

        }
    },
    created(){

        this.checkIsLogged()

        this.quantityTotalCart()

        this.priceTotalCart()

    },
    methods:{
        /*-------------------LOGOUT--------------------*/
        logout() {
            axios.post('/api/logout').then(response => this.isLogged = false)
        },
        checkIsLogged(){

            axios("/api/clients/auth")
                .then(res => {

                    this.isLogged = true;

                })
                .catch(err => { 
                    
                    console.log(err)
                    
                    this.isLogged = false})

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

        /* -------------QUITAR CANTIDAD DEL CARRITO -------------*/
        outProductBag(object) {
            if (object.quantity <= 0) {
                return
            }
            if (this.bag.find(item => item.id == object.id)) {
                object.quantity--
                if (object.quantity === 0) {
                    this.bag = this.bag.filter(item => item.quantity !== 0);
                }
            }
            localStorage.setItem("bag", JSON.stringify(this.bag))
            this.quantityTotalCart()
            this.priceTotalCart()
        },

        /*--------------QUITAR POR COMPLETO DEL CARRITO-------------*/

        removeItem(object) {
            if (this.bag.find(item => item.id == object.id)) {
                let index = this.bag.findIndex(item => item.id == object.id)
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

                if(object.discount > 0){

                    let priceDiscount = object.price - ((object.price / 100) * object.discount);

                    totalCount += priceDiscount * object.quantity

                } else {

                    totalCount += object.price * object.quantity
                }

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

            if (!this.active) {
                this.active = true;
                this.navActive = this.navActive != null ? false : null;
            } else {
                this.active = !this.active;
            }

        },
        handleNavResponsive() {

            if (!this.navActive) {

                this.navActive = true;

            } else {

                this.navActive = false;

            }

        },
        handleSearch(){

            location.href = "/web/shop.html?search="+this.searchProduct;

        },
        checkoutHandler(){

            this.isLoading = true;

            axios.post('/api/client/current/orders', {
                dateTime: new Date(),
                amount: this.totalCart,
                products: [...this.getProducts()]
            },
                { headers: { 'content-type': 'application/json' } })
                .then(res => {
                    console.log(res.data)
                    location.href = "/web/terminalpay.html?order="+res.data;
                })
                .catch(err => {
                    let item = this.bag.filter(item => item.id == err.response.data.split(":")[1].trim())
                    this.handleMessageAlert("No stock for "+item[0].name, 3, true)
                    this.isLoading = false;
                })


    

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
    }
}).mount("#app")