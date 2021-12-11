<template>
  <div id="app">
      <Home></Home>
  </div>
</template>

<script>
import Home from './views/Home.vue';

export default {
  name: 'App',
  components: {Home},
  data: () => ({
  }),
};
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
}
</style>
