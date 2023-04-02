const { createApp } = Vue
createApp({
    data() {
        return {
            isLogged: false,
            name: "",
            lastName: "",
            email: "",
            oldPassword: "",
            newPassword: "",
            avatarUrl: null,
            avatar: null,
            navActive: null,
            active: null,
            bag: JSON.parse(localStorage.getItem("bag")) || [],


        }
    },
    created() {

        this.checkIsLogged()

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
        toggleCar() {

            console.log("hola")
            if (this.active == null) {

                this.active = true;

            } else {

                this.active = !this.active;

            }

            console.log(this.active)

        },


        editProfilePic(event) {

            console.log(event.target.files)

            this.avatar = event.target.files;

            this.avatarUrl = URL.createObjectURL(event.target.files[0])

        },
        handleNavResponsive() {

            if (!this.navActive) {

                this.navActive = true;

            } else {

                this.navActive = false;

            }

        },
        handleMessageAlert(message, seconds, isError) {

            this.messageAlert = {
                message,
                isError
            }

            setTimeout(() => this.messageAlert.message = "", seconds * 1000)

        },
        updateInfo() {

            if (this.avatar) {

                let formData = new FormData();

                formData.append("avatar", this.avatar[0])

                axios.post("/api/clients/auth/avatar", formData, { headers: { "Content-Type": "multipart/form-data" } })
                    .then(res => {

                        console.log(res)

                    }).catch(err => console.log(err))

            }

            if (this.newPassword && this.oldPassword) {

                axios.patch("/api/clients/current/password", `oldPassword=${this.oldPassword}&newPassword=${this.newPassword}`)
                    .then(res => {

                        if (this.name || this.lastName || this.email || this.tel) {

                            axios.patch("/api/clients/current", {
                                name: this.name,
                                lastName: this.lastName,
                                email: this.email,
                                tel: this.tel
                            })
                                .then(res => {


                                    this.handleMessageAlert("updated succesfully", 3, false)

                                })

                        } else {

                            this.handleMessageAlert("updated succesfully", 3, false)

                        }

                    })

            } else {


                if (this.name || this.lastName || this.email || this.tel) {

                    axios.patch("/api/clients/current", {
                        name: this.name,
                        lastName: this.lastName,
                        email: this.email,
                        tel: this.tel
                    })
                        .then(res => {

                            this.handleMessageAlert("updated succesfully", 3, false)
                        })

                }

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
            if (this.bag.find(item => item.id == object.id)) {
                this.bag = this.bag.map(item => {
                    if (item.id == object.id && object.stock > 0 && object.stock > item.quantity) {
                        this.handleMessageAlert("Item added to cart", 3, false)
                        return { ...item, quantity: (item.quantity + 1) }
                    } else {
                        return item;
                    }
                })
            } else if (object.stock > 0) {

                let product = { ...object, quantity: 1 }
                this.bag.push(product)
                this.handleMessageAlert("Item added to cart", 3, false)
            }
            localStorage.setItem("bag", JSON.stringify(this.bag))
            this.quantityTotalCart()
            this.priceTotalCart()


        },
        formatTime(value) {

            let date = value.split("T");

            let dateFinal = date[0].split("-").reverse().join("/");

            let time = date[1].split(".")[0];

            return dateFinal + " - " + time;

        },

        /* -------------QUITAR CANTIDAD DEL CARRITO -------------*/
        outProductBag(object) {
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

                if (object.discount > 0) {

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



    },
}).mount("#app")
