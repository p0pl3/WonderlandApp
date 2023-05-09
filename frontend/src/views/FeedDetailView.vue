<template>
  <UpButtonPage/>
  <SliderSale/>
  <div class="">
    <div class="container">
      <div class="category_breadcrumb">
        <ul class="breadcrumb">
          <li><a href="#">Главная</a></li>
          <li><a href="#">Каталог</a></li>
        </ul>
      </div>
    </div>
  </div>
  <div class="">
    <div class="container">
      <div class="products__article">
        <div class="product-catalog__grip" style="width: 100%; margin-top: 10px;">
          <div class="feed-preview"
               style="margin-bottom: 10px; width: 100%; border-radius: 20px; display: flex; justify-content: space-between; align-items: center; background-color: white; padding: 10px;">
            <div class="product-preview__content" style="width: 70%; padding: 5px;">
              <h2>{{ feed.title }}</h2>
              <div class="product-preview__description">
                <p>{{ feed.description }}</p>
              </div>
            </div>
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
            <button class="button-write__review" @click.prevent="createComment">Оставить отзыв</button>
          </div>

          <div class="product-comments" style="width: 90%;">
            <div class="product-comments__title">
              <h2>Comments</h2>
            </div>
            <div class="product-comments__content">
              <div v-for="comment in this.feed.feedComments" :key="comment.id" class="product-comment"
                   style="max-width: 70%; margin-top: 10px; border-radius: 25px; padding: 10px; background-color: white;">
                <div class="product-comment__title"
                     style="display: flex; justify-content: space-between; align-items: center;">
                  <h4 style="padding: 0; margin: 0;">{{ comment.author.email }}</h4>
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
    </div>
  </div>
</template>

<script>
import axios from "axios";
import authHeader from "@/services/auth-header";

export default {
  name: "FeedDetailView",
  data() {
    return {
      comment:{
        comment:"",
        rating: 1,
      }
    }
  },
  computed: {
    feed() {
      return this.$store.getters.getCurrentFeedDetail;
    },
    feedCategoriesList(){
      return this.$store.getters.getCategoryFeedList;
    }
  },
  methods: {
    loadProductDetail() {
      this.$store.dispatch("loadFeedDetail", {id: this.$route.params.id})
    },
    loadCategoriesFeed(){
      this.$store.dispatch("loadCategoriesFeed");
    },
    createComment() {
      axios.post('http://localhost:8081/feed/comments/' + this.$route.params.id,
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
    },
  },
  mounted() {
    this.loadProductDetail();
    this.loadCategoriesFeed();
  }
}
</script>

<style lang="scss" scoped>
@import './src/colors';

.filter__price-left,
.filter__price-right {
  width: 100%;
  font-size: 20px;
  text-indent: 5px;
  border-right: 1px solid grey;
  height: 35px;
  border-radius: 15px 0px 0px 15px;
}

.filter__price-right {
  border-radius: 0px 15px 15px 0px;
}

.filter__price-label {
  margin: 0px 0px 2px 0px;
  font-size: 15px;
  font-weight: 200;

}

.filter__price-from {
  width: 50%;
}

.price-range {
  display: flex;
}

.product__reviews{
  width: 100%;
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

h4 {
  font-size: 22px;
  margin: 25px 0px 10px 0px;
}

.filter__price-range {
  margin: 10px 0px;
}

.sort_product_select {
  width: 120px;
  height: 30px;
  border: 1px solid #999;
  font-size: 18px;
  font-weight: 500;
  color: #1c87c9;
  background-color: #eee;
  border-radius: 5px;
}

.products-catalog__filter {
  margin-top: 8px;
  padding: 15px;
  border-radius: 15px;
  position: sticky;
  top: 10px;
}

.product__grid {
  width: 32% !important;
  max-width: none;
  min-width: 200px;
  margin: 1% 1% 0px 0px;
}

.products__article {
  display: flex;
  justify-content: space-between;
  align-items: start;
}

.products-catalog__filter {
  min-width: 280px;
  background: #ffffff;

ul > li > a {
  text-decoration: none;
  color: #999999;
}

}

.product-catalog__grip {
  display: flex;
  flex-wrap: wrap;
  margin-left: 10px;
  justify-content: space-between;
}

.products-catalog__title {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

ul {
  margin: 0px;
  list-style-type: none;
  padding: 0px;
}

ul > li > label {
  margin: 5px;
}

ul > li {
  font-size: 18px;
  padding: 2px;
  display: flex;
  align-items: center;
}

input[type=radio] {
  width: 20px;
  height: 20px;
}

ul.breadcrumb {
  padding: 0px 16px;
  list-style: none;
}

ul.breadcrumb li {
  display: inline;
  font-size: 18px;
}

ul.breadcrumb li + li:before {
  padding: 8px;
  color: black;
  content: "/\00a0";
}

ul.breadcrumb li a {
  color: #6c7073;
  text-decoration: none;
}

ul.breadcrumb li a:hover {
  color: #01447e;
  text-decoration: underline;
}

@media screen and (max-width: 1000px) {
  .product__grid {
    width: 49% !important;
    max-width: none;
    min-width: 200px;
  }
}

@media screen and (max-width: 820px) {
  .product__grid {
    width: 90% !important;
    max-width: 400px;
    min-width: 200px;
    margin: 1% auto;

  }
}

@media screen and (max-width: 600px) {
  .product-catalog__grip {
    margin-left: 0px;
  }

  .products-catalog__filter {
    position: inherit;
    margin-bottom: 10px;
  }

  .products__article {
    flex-wrap: wrap;
  }

  .product__grid {
    width: 48% !important;
    max-width: none;
    min-width: 150px;
    margin: 1% auto;

  }

  .products-catalog__filter {
    width: 100%;
  }
}
</style>