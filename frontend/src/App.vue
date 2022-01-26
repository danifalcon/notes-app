<template>
  <div>
    <div v-for="(note) in notes" :key="note.id" class="note-container">
      <span class="note-content">{{ note.id }}</span>
      <span class="note-content">{{ note.title }}</span>
      <span class="note-content">{{ note.message }}</span>
    </div>
    <hr>
    <form ref="note-form">
      <label for="title">Title: </label><input type="text" id="title" v-model="titleInput">
      <label for="message">Message: </label><input type="text" id="message" v-model="messageInput">
      <button type="button" @click="createNote()">Save</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'HelloWorld',
  props: [],
  data() {
    return {
      titleInput: null,
      messageInput: null,
      notes: [
        {
          id: null,
          message: null,
          title: null
        }
      ]
    }
  },
  async beforeMount() {
    this.getAllNotes();
  },
  methods: {
    getAllNotes() {
      axios.get('http://localhost:8080/notes').then((response) => {
        this.notes = response.data;
      });
    },
    createNote() {
      axios.post('http://localhost:8080/notes', {
        title: this.titleInput,
        message: this.messageInput
      }).then((response) => {
        if (response.status === 200) {
          this.getAllNotes();
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

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.note-content {
  margin-top: 10px;
  display: block
}

.note-container {
  border: 1px solid black;
  width: 150px;
  height: 150px;
  padding: 5px 5px;
  display: inline-block;
  margin: 5px 5px
}
</style>