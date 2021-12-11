<template>
  <v-container>
  <div class="home" align="center"
       justify="center">
    <basket-table/>
  </div>
  <v-btn color="primary" @click="redirectToPsp()">
    Buy
  </v-btn>
  </v-container>
</template>

<script>
import BasketTable from "@/components/WebShopItems/BasketTable";
import {pspFrontPath} from "@/utils/paths";

export default {
  name: "Basket",
  components: {BasketTable},
  methods: {
    redirectToPsp() {
        window.location.href = pspFrontPath
    }
  }
}
</script>

<style scoped>

</style>