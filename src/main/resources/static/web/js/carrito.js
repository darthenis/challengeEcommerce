const {createApp} = Vue;

createApp({ 
    data(){
        return {

            active : null
        }
    },
    methods : {
        toggleCar(){

            if(this.active == null){

                this.active = true;

            } else {

                this.active = !this.active;

            }

            console.log(this.active)

        }
    }


}).mount("#app")