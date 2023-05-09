<template>
  <router-link :to="{ name: 'OrderDetail', params: {id: order.id}}">
    <div class="basket__item">
      <div class="basket__item-description">
        <span>{{ order.creationDate }}</span>
      </div>
      <div class="basket__item-price">{{ order.total }} ₽</div>
    </div>
  </router-link>
</template>

<script>
export default {
  name: "OrderItem",
  props: {
    order: {}
  }
}
</script>

<style scoped>
.basket__item {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  background-color: white;
  padding: 5px;
  border-radius: 25px;
}

.basket__item-image {
  width: 100px;
  height: 100px;
  align-items: center;
  align-content: center;
  background: white;
  flex: 0 1 100px;
  object-fit: contain;
}

.basket__item-description {
  flex: 1 1 content;
  font-size: 16px;
  padding: 0 10px;
}

.basket__item-price {
  text-align: end;
  font-weight: bold;
}
</style>