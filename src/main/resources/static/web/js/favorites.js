const { createApp } = Vue;

createApp({
    data() {
        return {

            active: null,
            activeFade: false,
            favorites: [],
            visibleArticle: [],
            messageAlert: {
                message: "",
                isError: false
            },
            bag: JSON.parse(localStorage.getItem("bag")) || [],
            active: null,
            totalCart: 0,
            totalCartQuantity: 0,
            isLogged: false,
            navActive: null
    
        }
    },
    created() {

        this.quantityTotalCart()

        this.priceTotalCart()

        document.addEventListener("scroll", () => this.isVisible());

        this.loadData();

        this.checkIsLogged()

    },
    methods: {
        loadData() {

            axios.get("/api/client/current/favorites")
                .then(res => {

                    console.log(res)

                    this.favorites = res.data;

                }).catch(err => {

                    console.log(err)

                })

        },
        checkIsLogged() {

            axios("/api/clients/auth")
                .then(res => {

                    this.isLogged = true;

                })
                .catch(err => {

                    console.log(err)

                    this.isLogged = false
                })

        },
        handleSearch() {

            location.href = "/web/shop.html?search=" + this.searchProduct;

        },
        handleNavResponsive() {

            if (!this.navActive) {

                this.navActive = true;

            } else {

                this.navActive = false;

            }

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

            console.log("aca")

            axios.get("/api/products/"+object.productId)
                    .then(res => {

                        if (this.bag.find(item => item.id == object.id)) {
                            this.bag = this.bag.map(item => {
                                if (item.id == object.id && object.stock > 0 && object.stock > item.quantity) {
                                    this.handleMessageAlert("Item added to cart", 3, false)
                                    return { ...item, quantity: (item.quantity + 1) }
                                } else {
                                    return item;
                                }
                            })
                        } else if(object.stock > 0){
                            
                            let product = { ...object, quantity: 1 }
                            this.bag.push(product)
                            this.handleMessageAlert("Item added to cart", 3, false)
                        }
                        localStorage.setItem("bag", JSON.stringify(this.bag))
                        this.quantityTotalCart()
                        this.priceTotalCart()

                    })

           
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

            if (this.active == null) {
                this.active = true;
            } else {
                this.active = !this.active;
            }

        },
        handleMessageAlert(message, seconds, isError) {

            this.messageAlert = {
                message,
                isError
            }

            setTimeout(() => this.messageAlert.message = "", seconds * 1000)
        },
        toggleCar() {

            if (this.active == null) {

                this.active = true;

            } else {

                this.active = !this.active;

            }

            console.log(this.active)

        },
        deleteFavorite(id) {

            axios.delete("/api/client/current/favorites/" + id)
                .then(res => {

                    this.loadData();

                    this.handleMessageAlert("Favorite deleted succesfully", 2, false)

                })
                .catch(err => {

                    console.log(err)
                    this.handleMessageAlert(err.response.data, 2, false)

                })

        },

        isVisible() {

            this.visibleArticle = [];

            for (favorite of this.favorites) {

                const element = document.getElementById("article-" + favorite.id);

                if (element) {
                    let rect = element.getBoundingClientRect();
                    let windowHeight = (window.innerHeight || document.documentElement.clientHeight);
                    let windowWidth = (window.innerWidth || document.documentElement.clientWidth);

                    // Verificar si el elemento está dentro de la ventana vertical
                    let vertInView = (rect.top <= windowHeight) && ((rect.top + rect.height) >= 0);

                    // Verificar si el elemento está dentro de la ventana horizontal
                    let horInView = (rect.left <= windowWidth) && ((rect.left + rect.width) >= 0);

                    this.visibleArticle.push(vertInView && horInView);
                }

            }

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

    },
    mounted() {



    }


}).mount("#app")