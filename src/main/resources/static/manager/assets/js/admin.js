const { createApp } = Vue;

createApp({
    data() {
        return {
            clients: [],
            products: [],
            name: "",
            description: "",
            price: null,
            discount: null,
            stock: null,
            date: null,
            category: "",
            id: null,
            pictures: null,


        }
    },
    created() {
        this.loadData()
        this.date = new Date().toISOString().split("T")[0]

    },
    methods: {

        loadData() {
            axios.get('/api/clients')
                .then(res => {
                    this.clients = res.data
                })
            axios.get('/api/products')
                .then(res => {
                    this.products = res.data
                    console.log(this.products)
                })

            axios.get('/api/tickets')
                .then(res => {
                    this.tickets = res.data
                })
        },

        createProdcut() {
            axios.post('/api/products', {
                name: this.name,
                description: this.description,
                price: this.price,
                discount: this.discount,
                stock: this.stock,
                date: this.date,
                categories: [this.category]
            })
        },

        addPhotos() {

            let formData = new FormData()
            for(picture of this.pictures){

                formData.append('images[]', picture)
                
            }
         
            console.log(formData)
            axios.post(`/api/products/${this.id}/images`, formData,
                { headers: { "Content-Type": "multipart/form-data" } })
                .catch(er => console.error(er))

        },

        productId(id) {
            this.id = id
        },

        addPic(event) {
            this.picture = event.target.files
        },


        /*-------------------LOGOUT--------------------*/
        logout() {
            axios.post('/api/logout')

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


    }
}).mount("#app")