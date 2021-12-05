<template>
  <v-app>
    <drawer v-if="isSuperAdmin" :mini="miniDrawer" @miniVariant="miniDrawer = !miniDrawer"/>
    <nav-bar />
    <v-main class="content">
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>
import NavBar from "../components/NavBar";
import Drawer from "../components/Drawer";
import {mapActions} from "vuex";
//import {SuperAdmin} from "@/utils/drawerItems";

export default {
  name: "Main",
  components: {Drawer, NavBar},
  data: () => ({
    miniDrawer: true,
  }),
  methods: {
    ...mapActions('webShopItems', ['getWebItems']),
    isSuperAdmin : function() {
      return true
    }
  },
  mounted() {
    this.getWebItems();
  }
}
</script>

<style scoped>
.content {
  background-color: #f0f0f0;
}
</style>
