<template>
  <v-container>
  <div class="home" align="center"
       justify="center">
    <basket-table/>
  </div>
  <v-btn color="primary" @click="createInvoice()">
    Buy
  </v-btn>
  </v-container>
</template>

<script>
import BasketTable from "@/components/WebShopItems/BasketTable";
import {web_api, api_invoice} from "@/utils/paths";
import axios from "axios";

export default {
  name: "Basket",
  components: {BasketTable},
  methods: {
    redirectToPsp(path) {
      //console.log(this.$store.state.basket.basketItems);
      console.log("redirektuj");
      window.location.href = 'http' + path.slice(5, path.length);
    },
    createInvoice() {
      axios.post(`${web_api}/${api_invoice}`, {
        products: this.$store.state.basket.basketItems,
        courses: []
      })
      .then((response) => {
        console.log(response.data);
        this.redirectToPsp(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
    }
  }
}
</script>

<style scoped>

</style>