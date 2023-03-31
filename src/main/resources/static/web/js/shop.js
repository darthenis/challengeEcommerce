const { createApp } = Vue;

createApp({
    data() {
        return {
            products: [],
            filteredProducts: [],
            totalFilteredProducts: [],
            activeMenuCategories: null,
            filterTags: [],
            messageAlert: {
                message: "",
                isError: false
            },
            visibleArticle: [],
            minPrice: "",
            maxPrice: "",
            sortSeleted: "",
            searchProduct: "",
            initPage: 0,
            endPage: 9,
            favs: [],
            isLogged: false,
            active: null,
            bag: JSON.parse(localStorage.getItem("bag")) || [],
            totalCartQuantity: 0,
            totalCart: 0,
            navActive: null
        }
    },
    created() {

        let valueSearch = this.getParam("search");

        if (valueSearch) this.searchProduct = valueSearch;

        this.loadData().then(() => this.setCheckBox());

        document.addEventListener("scroll", () => this.isVisible());

        this.checkIsLogged()

        this.quantityTotalCart()

        this.priceTotalCart()

    },
    methods: {
        logout() {

            axios.post("/api/logout")
                .then(() => {

                    this.isLogged = false;
                    this.handleMessageAlert("Sign out successfully", 3, false)

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
        getParam(key) {

            let parameterUrl = location.search
            let parameters = new URLSearchParams(parameterUrl)
            return parameters.get(key)

        },
        async loadData() {

            axios.get("/api/products")
                .then(res => {
                    console.log(res)
                    this.products = res.data;
                    this.filterProducts = res.data;
                })
                .catch(err => {

                    console.log(err)

                })

            axios.get("/api/client/current/favorites")
                .then(res => this.favs = res.data)

        },
        setCheckBox() {

            let string = this.getParam("filter");

            let element;

            switch (string) {
                case "Appliance":
                    element = document.getElementById("appli1");
                    break;
                case "Audio":
                    element = document.getElementById("aud1");
                    break;
                case "Furniture":
                    element = document.getElementById("furn1");
                    break;
                case "Health":
                    element = document.getElementById("heal1");
                    break;
                case "Kids":
                    element = document.getElementById("kids1");
                    break;
                case "Tecnology":
                    element = document.getElementById("tecn1");
                    break;
                case "Video":
                    element = document.getElementById("vid1");
                    break;
            }

            if (element) element.click();

            let element2 = document.getElementById(string);

            if (element2) element2.click();


        },
        handlePages(isNext) {

            window.scrollTo(0, 0);

            if (isNext && this.totalFilteredProducts.length > this.endPage) {

                this.initPage += 9;

                this.endPage += 9

            } else if (this.initPage != 0) {

                this.initPage -= 9;

                this.endPage -= 9

            }




        },
        handlerMenuCategories() {

            if (!this.activeMenuCategories) {

                this.activeMenuCategories = true;

            } else {

                this.activeMenuCategories = false;

            }

        },
        formatCurrency(value) {

            let USDollar = new Intl.NumberFormat('en-US', {
                style: 'currency',
                currency: 'USD',
            });

            return USDollar.format(value)

        },
        isVisible() {

            this.visibleArticle = [];

            for (product of this.filteredProducts) {

                const element = document.getElementById("product-" + product.id);

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

        getWithDiscount(price, discount) {

            if (!discount) {

                return this.formatCurrency(price);

            } else {

                return this.formatCurrency(price - ((price / 100) * discount))

            }

        },

        handleFilterProducts() {

            this.filteredProducts = [];


            if (this.filterTags.length) {

                for (filterTag of this.filterTags) {

                    this.filteredProducts = this.filteredProducts.concat(this.products.filter(product => product.categoriesEnums.find(category => category === filterTag.toUpperCase())))

                }

            } else {

                this.filteredProducts = [...this.products]

            }

            let minPrice = this.minPrice ? this.minPrice : 0;

            let maxPrice = this.maxPrice ? this.maxPrice : Infinity;

            this.filteredProducts = this.filteredProducts.filter(product => product.name.toUpperCase().includes(this.searchProduct.toUpperCase()))

            this.filteredProducts = this.filteredProducts.filter(product => product.price > minPrice && product.price < maxPrice)

            if (this.sortSeleted) {

                if (this.sortSeleted == "asc") this.filteredProducts.sort((a, b) => a.price - b.price)

                if (this.sortSeleted == "des") this.filteredProducts.sort((a, b) => b.price - a.price)

                if (this.sortSeleted == "a-z") this.filteredProducts.sort((a, b) => {


                    if (a.name < b.name) {
                        return -1;
                    }
                    if (a.name > b.name) {
                        return 1;
                    }
                    return 0;

                })

                if (this.sortSeleted == "z-a") this.filteredProducts.sort((a, b) => {


                    if (a.name < b.name) {
                        return 1;
                    }
                    if (a.name > b.name) {
                        return -1;
                    }
                    return 0;

                })

            }

            this.totalFilteredProducts = [...this.filteredProducts]

            this.filteredProducts = this.filteredProducts.slice(this.initPage, this.endPage);
        },
        handleMessageAlert(message, seconds, isError) {

            this.messageAlert = {
                message,
                isError
            }

            setTimeout(() => this.messageAlert.message = "", seconds * 1000)
        },
        addFavorites(product) {

            if (this.favs.find(fav => fav.productId == product.id)) {

                axios.delete("/api/client/current/favorites/" + this.favs.find(fav => fav.productId == product.id).id)
                    .then(res => {

                        this.handleMessageAlert("Fav quit succesfully", 2, false)

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

                        this.handleMessageAlert("Fav added succesfully", 2, false)

                        this.loadData();

                    }).catch(err => console.log(err))

            }



        },
        checkedFavoritesAdded() {

            this.filteredProducts = this.filteredProducts.map(updated => {

                if (this.favs.find(fav => fav.productId == updated.id)) {

                    return { ...updated, inFavs: true }

                } else {

                    return { ...updated, inFavs: false }

                }

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

            this.handleMessageAlert("Product added succesfully", 2, false)
        },

        /* -------------QUITAR CANTIDAD DEL CARRITO -------------*/
        outProductBag(object) {
            if (object.quantity <= 0) {
                return
            }
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
        handleNavResponsive() {

            if (!this.navActive) {

                this.navActive = true;

            } else {

                this.navActive = false;

            }

        },

    },
    computed: {

        filtering() {

            this.handleFilterProducts();
            this.checkedFavoritesAdded();

        }

    }
}).mount("#app")