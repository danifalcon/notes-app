<template>
  <form ref="note-form">
    <label for="title">Title: </label><input type="text" id="title" v-model="titleInput" required>
    <label for="message">Message: </label><input type="text" id="message" v-model="messageInput" required>
    <button type="button" @click="createNote">Save</button>
  </form>
</template>
<script>
import axios from "axios";

export default {
  name: 'createNotesForm',
  props: ['authToken'],
  data() {
    return {
      titleInput: null,
      messageInput: null,
    }
  },
  methods: {
    createNote() {
      axios.post('http://localhost:8080/notes', {
            title: this.titleInput,
            message: this.messageInput
          },
          {
            headers: {
              Authorization: 'bearer ' + this.authToken
            }
          }).then((response) => {
        if (response.status === 200) {
          this.$root.$emit('getNotes');
          this.clearFormInputs();
        }
      });
    },
    clearFormInputs() {
      this.titleInput = null;
      this.messageInput = null;
    }
  }
}
</script>
<style scoped>

.login-container form input {
  display: block;
}
</style>