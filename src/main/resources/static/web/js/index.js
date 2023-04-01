const { createApp } = Vue;

createApp({
    data() {
        return {
            productData: null,
            bag: JSON.parse(localStorage.getItem("bag")) || [],
            active: null,
            totalCart: 0,
            totalCartQuantity: 0,
            id: null,
            top4LastUpdated: [],
            top4offersProducts: [],
            offersEffect: [],
            topUpdatedEffect: [],
            isLogged: false,
            navActive: null,
            favs: [],
            searchProduct: "",
            counter: {
                days: 0,
                hours: 0,
                minutes: 0,
                seconds: 0
            },
            messageAlert: {
                message : "",
                isError: false
            },
            scrollheader : false

        }
    },
    created() {

        let countDownDate = new Date(Date.UTC(2023, 3, 2, 20, 33, 0)).getTime();

        this.regretCount(countDownDate)

        this.loadData()

        document.addEventListener("scroll", () => this.isVisible("offer"));

        document.addEventListener("scroll", () => this.isVisible("top"));

        this.quantityTotalCart()

        this.priceTotalCart()
    },
    methods: {
        checkIsLogged() {

            axios("/api/clients/auth")
                .then(res => {

                    this.isLogged = true;

                })
                .catch(err => {

                    this.isLogged = false
                })

        },
        loadData() {

            axios.get("/api/products/last/updated")
                .then(res => {
                    this.top4LastUpdated = res.data.sort((a,b) => a.id - b.id);
                    this.checkIsLogged()

                }).catch(err => console.log(err))

            axios.get("/api/products/last/offers")
                .then(res => {
                    this.top4offersProducts = res.data.sort((a,b) => a.id - b.id);

                }).catch(err => console.log(err))

            axios.get("/api/client/current/favorites")
                .then(res => this.favs = res.data)
        },
        handleNavResponsive() {

            if (!this.navActive) {

                this.navActive = true;

            } else {

                this.navActive = false;

            }

        },
        handleMessageAlert(message, seconds, isError){

            this.messageAlert = {
                message,
                isError
            }

            setTimeout(() => this.messageAlert.message = "", seconds * 1000)

        },
        /*-------------------LOGOUT--------------------*/
        logout() {

            axios.post('/api/logout').then(response => this.isLogged = false)

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
                        this.handleMessageAlert("Item added to cart", 3, false)
                        return { ...item, quantity: (item.quantity + 1) }
                    } else {
                        return item;
                    }
                })
            } else {

                let product = { ...object, quantity: 1 }
                this.bag.push(product)
                this.handleMessageAlert("Item added to cart", 3, false)
            }
            localStorage.setItem("bag", JSON.stringify(this.bag))
            this.quantityTotalCart()
            this.priceTotalCart()

          
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

            if (!this.active) {
                this.active = true;
                this.navActive = this.navActive != null ? false : null;
            } else {
                this.active = !this.active;
            }

        },
        isVisible(type) {

            let array;

            let preId;

            if (type == "offer") {

                this.offersEffect = []

                array = this.top4offersProducts;

                preId = "product-"

            } else {

                this.topUpdatedEffect = []

                array = this.top4LastUpdated;

                preId = "productUpdated-"

            }

            if(window.scrollY > 100) this.scrollheader = true;

            else this.scrollheader = false;

            for (product of array) {

                const element = document.getElementById(preId + product.id);

                if (element) {
                    let rect = element.getBoundingClientRect();
                    let windowHeight = (window.innerHeight || document.documentElement.clientHeight);
                    let windowWidth = (window.innerWidth || document.documentElement.clientWidth);

                    // Verificar si el elemento está dentro de la ventana vertical
                    let vertInView = (rect.top <= windowHeight) && ((rect.top + rect.height) >= 0);

                    // Verificar si el elemento está dentro de la ventana horizontal
                    let horInView = (rect.left <= windowWidth) && ((rect.left + rect.width) >= 0);

                    type == "offer" ? this.offersEffect.push(vertInView && horInView)
                        : this.topUpdatedEffect.push(vertInView && horInView)
                }

            }

        },
        getWithDiscount(price, discount) {

            if (!discount) {

                return this.formatDollar(price);

            } else {

                return this.formatDollar(price - ((price / 100) * discount))

            }

        },
        avaregeStars(rates) {

            let five = 0;

            let four = 0;

            let three = 0;

            let two = 0;

            let one = 0;

            for (let rate of rates) {

                switch (rate.stars) {
                    case "ONE":
                        one++;
                        break;
                    case "TWO":
                        two++;
                        break;
                    case "THREE":
                        three++;
                        break;
                    case "FOUR":
                        four++;
                        break;
                    case "FIVE":
                        five++;
                        break;
                }

            }

            return ((five * 5) + (four * 4) + (three * 3) + (two * 2) + (one * 1)) / rates.length || "0.0"

        },

        counterStars(rates) {

            let rounded = 0;

            rounded += Math.round(this.avaregeStars(rates));

            let star = '<svg aria-hidden="true" class="w-5 h-5 text-yellow-300" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><title>First star</title><path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path></svg>'

            let starEmpty = '<svg aria-hidden="true" class="w-5 h-5 text-yellow-300" fill="gray" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><title>First star</title><path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path></svg>'

            let html = "";

            for (let i = 1; i <= rounded; i++) {

                html += star;

            }

            if ((5 - rounded) != 0) {

                for (let i = rounded; i < 5; i++) {

                    rounded++

                    html += starEmpty;

                }

            }

            return html

        },
        addFavorites(product) {

            if (this.favs.find(fav => fav.productId == product.id)) {

                axios.delete("/api/client/current/favorites/" + this.favs.find(fav => fav.productId == product.id).id)
                    .then(res => {

                        this.handleMessageAlert("Item quited from favorites", 3, false)

                        this.loadData();

                    }).catch(err => console.log(err))


            } else {

                const data = {
                    name: product.name,
                    imgUrl: product.imgsUrls[0],
                    price: product.price,
                    productId: product.id,
                    description: product.description,
                    stock: product.stock
                }
    
                axios.post("/api/client/current/favorites", { ...data })
                    .then(res => {
    
                        this.loadData();
    
                        this.handleMessageAlert("Item added to favorites", 3, false)
    
                    }).catch(err => console.log(err))

            }

         

        },
        checkedFavoritesAdded() {

            this.top4LastUpdated = this.top4LastUpdated.map(updated => {

                if (this.favs.find(fav => fav.productId == updated.id)) {

                    return { ...updated, inFavs: true }

                } else {

                    return { ...updated, inFavs: false }

                }

            })

            this.top4offersProducts = this.top4offersProducts.map(updated => {

                if (this.favs.find(fav => fav.productId == updated.id)) {

                    return { ...updated, inFavs: true }

                } else {

                    return { ...updated, inFavs: false }

                }

            })

        },
        handleSearch() {

            location.href = "/web/shop.html?search=" + this.searchProduct;

        },
        regretCount(countDownDate) {

            setInterval(() => {

                let now = new Date().getTime();

                let distance = countDownDate - now;

                this.counter.days = Math.floor(distance / (1000 * 60 * 60 * 24));
                this.counter.hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                this.counter.minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                this.counter.seconds = Math.floor((distance % (1000 * 60)) / 1000)

            }, 1000);


        }

    },
    computed: {

        checkedFavorites() {

            this.checkedFavoritesAdded();

        }

    }
}).mount("#app")