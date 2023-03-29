const {createApp} = Vue;

createApp({
    data(){
        return{
            products: [],
            activeMenuCategories: null,
            messageAlert:{
                message : "",
                isError : false
            },
            visibleArticle : []
        }
    },
    created(){

        this.loadData();

        document.addEventListener("scroll", () => this.isVisible());

    },
    methods:{
        loadData(){

            axios.get("/api/products")
                .then(res => {
                    console.log(res)
                    this.products = res.data;

                })
                .catch(err => {

                    console.log(err)

                })

        },
        handlerMenuCategories(){

            console.log("aca")

            if(!this.activeMenuCategories){

                this.activeMenuCategories = true;

            } else {

                    this.activeMenuCategories = false;
                
            }

        },
        isVisible() {

            this.visibleArticle = [];

            for(product of this.products){

                const element = document.getElementById("product-"+product.id);

                if(element){
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

            console.log(this.visibleArticle)

          }
    }
}).mount("#app")