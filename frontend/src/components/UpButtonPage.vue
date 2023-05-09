<template>
  <button @click="toUpPage" v-if="isVisibleUpButton">
    <img src="@/assets/expand_less.svg" style="width: 30px; height: 30px;">
  </button>
</template>

<script>
export default {
  name: "UpButtonPage",
  data() {
    return {
      isVisibleUpButton: false,
    }
  },
  methods: {
    toUpPage: function () {
      window.scrollTo(0, 0);
    },
    onScroll() {
      if (window.pageYOffset > 200) {
        this.isVisibleNavbar = false;
        this.isVisibleUpButton = true;
      } else {
        this.isVisibleNavbar = true;
        this.isVisibleUpButton = false;
      }
    }
  },
  mounted() {
    window.addEventListener('scroll', this.onScroll);
  },
}
</script>

<style lang="scss" scoped>
@import './src/colors';

button {
  position: fixed;
  bottom: 20px;
  right: 30px;
  z-index: 99;
  border: none;
  outline: none;
  background-color: $base-background-white;
  color: white;
  cursor: pointer;
  padding: 5px;
  border-radius: 4px;
}

button:hover {
  background-color: #555;
}
</style>