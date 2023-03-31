const { createApp } = Vue;

createApp({
  data() {},
  created() {},
  methods: {
    editProfilePic: function(){
        const image = document.querySelector("#profile")
        const input = document.querySelector("#picFile")

        image.src = URL.createObjectURL(input.files[0])
    }
  },
}).mount("#app");
