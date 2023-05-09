<template>
  <div>
    <div class="container">
      <div class="cart__title">
        <h2>Корзина</h2>
      </div>
      <div class="cart__article">
        <p v-show="!cartList.length">
          <i>Please add some products to cart.</i>
        </p>
        <div v-show="cartList.length" class="cart__products">
          <CartProductItem v-for="product in cartList" :key="product.id" :product="product"/>
        </div>
        <div class="cart__info">
          <div class="cart__description-price">
            <div class="cart__description-title">
              <p>Товары: </p>
              <p>{{ totalPriceCart }}₽ </p>
            </div>
            <div class="cart__description-title">
              <p>Скидка:</p>
              <p style="color: red">-{{ totalPriceCart-totalPriceCartDiscount }}₽ </p>
            </div>
            <div class="cart__description-title">
              <p style="font-size: 24px">Итого:</p>
              <p style="font-size: 30px">{{ totalPriceCartDiscount }}₽ </p>
            </div>
          </div>
          <router-link class="cart__info__confirm-order" :to="{ name: 'confirmOrder'}">Оформить</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CartProductItem from "@/components/CartProductItem";

export default {
  name: "CartGrid",
  components: {CartProductItem},
  computed: {
    cartList() {
      return this.$store.getters.getCartList;
    },
    totalPriceCart() {
      return this.$store.getters.getTotalPriceCart;
    },
    totalPriceCartDiscount() {
      return this.$store.getters.getTotalPriceCartDiscount;
    }
  }
}
</script>

<style lang="scss" scoped>
@import './src/colors';

.cart__promo-placeholder {
  margin: 0;
  font-weight: 100;
  font-size: 18px;
}

.cart__description-title {
  display: flex;
  justify-content: space-between;
}

.cart__promo {
  width: 100%;
}

.cart__description-price {
  width: 100%;
  margin-top: 15px;
}

.cart__info__confirm-order {
  display: block;
  text-align: center;
  font-size: 26px;
  margin-top: 10px;
  width: 100%;
  padding: 10px;
  background: $base-background-white;
  border-radius: 25px;
  text-decoration: none;
  color: white;
}

.cart__promo-input {
  text-indent: 10px;
  width: 100%;
  font-size: 25px;
  height: 50px;
  border-radius: 15px;
  border: 1px solid grey;
}

.cart__article {
  display: flex;
  flex-wrap: wrap;
  align-items: flex-start;
  justify-content: space-between;
}

.cart__products {
  width: 66%;
  border-radius: 15px;
}

.cart__info {
  width: 33%;
  padding: 10px;
  background: white;
  border-radius: 15px;
  position: sticky;
  top: 15px;

  p {
    margin: 0 0 10px 0;
  }
}

@media screen and (max-width: 820px) {
  .cart__products {
    width: 100%;
  }

  .cart__info {
    width: 100%;
    margin-top: 10px;
    position: sticky;
    top: 0;
  }
}
</style>