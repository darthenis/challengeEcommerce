const { createApp } = Vue;

createApp({
    data() {
        return {
            clients: [],
            products: [],
            filteredProducts: [],
            searchProduct: "",
                name: "",
                description: "",
                price: null,
                discount: null,
                stock: null,
                category: "",
            id: null,
            pictures: null,
            messageAlert: {
                message : "",
                isError: false
            }


        }
    },
    created() {
        this.loadData()

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
                    this.filteredProducts = [...this.products]
                    console.log(this.products)
                })

            axios.get('/api/tickets')
                .then(res => {
                    this.tickets = res.data
                })
        },
        handleMessageAlert(message, seconds, isError){

            this.messageAlert = {
                message,
                isError
            }

            setTimeout(() => this.messageAlert.message = "", seconds * 1000)

        },
        handleSearchProduct(){

            this.filteredProducts = this.products.filter(product => product.name.toUpperCase().includes(this.searchProduct.toUpperCase()))

        },
        clearForm(){

                this.name= "";
                this.description = "";
                this.price = null;
                this.discount = null;
                this.stock= null;
                this.category= "";

        },
        createProduct() {

            let date = new Date().toISOString();
            axios.post('/api/products', {
                name: this.name,
                description: this.description,
                price: this.price,
                discount: this.discount,
                stock: this.stock,
                date: date,
                categories: [this.category]
            }).then(res => {

                
                this.handleMessageAlert("Product created succesfully", 3, false);
                this.loadData();


            }).catch(err => {

                console.log(err)

            })
        },
        deleteProduct(){

            axios.post(`/api/current/products/${this.id}`)
                    .then(() => {

                        this.handleMessageAlert("changed status succesfully", 3, false);
                        this.loadData();

                    }).catch(err => console.log(err))

        },

        editProduct(product){
                this.id = product.id;
                this.name = product.name;
                this.description = product.description;
                this.price = product.price;
                this.discount = product.discount;
                this.stock = product.stock;
                this.category = product.categoriesEnums[0]
        
        },

        getImgsProductById(){

            let result = this.products.filter(product => product.id == this.id)[0];

            if(result) return result.imgsUrls;

            else return []

        },
        deleteImg(img){

            axios.delete("/api/products/"+this.id+"?url="+img)
                .then(res => {

                    this.handleMessageAlert("picture deleted succesfully", 3, false);
                    this.loadData();

                })
                .catch(err => console.log(err))

        },
        edit(){

            axios.patch("/api/products", {  name : this.name, 
                                            description: this.description, 
                                            id: this.id, 
                                            price : this.price,
                                            discount : this.discount,
                                            stock: this.stock,
                                            categories : [this.category]})
                .then(res => {

                    
                    this.handleMessageAlert("edit product succesfully", 3, false);
                    this.loadData();

                }).catch(err => console.log(err))

        },

        addPhotos() {

            let formData = new FormData()
            for(picture of this.pictures){

                formData.append('images', picture)

            }
         
            axios.post(`/api/products/${this.id}/images`, formData,
                { headers: { "Content-Type": "multipart/form-data" } })
                .then(res =>{

                    this.loadData();

                })
                .catch(er => console.error(er))

        },

        productId(id) {
            this.id = id
        },

        addPic(event) {
            this.pictures = event.target.files
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
        }
    }
}).mount("#app")