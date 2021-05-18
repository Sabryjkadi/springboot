<template>
  <div id="app">
    <h1 class="subtitle has-text-centered">Bucket List:</h1>
    <hr />

    <div class="field has-addons">
      <div class="control is-expanded">
        <input class="input" v-model="details" type="text" placeholder="Go to mars..." />
      </div>
      <div class="control">
        <a class="button is-info" @click="addItem" :disabled="!details">Add</a>
      </div>
    </div>
    <div class="notification" v-for="(item, i) in items" :key="item.id">
      <div class="columns">
        <input class="column input" v-if="isSelected(item)" v-model="editeddetails" />
        <p v-else class="column">
          <span class="tag is-primary">{{ i + 1}}</span>
          {{ item.details }}
        </p>
        <div class="column is-narrow">
          <span
              class="icon has-text-primary"
              @click="isSelected(item) ?  unselect() : select(item)"
          >
            <i class="material-icons">{{isSelected(item) ? 'close': 'edit'}}</i>
          </span>

          <span
              class="icon has-text-info"
              @click="isSelected(item) ? updateItem(item, i) : removeItem(item, i)"
          >
            <i class="material-icons">{{isSelected(item) ? 'save': 'delete'}}</i>
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "App",
  data() {
    return {
      items: [],
      details: "",
      editeddetails: "",
      selected: {}

    };
  },
  async mounted() {
    var response = await axios.get("http://localhost:8095/todo");
    this.items = response.data;
  },
  methods: {
    async addItem() {
      const response = await axios.post("http://localhost:8095/todo", {
        details: this.details
      });

      this.items.push(response.data);
      this.details = "";

    },
    async removeItem(item, i) {
      await axios.delete("http://localhost:8095/todo/" + item.id);
      this.items.splice(i, 1);
    },

    select(item) {
      this.selected = item;
      this.editeddetails = item.details;
    },
    isSelected(item) {
      return item.id === this.selected.id;
    },
    unselect() {
      console.log("unselect")
      this.selected = {};
      this.editeddetails = "";
    },

    async updateItem(item, i) {

      const response = await axios.put("http://localhost:8095/todo/" + item.id, {
        details: this.editeddetails
      });
      this.items[i] = response.data;

      window.location.reload()    }
  }
};
</script>

<style>
#app {
  margin: auto;
  margin-top: 3rem;
  max-width: 700px;
}
.icon {
  cursor: pointer;
}
</style>