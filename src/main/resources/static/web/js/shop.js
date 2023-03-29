const {createApp} = Vue;

createApp({
    data(){
        return{
            activeMenuCategories: null,
            messageAlert:{
                message : "",
                isError : false
            }
        }
    },
    methods:{
        handlerMenuCategories(){

            console.log("aca")

            if(!this.activeMenuCategories){

                this.activeMenuCategories = true;

            } else {

                    this.activeMenuCategories = false;
                
            }

        }
    }
}).mount("#app")