const { createApp } = Vue;

createApp({
  data() {
      return{
        navActive : null,
        active : null,
        bag: JSON.parse(localStorage.getItem("bag")) || [],
        totalCartQuantity: 0,
        totalCart: 0,
        isLogged: false
      }

  },
  created() {},
  methods: {
    editProfilePic: function(){
        const image = document.querySelector("#profile")
        const input = document.querySelector("#picFile")

        image.src = URL.createObjectURL(input.files[0])
    },
     /*---------CAMBIO DE IMAGEN DE PRODUCTO-----------------*/

     changeImg(img) {
      this.selectImg = img
  },


  /*------------------FORMATEO A MONEDA TIPO DOLAR US--------------*/
  formatDollar(price) {
      let USDollar = new Intl.NumberFormat('en-US', {
          style: 'currency',
          currency: 'USD',
      });
      return USDollar.format(price)
  },

  resetPayments() {
      this.radioPayments = null
  },
  /*---------------AGREGAR AL CARRITO Y CANTIDAD----------------*/
  saveBag(object) {
      if (this.bag.find(item => item.id == object.id)) {
          this.bag = this.bag.map(item => {
              if (item.id == object.id && object.stock > 0 && object.stock > item.quantity) {
                  return { ...item, quantity: (item.quantity + 1) }
              } else {
                  return item;
              }
          })
      } else {

          let product = { ...object, quantity: 1 }
          this.bag.push(product)
      }
      localStorage.setItem("bag", JSON.stringify(this.bag))
      this.quantityTotalCart()
      this.priceTotalCart()
  },

  /* -------------QUITAR CANTIDAD DEL CARRITO -------------*/
  outProductBag(object) {
      if (this.bag.find(item => item.id == object.id)) {
                object.quantity--
                if (object.quantity === 0) {
                    this.bag = this.bag.filter(item => item.quantity !== 0);
                }
      }
      localStorage.setItem("bag", JSON.stringify(this.bag))
      this.quantityTotalCart()
      this.priceTotalCart()
  },

  /*--------------QUITAR POR COMPLETO DEL CARRITO-------------*/

  removeItem(object) {
      if (this.bag.find(item => item.id == object.id)) {
          let index = this.bag.findIndex(item => item.id == object.id)
          this.bag.splice(index, 1)
      }
      localStorage.setItem("bag", JSON.stringify(this.bag))
      this.quantityTotalCart()
      this.priceTotalCart()
  },


  /*--------------CALCULO DE TOTAL CARRITO--------------*/
  priceTotalCart() {
      let totalCount = 0
      this.bag.forEach(object => {

        if(object.discount > 0){

            let priceDiscount = object.price - ((object.price / 100) * object.discount);

            totalCount += priceDiscount * object.quantity

        } else {

            totalCount += object.price * object.quantity
        }

    })
      this.totalCart = totalCount
  },

  /*-----------------CANTIDAD DE PRODUCTOS-----------------*/
  quantityTotalCart() {
      let totalCount = 0
      this.bag.forEach(object => {
          totalCount += object.quantity
      })
      this.totalCartQuantity = totalCount
  },


  /*--------------TOGGLE CART--------------*/
  toggleCart() {

      if (!this.active) {
          this.active = true;
          this.navActive = this.navActive !== null ? false : null;
      } else {
          this.active = !this.active;
      }

  },
  handleNavResponsive(){

    if(!this.navActive){

        this.navActive = true;

    } else {

        this.navActive = false;

    }

},

  },
}).mount("#app");
