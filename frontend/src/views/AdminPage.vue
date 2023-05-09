<template>
  <div class="container">
    <div class="order">
      <div class="order__title">
        <h1>Добавление товара</h1>
      </div>
      <div class="order__form">

        <div class="input__form required">
          <label class="input__title" for="title">
            Название товара
          </label>
          <input class="my__input" type="text" id="title" v-model="this.product.title">
        </div>

        <div class="input__form required">
          <label class="input__title" for="description">
            Описание
          </label>
          <input class="my__input" type="text" id="description" v-model="this.product.description">
        </div>

        <div class="input__form required">
          <label class="input__title" for="price">
            Цена
          </label>
          <input class="my__input" type="text" id="price" v-model="this.product.price">
        </div>

        <div class="delivery__methods">
          <h3>скидка</h3>
          <label class="method" for="discount_0">
            <div class="method-input">
              <input name="discount" class="my__input-radio" type="radio" id="discount_0" :value=0
                     v-model.number="this.product.discount">
              <span></span>
            </div>
            <div class="method-info">
              <h4>0%</h4>
            </div>
          </label>
          <label class="method" for="discount_10">
            <div class="method-input">
              <input name="discount" class="my__input-radio" type="radio" id="discount_10" :value=10
                     v-model.number="this.product.discount">
              <span></span>
            </div>
            <div class="method-info">
              <h4>10%</h4>
            </div>
          </label>
          <label class="method" for="discount_30">
            <div class="method-input">
              <input name="discount" class="my__input-radio" type="radio" id="discount_30" :value=30
                     v-model.number="this.product.discount">
              <span></span>
            </div>
            <div class="method-info">
              <h4>30%</h4>
            </div>
          </label>
          <label class="method" for="discount_50">
            <div class="method-input">
              <input name="discount" class="my__input-radio" type="radio" id="discount_50" :value=50
                     v-model.number="this.product.discount">
              <span></span>
            </div>
            <div class="method-info">
              <h4>50%</h4>
            </div>
          </label>
        </div>

        <div class="payment__methods">
          <h3>Категория</h3>
          <label v-for="category in categoryList" :key="category.id" class="method" :for="category.title">
            <div class="method-input">
              <input name="category" class="my__input-radio" type="radio" :id="category.title" :value="category.id"
                     v-model.number="this.product.categoryId">
              <span></span>
            </div>
            <div class="method-info">
              <h4>{{ category.title }}</h4>
            </div>
          </label>
        </div>

        <div class="payment__methods">
          <h3>Период доставки</h3>
          <label class="method" for="delivery_1">
            <div class="method-input">
              <input name="payment_method" class="my__input-radio" type="radio" id="delivery_1" :value=1
                     v-model.number="this.product.delivery_period">
              <span></span>
            </div>
            <div class="method-info">
              <h4>1 день</h4>
            </div>
          </label>
          <label class="method" for="delivery_2">
            <div class="method-input">
              <input name="payment_method" class="my__input-radio" type="radio" id="delivery_2" :value=2
                     v-model.number="this.product.delivery_period">
              <span></span>
            </div>
            <div class="method-info">
              <h4>2 дня</h4>
            </div>
          </label>
          <label class="method" for="delivery_3">
            <div class="method-input">
              <input name="payment_method" class="my__input-radio" type="radio" id="delivery_3" :value=3
                     v-model.number="this.product.delivery_period">
              <span></span>
            </div>
            <div class="method-info">
              <h4>3 дня</h4>
            </div>
          </label>
        </div>
        <div class="product__image">
          <h3>Фотографии</h3>
          <label class="method" for="image">
            <div class="method-input">
              <input type="file" id="files" ref="files" multiple v-on:change="handleFilesUpload()">
            </div>
          </label>
        </div>
        <button class="confirm__order" @click="createProduct">Добавить товар</button>
      </div>
    </div>


    <div class="order">
      <div class="order__title">
        <h1>Добавление категории</h1>
      </div>
      <div class="order__form">
        <div class="input__form required">
          <label class="input__title" for="title_cat">
            Название категории
          </label>
          <input class="my__input" type="text" id="title_cat" v-model="category.title">
        </div>
        <button class="confirm__order" @click="createCategory">Добавить категорию</button>
      </div>


      <div class="order__title">
        <h1>Добавление категории новостей</h1>
      </div>
      <div class="order__form">
        <div class="input__form required">
          <label class="input__title" for="title_cat_feed">
            Название категории
          </label>
          <input class="my__input" type="text" id="title_cat_feed" v-model="category_feed.title">
        </div>
        <button class="confirm__order" @click="createFeedCategory">Добавить категорию новостей</button>
      </div>


      <div class="order__title">
        <h1>Добавление новости</h1>
      </div>
      <div class="order__form">
        <div class="input__form required">
          <label class="input__title" for="feed_title">
            Заголовок новости
          </label>
          <input class="my__input" type="text" id="feed_title" v-model="feed.title">
        </div>
        <div class="input__form required">
          <label class="input__title" for="feed_descr">
            Описание новости
          </label>
          <input class="my__input" type="text" id="feed_descr" v-model="feed.description">
        </div>
        <div class="payment__methods">
          <h3>Категория</h3>
          <label v-for="category in categoryFeedList" :key="category.id" class="method" :for="category.title">
            <div class="method-input">
              <input name="category" class="my__input-radio" type="radio" :id="category.title" :value="category.id"
                     v-model.number="this.feed.categoryId">
              <span></span>
            </div>
            <div class="method-info">
              <h4>{{ category.title }}</h4>
            </div>
          </label>
        </div>
        <button class="confirm__order" @click="createFeed">Добавить новость</button>
      </div>
      <button @click.prevent="logOut">Выйти</button>
    </div>

  </div>
</template>

<script>
import axios from "axios";
import authHeader from "@/services/auth-header";

export default {
  name: "AdminPage",
  data() {
    return {
      product: {
        title: "",
        description: "",
        discount: 0,
        price: 0,
        delivery_period: 1,
        categoryId: null,
      },
      files: [],
      category: {
        title: "",
      },
      feed: {
        title: "",
        description: "",
        categoryId: null,
      },
      category_feed: {
        title: "",
      }
    }
  },
  computed: {
    categoryList() {
      return this.$store.getters.getCategoryList;
    },
    categoryFeedList() {
      return this.$store.getters.getCategoryFeedList;
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch('logout')
      this.$router.push('/');
    },
    handleFilesUpload() {
      this.files = this.$refs.files.files;
      console.log(this.files)
    },
    createProduct() {
      let formData = new FormData();
      formData.append('product', new Blob([JSON.stringify(this.product)], {
        type: "application/json"
      }));
      for (let i = 0; i < this.files.length; i++) {
        formData.append('files', this.files[i]);
      }
      axios.post('http://localhost:8081/product/',
          formData,
          {
            headers: {
              'Authorization': authHeader().Authorization,
              'Content-Type': 'multipart/form-data'
            }
          })
          .then(function () {
            console.log('SUCCESS!!');
          })
          .catch(function () {
            console.log('FAILURE!!');
          });
    },
    createCategory() {
      axios.post('http://localhost:8081/category/',
          this.category,
          {
            headers: {
              'Authorization': authHeader().Authorization
            }
          })
          .then(() => {
            console.log('SUCCESS!!');
            this.$store.dispatch('loadCategories')
          })
          .catch(() => {
            console.log('FAILURE!!');
          });
    },
    createFeedCategory() {
      axios.post('http://localhost:8081/feed_category/',
          this.category_feed,
          {
            headers: {
              'Authorization': authHeader().Authorization
            }
          })
          .then(() => {
            console.log('SUCCESS!!');
            this.$store.dispatch('loadCategoriesFeed')
          })
          .catch(function () {
            console.log('FAILURE!!');
          });
    },
    createFeed() {
      axios.post('http://localhost:8081/feed/',
          this.feed,
          {
            headers: {
              'Authorization': authHeader().Authorization
            }
          })
          .then(function () {
            console.log('SUCCESS!!');
          })
          .catch(function () {
            console.log('FAILURE!!');
          });
    }
  },
  mounted() {
    this.$store.dispatch('loadCategories')
    this.$store.dispatch('loadCategoriesFeed')
  },
  watch: {
    'product': {
      handler() {
        console.log(this.product)
      },
      deep: true,
    }
  }
}

</script>

<style lang="scss" scoped>
@import './src/colors';
h1, h2 {
  margin: 20px 0 20px 0;
}

h3 {
  font-size: 16px;
  font-weight: normal;
  margin: 0 0 5px 0;
}

h4 {
  margin: 5px 0 5px 0;
  font-size: 20px;
  font-weight: normal;
}

h5 {
  font-weight: normal;
  color: $base-grey;
  font-size: 15px;
  margin: 5px 0 5px 0;
}

h6 {
  font-size: 20px;
  margin: 0;
  text-align: end;
}

.container {
  display: flex;
}

.order {
  width: 45%;
  margin-right: 15px;
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

.delivery__methods {
  width: 100%;
}

.method {
  width: 100%;
  display: flex;
  align-items: center;
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

.confirm__order {
  width: 100%;
  height: 50px;
  margin: 40px 0;
  border-radius: 10px;
  background: $base-background-white;
  font-size: 17px;
  color: #FFFFFF;
  border: none;
}

.required > label:after {
  content: "*";
  color: red;
  margin-left: 3px;
}

@media (max-width: 810px) {
  .container {
    display: block;
  }
  .basket {
    width: 100%;
    box-shadow: inset 0px -5px 4px -3px #cccccc;
  }
  .order {
    width: 100%;
    margin-right: 15px;
  }
}
</style>