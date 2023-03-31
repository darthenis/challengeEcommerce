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
			email: "",
			token: "",
			navActive: null

		}
	},
	created() {
		let parameterUrl = location.search
		let parameters = new URLSearchParams(parameterUrl)
		this.token = parameters.get("token")


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
				.catch(error => console.error(error))
		},
		handleNavResponsive() {

			console.log("aca")

			if (!this.navActive) {

				this.navActive = true;

			} else {

				this.navActive = false;

			}

		},

		/*----------------PARAMS TOKEN LOGIN-------------------*/

		signUp() {
			axios.post('/api/clients', {
				name: this.name,
				lastName: this.lastName,
				email: this.email,
				tel: this.tel,
				password: this.password
			},)
				.then(response => {
					window.location.href = "/web/login.html"
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







