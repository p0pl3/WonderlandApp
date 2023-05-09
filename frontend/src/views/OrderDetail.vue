<template>
  <div class="container">
    <div class="basket">
      <h1 class="basket__title">Ваш заказ</h1>
      <div class="basket__item-list" v-if="order!=null">
        <BasketItem v-for="orderItem in order.orderItems" :key="orderItem.id" :product="{
          title: orderItem.product.title,
          quantity: orderItem.quantity,
          price: orderItem.product.price,
          images: orderItem.product.images
          }"/>
      </div>
      <div class="basket__total">
        <div class="basket__total-title">Итого:</div>
        <h6 v-if="order!=null" class="basket__total-price">{{ order.total }}₽</h6>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import authHeader from "@/services/auth-header";
import BasketItem from "@/components/BasketItem";

export default {
  name: "OrderDetail",
  components: {BasketItem},

  data() {
    return {
      order: null
    }
  },
  mounted() {
    axios.get("http://localhost:8081/orders/" + this.$route.params.id, {headers: authHeader()}).then(r => {
      console.log(r);
      this.order = r.data
    }).catch(e => {
      console.log(e);
    });
  }
}
</script>

<style lang="scss" scoped>
@import './src/colors';
.basket {
  padding: 15px;
}

.basket__subtotal {
  border-top: 1px solid $base-grey;
  border-bottom: 1px solid $base-grey;
  padding: 10px 0 10px 0;
}

.basket__subtotal-item {
  width: 100%;
  display: flex;
  align-items: center;
  padding: 5px 0 5px 0;
}

.basket__subtotal-title {
  font-size: 16px;
  flex: 1 1 content;
}

.basket__total {
  margin-top: 20px;
  width: 100%;
  display: flex;
  align-items: center;
}

.basket__total-title {
  flex: 1 1 content;
}

.basket__total-price {
  font-size: 25px;
}
</style>