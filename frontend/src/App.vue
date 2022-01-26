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
    <loginForm/>
    <div>
      <h3>Logged user is -> {{ this.loggedUser }}</h3>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import CreateNotesForm from "@/components/CreateNotesForm";
import LoginForm from "@/LoginForm";

export default {
  name: 'HelloWorld',
  components: {LoginForm, CreateNotesForm},
  props: [],
  data() {
    return {
      loggedUser: null,
      authToken: null,
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
    this.$root.$on('loginSuccessful', () => {
      this.authToken = localStorage.getItem('token');
      this.loggedUser = localStorage.getItem('loggedUser');
    });
  },
  methods: {
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
</style>