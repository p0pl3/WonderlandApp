<template>
  <div class="container">
    <user-auth-form @submitForm="signUpUser">
      <template v-slot:title>Регистрация</template>
      <template v-slot:btn_form>Зарегистрироваться</template>
      <template v-slot:title__choice>
        <router-link to="/login" class="redirect_choice">or Login</router-link>
      </template>
    </user-auth-form>
  </div>
</template>

<script>
import UserAuthForm from "@/components/UserAuthForm";

export default {
  name: "SingUpView",
  components: {UserAuthForm},
  data() {
    return {}
  },
  methods: {
    signUpUser(userLog) {
      this.$store.dispatch("register", {email: userLog.email, password: userLog.password});
      this.$router.push('/profile');
    }
  },
  watch: {
    '$store.state.curUser': function () {
      this.$router.push("/");
    }
  }
}
</script>

<style scoped>
.redirect_choice {
  margin-left: 15px;
  color: grey;
  font-size: 20px;
}
</style>