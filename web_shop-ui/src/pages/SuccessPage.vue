<template>
    <v-container>
      <v-card
          elevation="2"
           justify="center">
        <div class="text-overline mb-4">
          Report
        </div>
        <v-card-title primary-title class="justify-center">
          <div>
            <h3 class="headline green--text text--accent-2">SUCCESS</h3>
            <div>Rookie Aviator Patched BomberproductItem.description</div>
          </div>
        </v-card-title>
      </v-card>
    </v-container>
</template>

<script>
import axios from "axios";
import {api_invoice, web_api} from "@/utils/paths";

export default {
  name: "SuccessPage",
  methods:{
    getUnits: function() {
      console.log(this.$route.query.status)
      console.log(this.$route.query.invoceId)
      console.log(this.$route.query.type)
      console.log("PERA")

      axios.post(`${web_api}/${api_invoice}/pp-btc-transaction`, {
        invoiceId:this.$route.query.invoceId,
        type:this.$route.query.type,
        status:this.$route.query.status
      })
          .then((response) => {
            console.log(response.data);
            this.redirectToPsp(response.data);
          })
          .catch((error) => {
            console.log(error);
          });

    }
  },
  created: function(){
    this.getUnits()
  }
}
</script>

<style scoped>

</style>