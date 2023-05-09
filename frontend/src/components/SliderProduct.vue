<template>
  <div class="product__slider__horizontal">
    <div class="container">
      <div class="product__slider-title">
        <h4>Плюшевые игрушки</h4>
        <div class="product__slider-control">
          <a @click="previousProductHorizontalSlider">
            <img src="@/assets/arrow_back.svg" style="width: 20px; height: 20px">
          </a>
          <a @click="nextProductHorizontalSlider">
            <img src="@/assets/arrow_forward.svg" style="width: 20px; height: 20px">
          </a>
        </div>
      </div>
      <div class="product__list" ref="ProductListHorizontalSlider">
        <ProductCard v-for="product in this.specialProductsCart" :key="product.id" :product="product"/>
      </div>
    </div>
  </div>
</template>

<script>
import ProductCard from "@/components/ProductCard";

export default {
  name: "SliderProduct",
  components: {ProductCard},
  methods: {
    nextProductHorizontalSlider: function () {
      this.$refs.ProductListHorizontalSlider.scrollTo({
        left: this.$refs.ProductListHorizontalSlider.scrollLeft + 100,
        behavior: 'smooth'
      })
    },
    previousProductHorizontalSlider: function () {
      this.$refs.ProductListHorizontalSlider.scrollTo({
        left: this.$refs.ProductListHorizontalSlider.scrollLeft - 100,
        behavior: 'smooth'
      })
    },
  },
  mounted() {
    this.$store.dispatch('loadSpecialProductsCart')
  },
  computed: {
    specialProductsCart() {
      return this.$store.getters.getSpecialProductsCart;
    }
  }
}
</script>

<style lang="scss" scoped>
@import './src/colors';

.product__list {
  display: flex;
  width: 100%;
  overflow-x: scroll;

  &::-webkit-scrollbar {
    width: 0px;
  }

}

.product__slider__horizontal {
  margin-top: 15px;
}

.product__slider-title {
  margin-bottom: 10px;
  display: flex;
  justify-content: space-between;
  align-items: center;

  h4 {
    margin: 0;
  }
}

@media only screen and (max-width: 600px) {
  .slider__categories-control {
    display: none;
  }
}
</style>