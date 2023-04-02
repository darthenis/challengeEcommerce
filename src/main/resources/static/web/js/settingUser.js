const { createApp } = Vue
createApp({
    data() {
        return {
            isLogged: false

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

    },
}).mount("#app")
