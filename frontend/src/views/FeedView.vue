<template>
  <UpButtonPage/>
  <div class="">
    <div class="container">
      <div class="products__article">
        <div class="products-catalog__filter">
          <div class="filter__category">
            <h4 style="margin-top: 0px;">Категории</h4>
            <ul>
              <li v-for="category in feedCategoriesList" :key="category.id">
                <router-link :to="{name: 'FeedView', params: {id: category.id}}">
                  {{ category.title }}
                </router-link>
              </li>
            </ul>
          </div>
          <div class="filter__price-range" style="border-top: 1px solid grey;">
            <h4>Цена</h4>
            <div class="price-range">
              <div class="filter__price-from">
                <p class="filter__price-label">от</p>
                <input class="filter__price-left" type="number">
              </div>
              <div class="filter__price-from">
                <p class="filter__price-label">до</p>
                <input class="filter__price-right" type="number">
              </div>
            </div>
          </div>
        </div>
        <div class="product-catalog__grip" style="width: 100%; margin-top: 10px;">


          <div class="feed-preview" v-for="feed in feedList" :key="feed.id"
               style="margin-bottom: 10px; width: 100%; border-radius: 20px; display: flex; justify-content: space-between; align-items: center; background-color: white; padding: 10px;">
            <div class="product-preview__content" style="width: 70%; padding: 5px;">
              <router-link :to="{name: 'FeedDetailView', params: {id: feed.id}}" class="product__title">
                <h2>{{ feed.title }}</h2>
              </router-link>
              <div class="product-preview__description">
                <p>{{ feed.description }}</p>
              </div>
              <div class="product-preview_continue">
                <p>Learn more</p>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import UpButtonPage from "@/components/UpButtonPage.vue";

export default {
  name: "FeedView",
  components: {UpButtonPage},
  methods: {
    loadFeeds() {
      if (this.$route.params.id) {
        this.$store.dispatch("loadFeedsById", {id: this.$route.params.id});
      } else {
        this.$store.dispatch("loadFeeds");
      }
    },
    loadCategoriesFeed() {
      this.$store.dispatch("loadCategoriesFeed");
    },
  },
  computed: {
    feedList() {
      return this.$store.getters.getFeedList;
    },
    feedCategoriesList() {
      return this.$store.getters.getCategoryFeedList;
    }
  },
  watch: {
    '$route.params.id': {
      handler() {
        this.loadFeeds();
      }
    }
  },
  mounted() {
    this.loadFeeds();
    this.loadCategoriesFeed();
    // this.loadProducts();
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