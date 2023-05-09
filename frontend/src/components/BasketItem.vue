<template>
  <div class="basket__item">
    <img class="basket__item-image" :src="imgUrl">
    <div class="basket__item-description">
      <span>{{ this.product.title }}</span>
    </div>
    <div>
      <div class="basket__item-price">{{ this.product.quantity }} x
        {{ this.product.discount ? this.product.new_price : this.product.price }} ₽
      </div>
      <div class="basket__item-price">{{ this.product.quantity * (this.product.discount ? this.product.new_price : this.product.price) }} ₽</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "BasketItem",
  props: {
    product: {}
  },
  data() {
    return {
      imgUrl: "https://ih1.redbubble.net/image.343726250.4611/flat,1000x1000,075,f.jpg"
    }
  },
  mounted() {
    console.log(this.product)
    if (this.product.images && this.product.images.length > 0) {
      this.imgUrl = "http://localhost:8081/image/" + this.product.images[0].url
    }
  },
}
</script>

<style  scoped>
.basket__item {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
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