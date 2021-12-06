<template>
  <v-container>
  <div class="home" align="center"
       justify="center">
    <basket-table/>
  </div>
  </v-container>
</template>

<script>
import BasketTable from "@/components/WebShopItems/BasketTable";
export default {
  name: "Basket",
  components: {BasketTable}
}
</script>

<style scoped>

</style>