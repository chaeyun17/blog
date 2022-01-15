<template>
  <div id="app">
    <b-container>
      <h3>My Blog</h3>
      <router-view/>
    </b-container>
  </div>
</template>

<script>
// import Home from './components/Home.vue'

export default {
  name: 'App',
  components: {
    // Home
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  text-align: left;
}
</style>
