<template>
  <div class="profile">
    <div class="container">
      <div class="profile__container">

        <div class="content delivery__address">
          <h1>Адрес доставки</h1>
          <form action="">
            <div class="input__form">
              <label>
                Город
                <input type="text">
              </label>
            </div>
            <div class="input__form">
              <label>
                Улица и дом
                <input type="text">
              </label>
            </div>
            <div class="other">
              <div class="input__form" style="margin-right: 10px">
                <label>
                  Подъезд
                  <input type="text">
                </label>
              </div>
              <div class="input__form" style="margin-right: 10px">
                <label>
                  Квартира
                  <input type="text">
                </label>
              </div>
              <div class="input__form">
                <label>
                  Этаж
                  <input type="text">
                </label>
              </div>
            </div>
            <button>Сохранить</button>
          </form>
        </div>
        <div class="content contacts">
          <h1>Контакты</h1>
          <form action="">
            <div class="input__form">
              <label>
                Имя
                <input type="text">
              </label>
            </div>
            <div class="input__form">
              <label>
                Телефон
                <input type="text">
              </label>
            </div>
            <div class="input__form">
              <label>
                Email
                <input type="text">
              </label>
            </div>
            <button>Сохранить</button>
          </form>

        </div>
        <div class="content order__history">
          <h1>История заказов</h1>
          <div class="orders">
            <OrderItem v-for="order in orderList" :key="order.id" :order="order"/>
            <p v-if="!orderList">Вы не сделали ещё ни одного заказа</p>
          </div>
        </div>
      </div>
      <button style="width: 100px" @click.prevent="logOut">Выйти</button>

    </div>
  </div>

</template>

<script>
import OrderItem from "@/components/OrderItem.vue";

export default {
  name: "ProfileView",
  components: {OrderItem},
  data() {
    return {}
  },
  methods: {
    logOut() {
      this.$store.dispatch('logout')
      this.$router.push('/');
    }
  },
  computed: {
    currentUser() {
      return this.$store.getters.getUser;
    },
    orderList() {
      return this.$store.getters.getOrderList;
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    } else if (!this.orderList) {
      this.$store.dispatch('loadOrderList')
    }
  }
}
</script>

<style lang="scss" scoped>
@import './src/colors';

.profile__container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.content {
  width: 31% !important;
  max-width: none;
  min-width: 200px;
}

.other {
  display: flex;
  justify-content: space-between;
  margin: 10px 0;
}

.other .input__form {
  min-width: 85px;
  margin: 0;
}

h1 {
  margin: 0;
  font-size: 30px;
}

p {
  font-size: 16px;
}

label {
  font-size: 17px;
}

input {
  display: block;
  width: 100%;
  border: 1px solid;
  border-radius: 12px;
  height: 40px;
  font-size: 17px;
  text-indent: 7px;
}

.input__form {
  margin: 10px 0 10px 0;
}

button {
  width: 100%;
  height: 40px;
  margin: 10px 0;
  border-radius: 10px;
  background: $base-background-white;
  font-size: 17px;
  color: #FFFFFF;
  border: none;
}

.orders {
  margin-top: 10px;
}

@media (max-width: 950px) {
  .content {
    width: 100% !important;
    margin: 0 0 15px 0;
  }
}
</style>