<template>
  <div>
    <div class="container">
      <div class="product__container">
        <div class="product__gallery">
          <SliderImages v-if="this.product.images" :images="this.product.images"/>
        </div>
        <div class="product__info">
          <div class="product__rating"></div>
          <h1 class="product__title">{{ this.product.title }}</h1>
          <h2 class="product__price">{{ this.product.price }} ₽</h2>
          <div class="product__controls">
            <button v-if="this.productCountCart===0" class="buy__button" @click="this.addProductInCart">
              <h>В корзину</h>
              <img class="cart__image" src="@/assets/cart.svg">
            </button>
            <div v-if="this.productCountCart>0" class="product__counter">
              <span class="product__counter-minus" @click="this.deleteProductInCart">-</span>
              <router-link :to="{name: 'ProductsCart'}" class="product__counter-center">
                <p style="font-size: 14px">В корзине {{ this.productCountCart }} шт</p>
                <p style="font-size: 12px">Перейти в корзину</p>
              </router-link>
              <span class="product__counter-plus" @click="this.addProductInCart">+</span>
            </div>
            <div class="wish__button">
              <WishButton :product="this.product"/>
            </div>
          </div>
          <div class="product__compare">
            <p>В сравнении</p>
          </div>
          <div class="product__characteristics">
            <h3>Характеристики</h3>
            <div class="characteristic">
              <p class="characteristic-name">Вид застежки</p>
              <p class="characteristic-type">молния</p>
            </div>
            <div class="characteristic">
              <p class="characteristic-name">Спортивное назначение</p>
              <p class="characteristic-type">гимнастика</p>
            </div>
            <div class="characteristic">
              <p class="characteristic-name">Декоративные элементы</p>
              <p class="characteristic-type">светоотражающие вставки</p>
            </div>
            <div class="characteristic">
              <p class="characteristic-name">Карманы</p>
              <p class="characteristic-type">Наружный спереди; Наружный боковой; Внутренний для ноутбука/планшета</p>
            </div>
          </div>
          <div class="product__description">
            <h3>Описание</h3>
            <p>
              {{ this.product.description }}
            </p>
          </div>
          <div class="product__reviews">
            <div class="order__title">
              <h3>Отзыв</h3>
            </div>
            <div class="order__form">
              <div class="input__form required">
                <label class="input__title" for="title_cat_feed">
                  Комментарий
                </label>
                <input class="my__input" type="text" id="title_cat_feed" v-model="comment.comment">
              </div>

            </div>
            <h3>Рейтинг</h3>
            <div class="rating__form">
              <label v-for="index in (1,2,3,4,5)" :key="index" class="method" :for="'rating_'+index">
                <div class="method-input">
                  <input name="payment_method" class="my__input-radio" type="radio" :id="'rating_'+index" :value=index
                         v-model.number="this.comment.rating">
                  <span></span>
                </div>
                <div class="method-info">
                  <h4>{{ index }}</h4>
                </div>
              </label>
            </div>
            <button class="button-write__review" @click.prevent="createComment">Оставить отзыв</button>
          </div>

          <!--          <div class="overlay">-->
          <!---->
          <!--          </div>-->
          <!--          <div class="modal-window" style="background-color: white; width: 500px; height: 500px;    position: fixed;-->
          <!--    top: 30%;-->
          <!--    left: 40%;-->
          <!--z-index: 101;-->
          <!--border-radius: 25px;">-->
          <!--          </div>-->

        </div>
      </div>
      <div class="product-comments">
        <div class="product-comments__title">
          <h2>Отзывы</h2>
        </div>
        <div class="product-comments__content">
          <div v-for="comment in this.product.comments" :key="comment.id" class="product-comment"
               style="max-width: 50%; background-color: white; margin-top: 10px; border-radius: 25px; padding: 10px;">
            <div class="product-comment__title"
                 style="display: flex; justify-content: space-between; align-items: center;">
              <h4 style="padding: 0; margin: 0;">{{ comment.author.email }}</h4>
              <p style="padding: 0; margin: 0;">оценка:{{ comment.rating }}</p>

              <p>{{ comment.dateCreation }}</p>
            </div>
            <div class="product-comment__content">
              <p>{{ comment.comment }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SliderImages from "@/components/SliderImages";
import WishButton from "@/components/WishButton";
import axios from "axios";
import authHeader from "@/services/auth-header";

export default {
  name: "ProductView",
  components: {SliderImages, WishButton},
  data() {
    return {
      product_count: 0,
      comment: {
        comment: "",
        rating: 1
      }
    }
  },
  computed: {
    product() {
      return this.$store.getters.getCurrentProductDetail;
    },
    productCountCart() {
      return this.$store.getters.getProductCountCart(this.product.id);
    }
  },
  mounted() {
    this.loadProductDetail();
    console.log(this.product)
  },
  methods: {
    addProductInCart() {
      this.$store.commit('changeQuantityProductInCart', {payload: this.product, increase: true});
    },
    deleteProductInCart() {
      this.$store.commit('changeQuantityProductInCart', {payload: this.product, increase: false});
    },
    loadProductDetail() {
      this.$store.dispatch("loadProductDetail", {id: this.$route.params.id})
    },
    createComment() {
      axios.post('http://localhost:8081/product/comments/' + this.$route.params.id,
          this.comment,
          {
            headers: {
              'Authorization': authHeader().Authorization
            }
          })
          .then(() => {
            this.loadProductDetail();
            console.log('SUCCESS!!');
          })
          .catch(() => {
            console.log('FAILURE!!');
          });
    }
  }
}
</script>

<style lang="scss" scoped>
@import './src/colors';

.overlay {
  position: fixed; /* фиксированное положение */
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background: rgba(0, 0, 0, 0.5); /* цвет фона */
  z-index: 100;
  //opacity: 0; /* по умолчанию модальное окно прозрачно */
  -webkit-transition: opacity 200ms ease-in;
  -moz-transition: opacity 200ms ease-in;
  transition: opacity 200ms ease-in; /* анимация перехода */
  pointer-events: none; /* элемент невидим для событий мыши */
  margin: 0;
  padding: 0;
}

.rating__form {
  display: flex;
}

h1, h2 {
  font-size: 30px;
  margin: 0 0 10px 0;
}

h3 {
  font-size: 25px;
  margin: 0 0 10px 0;
}

.product-comments__title h2 {
  margin-bottom: 10px !important;
}

p {
  font-size: 16px;
  margin: 0;
  line-height: 1.5;
}

.input__form {
  display: block;
  margin-bottom: 20px;
}

.my__input {
  width: 100%;
  border: 1px solid;
  border-radius: 12px;
  height: 40px;
  font-size: 17px;
}

.input__title {
  font-size: 17px;
  display: flex;
}

.method {
  width: 100%;
  display: flex;
  align-items: center;

  h4 {
    margin: 10px 0;

  }
}

.method-info {
  flex: 1 1 content;
  padding: 5px;
}

.method-input {
  display: flex;
  align-items: center;
  flex: 0 0 36px;
}

.my__input-radio {
  display: none;
}

.my__input-radio {
  display: none;
}

span {
  width: 18px;
  height: 18px;
  border-radius: 50%;
  box-shadow: inset 0 0 0 3px #ECEFF1;
  border: 2px solid $base-background-white;
}

input[type="radio"]:checked + span {
  background: $base-background-white;
}

.payment__method-input {
  flex: 0 1 2rem;
}

.product__container {
  display: flex;
  align-items: start;
  justify-content: space-between;
  margin: 0;
  padding: 0;
}

.product__gallery {
  width: 59%;
  position: sticky;
  top: 10px;
}

.product__info {
  width: 39%;
}

.product__controls {
  display: flex;
  justify-content: space-between;
  margin-bottom: 15px;
  align-items: center;
}

.product__compare {
  margin-bottom: 40px;
}

.buy__button {
  min-width: 85%;
  height: 50px;
  border-radius: 10px;
  background: $base-background-white;
  font-size: 20px;
  color: #FFFFFF;
  border: none;
  justify-content: center;
  display: flex;
  align-items: center;
  cursor: pointer;
}

.product__counter {
  display: flex;
  cursor: pointer;
  min-width: 85%;
  height: 50px;
  border-radius: 10px;
  font-size: 20px;
}

.product__counter span, a {
  display: inline-flex;
  background: $base-background-white;
  align-content: center;
  align-items: center;
  justify-content: center;
  color: white;
  text-decoration: none;
}

.product__counter-center {
  min-width: 65%;
  flex-direction: column;
  text-align: center;
}

.product__counter-plus {
  width: 100%;
  margin-left: 2px;
  border-bottom-right-radius: 10px;
  border-top-right-radius: 10px;
}

.product__counter-minus {
  width: 100%;
  margin-right: 2px;
  border-bottom-left-radius: 10px;
  border-top-left-radius: 10px;
}

.cart__image {
  width: 21px;
  margin-left: 10px;
}

.wish__button {
  width: 27px;
  height: 27px;
  display: flex;
}

.product__characteristics {
  width: 100%;
  margin-bottom: 20px;
}

.characteristic {
  margin-bottom: 10px;
}

.characteristic-name {
  color: #999999;
  font-size: 14px;
}

.product__description {
  margin-bottom: 20px;
}

.button-write__review {
  height: 40px;
  border-radius: 10px;
  background: $base-background-white;
  font-size: 16px;
  color: #FFFFFF;
  border: none;
  padding: 0 20px;
  margin: 10px 0 0 0
}

@media (max-width: 1000px) {
  .product__container {
    display: block;
    align-items: normal;
  }
  .product__gallery, .product__info {
    width: 100%;
    position: relative;
  }
  .product__info {
    padding: 0 20px;
  }
  .product__gallery {
    margin-bottom: 40px;
  }
}
</style>