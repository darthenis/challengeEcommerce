const { createApp } = Vue
createApp({
    data() {
        return {
            isLogged: false,
            name : "",
            lastName : "",
            email : "",
            oldPassword : "",
            newPassword: "",
            avatarUrl : null,
            avatar : null
            

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
        editProfilePic(event){

            console.log(event.target.files)

            this.avatar = event.target.files;

            this.avatarUrl = URL.createObjectURL(event.target.files[0])

        },
        handleMessageAlert(message, seconds, isError){

            this.messageAlert = {
                message,
                isError
            }

            setTimeout(() => this.messageAlert.message = "", seconds * 1000)

        },
        updateInfo(){

            if(this.avatar){

                let formData = new FormData();

                formData.append("avatar", this.avatar[0])

                axios.post("/api/clients/auth/avatar", formData, { headers: { "Content-Type": "multipart/form-data" }})
                                    .then(res => {

                                        console.log(res)

                                    }).catch(err => console.log(err))

            }

            if(this.newPassword && this.oldPassword){

                axios.patch("/api/clients/current/password", `oldPassword=${this.oldPassword}&newPassword=${this.newPassword}`)
                            .then(res => {

                                if(this.name || this.lastName || this.email || this.tel){

                                    axios.patch("/api/clients/current", {
                                                                            name : this.name,
                                                                            lastName :this.lastName,
                                                                            email : this.email,
                                                                            tel : this.tel
                                                                        })
                                                .then(res => {


                                                    this.handleMessageAlert("updated succesfully", 3, false)

                                                })

                                } else {

                                    this.handleMessageAlert("updated succesfully", 3, false)

                                }

                            })

            } else {


                if(this.name || this.lastName || this.email || this.tel){

                    axios.patch("/api/clients/current", {
                                                            name : this.name,
                                                            lastName :this.lastName,
                                                            email : this.email,
                                                            tel : this.tel
                                                        })
                                        .then(res => {

                                            this.handleMessageAlert("updated succesfully", 3, false)
                                        })

                }

            }

        },

    },
}).mount("#app")
