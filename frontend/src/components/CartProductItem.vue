<template>
  <div class="cart__product-item">
    <div style="display: flex; align-items: center;">
      <img :src="imgUrl" style="height: 100%; width:100px">
      <div class="product-item__description">

        <div class="product-item__info">
          <router-link :to="{name: 'ProductView', params: {id: product.id}}">
            <h3>{{ product.title }}</h3>
          </router-link>
        </div>

        <div class="product-item__price" v-if="product.discount">
          <h3 class="new__price">{{ product.new_price }}₽</h3>
          <h3 class="old__price">{{ product.price }}₽</h3>
        </div>

        <div class="product-item__price" v-if="!product.discount">
          <h3>{{ product.new_price }}₽</h3>
        </div>

        <div class="product__counter">
          <span @click="deleteProductInCart()" style="padding-left: 10px">&#8211;</span>
          <span>{{ product.quantity }} </span>
          <span @click="addProductInCart()" style="padding-right: 10px">+</span>
        </div>

      </div>
    </div>

    <div style="display: flex">
      <h2 class="total_price">{{ product.quantity * product.new_price }} ₽</h2>

      <div class="product-item__controls">
        <WishButton/>
        <img src="@/assets/delete.svg" style="width: 30px; height: 30px;cursor: pointer"
             @click="removeProductFromCart">
      </div>
    </div>
  </div>
</template>

<script>
import WishButton from "@/components/WishButton";

export default {
  name: "CartProductItem",
  components: {WishButton},
  data() {
    return {
      imgUrl: "https://ih1.redbubble.net/image.343726250.4611/flat,1000x1000,075,f.jpg"
    }
  },
  props: {
    product: {}
  },
  mounted() {
    if (this.product.images && this.product.images.length > 0) {
      this.imgUrl = "http://localhost:8081/image/" + this.product.images[0].url
    }
  },
  methods: {
    addProductInCart() {
      this.$store.commit('changeQuantityProductInCart', {payload: this.product, increase: true});
    },
    deleteProductInCart() {
      this.$store.commit('changeQuantityProductInCart', {payload: this.product, increase: false});
    },
    removeProductFromCart() {
      this.$store.commit('removeProductFromCart', this.product);
    }
  },
}
</script>

<style lang="scss" scoped>
@import "src/colors";

.product-item__description h1, h2, h3, h4, h5, h6 {
  margin: 0;
}

.product-item__title {
  line-height: 1.1;
}

.total_price {
  align-self: center;
  margin: 0 15px 0 0;
}

.product-item__price {
  line-height: 1;
}

.product-item__controls {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.new__price {
  color: red !important;
  font-weight: 600 !important;
  line-height: 1;
}

.old__price {
  text-decoration: red line-through solid 2px;
  line-height: 1;
}

.cart__product-item {
  display: flex;
  padding: 10px;
  background: white;
  border-radius: 15px;
  margin-bottom: 10px;
  justify-content: space-between;
  height: 150px;
}

.cart__product-item:last-child {
  margin-bottom: 0;
}

.product-item__action {
  width: 20px;
  height: 20px;
}

.product-item__description {
  margin-left: 20px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 100%;
}

.product__counter {
  display: flex;
  cursor: pointer;
  justify-content: space-between;
  background: $base-background-white;
  border-radius: 10px;
  -webkit-touch-callout: none;
  -webkit-user-select: none;
  -ms-user-select: none;
  user-select: none;
  width: 100px;
}

.product__counter span {
  margin: 0;
  color: white;
  font-weight: bolder;
  font-size: 24px;
}
</style>