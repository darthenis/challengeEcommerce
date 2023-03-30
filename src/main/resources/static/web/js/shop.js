const {createApp} = Vue;

createApp({
    data(){
        return{
            products: [],
            filteredProducts: [],
            totalFilteredProducts: [],
            activeMenuCategories: null,
            filterTags: [],
            messageAlert:{
                message : "",
                isError : false
            },
            visibleArticle : [],
            minPrice: "",
            maxPrice: "",
            sortSeleted : "",
            searchProduct: "",
            initPage: 0,
            endPage: 9,
        }
    },
    created(){

        this.loadData();

        document.addEventListener("scroll", () => this.isVisible());

      
    },
    methods:{
        getParam(key){

            let parameterUrl = location.search
            let parameters = new URLSearchParams(parameterUrl)
            return parameters.get(key)

        },
        loadData(){

            axios.get("/api/products")
                .then(res => {
                    console.log(res)
                    this.products = res.data;
                    this.filterProducts = res.data;

                    let string = this.getParam("filter");

                    let element = document.getElementById(string);

                    if(element) element.click();         
                    
                })
                .catch(err => {

                    console.log(err)

                })

        },
        handlePages(isNext){

            window.scrollTo(0, 0);

            if(isNext && this.totalFilteredProducts.length > this.endPage){

                this.initPage += 9;

                this.endPage += 9

            } else if(this.initPage != 0){

                this.initPage -= 9;

                this.endPage -= 9

            }

            


        },
        handlerMenuCategories(){

            if(!this.activeMenuCategories){

                this.activeMenuCategories = true;

            } else {

                    this.activeMenuCategories = false;
                
            }

        },
        formatCurrency(value){

            let USDollar = new Intl.NumberFormat('en-US', {
                style: 'currency',
                currency: 'USD',
            });
            
            return USDollar.format(value)

        },
        isVisible() {

            this.visibleArticle = [];

            for(product of this.filteredProducts){

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

        getWithDiscount(price, discount){

            if(!discount){

                return this.formatCurrency(price);

            } else {

                return this.formatCurrency(price - ((price / 100) * discount ))

            }

        },

        handleFilterProducts(){

            this.filteredProducts = [];
            
            
            if(this.filterTags.length){

                for(filterTag of this.filterTags){

                    this.filteredProducts = this.filteredProducts.concat(this.products.filter(product => product.categoriesEnums.find(category => category === filterTag.toUpperCase())))

                }

            } else {

                this.filteredProducts = [...this.products]

            }

            let minPrice = this.minPrice ? this.minPrice : 0;

            let maxPrice = this.maxPrice ? this.maxPrice : Infinity;

            this.filteredProducts = this.filteredProducts.filter(product => product.name.includes(this.searchProduct))

            this.filteredProducts = this.filteredProducts.filter(product => product.price > minPrice && product.price < maxPrice)

            if(this.sortSeleted){

                if(this.sortSeleted == "asc") this.filteredProducts.sort((a, b) => a.price - b.price)

                if(this.sortSeleted == "des") this.filteredProducts.sort((a, b) => b.price - a.price)

                if(this.sortSeleted == "a-z") this.filteredProducts.sort((a, b) => {


                    if (a.name < b.name) {
                        return -1;
                      }
                      if (a.name > b.name) {
                        return 1;
                      }
                      return 0;

                })

                if(this.sortSeleted == "z-a") this.filteredProducts.sort((a, b) => {


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
        }
    },
    computed : {

        filtering(){

            this.handleFilterProducts();

        }

    }
}).mount("#app")