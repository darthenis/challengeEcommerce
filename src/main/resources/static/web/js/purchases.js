const { createApp } = Vue
createApp({
    data() {
        return {
            selectImg: "",
            products: [],
            radioPayments: null,
            bag: JSON.parse(localStorage.getItem("bag")) || [],
            active: null,
            totalCart: 0,
            totalCartQuantity: 0,
            id: null,
            navActive: null,
            isLogged: false,
            selectSubMenu: false,
            date: ""

        }
    },
    created() {

        this.checkIsLogged()

        this.loadData();

        this.quantityTotalCart()

        this.priceTotalCart()

        let date = new Date();

        this.date = date.getDate() + "/" + (date.getMonth() + 1)+"/" +date.getFullYear();
    },

    /*-------------------METHODS----------------------*/
    methods: {

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

        loadData(){

                axios("/api/client/current/products")
                        .then(res => {

                            this.products = res.data;

                        }).catch(err => console.log(err))

        },


        /*---------CAMBIO DE IMAGEN DE PRODUCTO-----------------*/

        changeImg(img) {
            this.selectImg = img
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
                this.navActive = this.navActive !== null ? false : null;
            } else {
                this.active = !this.active;
            }

        },
        avaregeStars(rates){

            let five = 0;

            let four = 0;

            let three = 0;

            let two = 0;

            let one = 0;

            for(let rate of rates){

                switch(rate.stars){
                    case "ONE":
                        one ++;
                        break;
                    case "TWO":
                        two ++;
                        break;
                    case "THREE":
                        three ++;
                        break;
                    case "FOUR":
                        four ++;
                        break;
                    case "FIVE":
                        five ++;
                        break;
                }

            }

            return ((five * 5) + (four * 4) + (three * 3) + (two * 2) + (one * 1)) / rates.length || "0.0"

        },

        counterStars(rates){

            let rounded = 0;

            rounded += Math.round(this.avaregeStars(rates));

            let star = '<svg aria-hidden="true" class="w-5 h-5 text-yellow-300" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><title>First star</title><path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path></svg>'

            let starEmpty = '<svg aria-hidden="true" class="w-5 h-5 text-yellow-300" fill="gray" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><title>First star</title><path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path></svg>'

            let html = "";

            for(let i = 1; i <= rounded; i++){

                hmtl += star;

            }

            if((5 - rounded) != 0){

                for(let i = rounded; i <= 5; i++){

                    rounded++

                    html += starEmpty;

                }

            }

            return html

        },
        handleNavResponsive(){

            if(!this.navActive){

                this.navActive = true;

            } else {

                this.navActive = false;

            }

        },



    },

}).mount("#app")