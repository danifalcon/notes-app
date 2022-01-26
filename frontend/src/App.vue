<template>
  <div>
    <div v-if="notes.length">
      <div v-for="(note) in notes" :key="note.id" class="note-container">
        <span class="note-content">{{ note.id }}</span>
        <span class="note-content">{{ note.title }}</span>
        <span class="note-content">{{ note.message }}</span>
      </div>
    </div>
    <hr>
    <createNotesForm :authToken="this.authToken"/>
    <div class="login-container">
      <form ref="login-form">
        <label for="username">Username: </label><input type="text" id="username" v-model="username" required>
        <label for="password">Password: </label><input type="text" id="password" v-model="password" required>
        <button type="button" @click="login()">Log in</button>
      </form>
    </div>
    <div>
      <h3>Logged user is -> {{ loggedUser }}</h3>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import CreateNotesForm from "@/components/CreateNotesForm";

export default {
  name: 'HelloWorld',
  components: {CreateNotesForm},
  props: [],
  data() {
    return {
      loggedUser: null,
      authToken: null,
      username: null,
      password: null,
      notes: []
    }
  },
  beforeMount() {
    this.getAllNotes();
  },
  mounted() {
    this.$root.$on('getNotes', () => {
      this.getAllNotes();
    });
  },
  methods: {
    login() {
      axios.post('http://localhost:8080/login', {
        username: this.username,
        password: this.password
      }).then((response) => {
        this.authToken = response.data;
        this.loggedUser = this.username;
        this.getAllNotes();
      });
    },
    getAllNotes() {
      axios.get('http://localhost:8080/notes').then((response) => {
        this.notes = response.data;
      });
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
  background-color: #fff327;
  border: 1px solid black;
  width: 150px;
  height: 150px;
  padding: 5px 5px;
  display: inline-block;
  margin: 5px 5px
}

.login-container {
  position: fixed;
  margin-top: 20%;
  display: block;
}

.login-container form input {
  display: block;
}
</style>