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
		login() {
			axios.post('/api/login', `email=${this.user}&password=${this.password1}`,
				{ headers: { 'content-type': 'application/x-www-form-urlencoded' } })
				.then(response => {
					if (this.user === "admin@mindhub.com") {
						window.location.href = '/web/vista-admin.html';
					}
					else {
						window.location.href = '/web/index.html';
					}
				})
				.catch(error => concole.error(error))
		},
		signUp() {
			axios.post('/api/clients', `firstName=${this.firstName}&lastName=${this.lastName}&email=${this.email}&password=${this.password}`,
				{ headers: { 'content-type': 'application/x-www-form-urlencoded' } })
				.then(response => {
					console.log(response)
					this.user = this.email
					this.password1 = this.password
					this.login()
				})
				.catch(error => console.log(error))
		},
		animationLogin(e) {
			const signupBtn = document.getElementById('signup');
			let parent = e.target.parentNode.parentNode;
			Array.from(e.target.parentNode.parentNode.classList).find((element) => {
				if (element !== "slide-up") {
					parent.classList.add('slide-up')
				} else {
					signupBtn.parentNode.classList.add('slide-up')
					parent.classList.remove('slide-up')
				}
			});

		},

		animationSignIn(e) {
			const loginBtn = document.getElementById('login');
			let parent = e.target.parentNode;
			Array.from(e.target.parentNode.classList).find((element) => {
				if (element !== "slide-up") {
					parent.classList.add('slide-up')
				} else {
					loginBtn.parentNode.parentNode.classList.add('slide-up')
					parent.classList.remove('slide-up')
				}
			});


		}
	},
}).mount("#app")







