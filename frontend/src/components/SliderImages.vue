<template>
  <div class="slider__horizontal">

    <div class="images__preview">
      <div v-for="(image,index) in images" :key="image.id">
        <input type="radio" :id="index" @click='this.currentSlide=index'>
        <label :for="index" :class="{input__checked: this.currentSlide===index}">
          <img :src="'http://localhost:8081/image/'+image.url">
        </label>
      </div>

      <!--      <input type="radio" id="slide1" @click='this.currentSlide=0'>-->
      <!--      <label for="slide1" :class="{input__checked: this.currentSlide===0}"><img src="@/assets/product_1.jpeg"></label>-->
      <!--      <input type="radio" id="slide2" @click="this.currentSlide=1">-->
      <!--      <label for="slide2" :class="{input__checked: this.currentSlide===1}"><img src="@/assets/product_1.jpeg"></label>-->
      <!--      <input type="radio" id="slide3" @click="this.currentSlide=2">-->
      <!--      <label for="slide3" :class="{input__checked: this.currentSlide===2}"><img src="@/assets/product_1.jpeg"></label>-->
    </div>
    <div class="slider">
      <a v-if="this.currentSlide>0" @click="prevSlide" style="position: absolute">
        <img src="@/assets/arrow_back_2.svg" style="width: 30px; height: 30px">
      </a>
      <div class="slider__list">
        <div class="slider__item" :style="{transform: this.getCurrentTranslate}"
             v-for="(image) in images" :key="image.id">
          <img :src="'http://localhost:8081/image/'+image.url">
        </div>
      </div>
      <a v-if="this.currentSlide<images.length-1" @click="nextSlide" class="button__next">
        <img src="@/assets/arrow_forward.svg" style="width: 30px; height: 30px">
      </a>
    </div>
  </div>
</template>

<script>
export default {
  name: "ProductSliderImages",
  data() {
    return {
      currentSlide: 0,
      currentWidth: 0
    }
  },
  props: {
    images: []
  },
  computed: {
    getCurrentTranslate() {
      return 'translateX(' + -this.currentSlide * 420 + 'px)';
    },
  },
  created() {

  },
  mounted() {

  },
  methods: {
    nextSlide: function () {
      this.currentSlide += 1;
    },
    prevSlide: function () {
      this.currentSlide -= 1;
    },

  },
}
</script>

<style lang="scss" scoped>
@import './src/colors';

.button__next {
  position: absolute;
  margin-left: 430px;
}

.slider__horizontal {
  display: flex;
  justify-content: space-around;
  align-items: center;
}

input {
  display: none;
}

.images__preview label {
  display: block;
  cursor: pointer;
  padding: 5px;
  width: 135px;
}

.input__checked {
  border: 2px solid $base-background-white;
  padding: 3px !important;
}

.images__preview label img {
  width: 100%;
}

.slider {
  display: flex;
  align-items: center;
  width: 460px;
}

.slider__list {
  display: flex;
  overflow: hidden;
  width: 400px;
  margin-left: 30px;
  align-items: center;
}

.slider__item > img {
  width: 400px;
}

.slider__item {
  transition: transform ease-in-out .5s;
  width: 400px;
}

@media (max-width: 600px) {
  .slider__list {
    width: 240px;
  }
  .slider__item > img {
    width: 240px;
  }
  .button__next {
    margin-left: 270px;
  }
  .slider {
    width: 300px;
    height: 300px;
  }
  .images__preview {
    display: none;
  }
}

</style>