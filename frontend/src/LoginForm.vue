<template>
  <div class="login-container">
    <form ref="login-form">
      <label for="username">Username: </label><input type="text" id="username" v-model="username" required>
      <label for="password">Password: </label><input type="text" id="password" v-model="password" required>
      <button type="button" @click="login">Log in</button>
    </form>
  </div>
</template>
<script>
import axios from "axios";

export default {
  name: 'loginForm',
  props: [],
  data() {
    return {
      username: null,
      password: null
    }
  },
  methods: {
    login() {
      axios.post('http://localhost:8080/login', {
        username: this.username,
        password: this.password
      }).then((response) => {
        localStorage.setItem('token', response.data);
        localStorage.setItem('loggedUser', this.username);
        this.$root.$emit('loginSuccessful');
        this.$root.$emit('getNotes');
      });
    }
  }
}
</script>
<style scoped>

.login-container {
  margin-top: 5%;
}

.login-container form input {
  display: block;
}
</style>