const { createApp } = Vue
createApp({
    data() {
        return {
            password1: null,
            user: null,
            name: "",
            lastName: "",
            tel: "",
            password: "",
            email: ""


        }
    },
    created() {







    },
    methods: {
        logout() {
            axios.post('/api/logout')
                .then(response => console.log('signed out!!!'))
                .then
            window.location.href = "/web/index.html";
        },
    },
}).mount("#app")







