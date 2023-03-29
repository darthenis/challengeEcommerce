const {createApp} = Vue;

createApp({ 
    data(){
        return {

            active : null,
            activeFade : false,
            favorites : [{id: 1, name: "Refrigerator Patrick Fagor", description: "Refrigerator Patrick Fagor, engine watts 1/4HP", price: 200, stock: 258},
            {id: 2, name: "Refrigerator Patrick Fagor", description: "Refrigerator Patrick Fagor, engine watts 1/4HP", price: 200, stock: 258}],
            visibleArticle : [],
            messageAlert: {
                message : "",
                isError : false
            }
        }
    },
    created(){

        document.addEventListener("scroll", () => this.isVisible());

        axios.post("/api/login", "email=emi.acevedo@gmail.com&password=123")
            .then(res => {

                console.log(res)
                this.loadData();

            })
            .catch(err => {

                console.log(err)

            })

     

    },
    methods : {
        loadData(){

            axios.get("/api/client/current/favorites")
            .then(res => {

                console.log(res)

                this.favorites = res.data;

            }).catch(err => {

                console.log(err)

            })

        },
        handleMessageAlert(message, seconds, isError){

            this.messageAlert = {
                message,
                isError
            }

            setTimeout(() => this.messageAlert.message = "", seconds * 1000)
        },
        toggleCar(){

            if(this.active == null){

                this.active = true;

            } else {

                this.active = !this.active;

            }

            console.log(this.active)

        },
        deleteFavorite(id){

            axios.delete("/api/client/current/favorites/"+id)
                .then(res => {

                    this.loadData();
                    console.log(res)

                    this.handleMessageAlert("Favorite deleted succesfully", 2, false)

                })
                .catch(err => {

                    console.log(err)
                    this.handleMessageAlert(err.response.data, 2, false)

                })

        },
        isVisible() {

            this.visibleArticle = [];

            for(favorite of this.favorites){

                const element = document.getElementById("article-"+favorite.id);

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

          }
    },
    mounted(){



    }


}).mount("#app")