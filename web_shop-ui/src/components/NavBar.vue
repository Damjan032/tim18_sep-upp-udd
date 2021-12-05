<template>
  <v-app-bar
      absolute
      color="teal lighten-3"
      flat
      app
      dark
  >
    <v-toolbar-title >Web Shop</v-toolbar-title>

    <v-spacer></v-spacer>
    <v-icon>mdi-basket</v-icon>
    <avatar-menu :menu-active="menuActive"></avatar-menu>
  </v-app-bar>
</template>

<script>
import AvatarMenu from "./Auth/AvatarMenu";

export default {
  name: "NavBar",
  components: {AvatarMenu},
  data: () => ({
    menuActive: false
  }),
}
</script>

<style scoped>
</style>
