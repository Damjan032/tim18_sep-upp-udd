<template>
  <v-container>
    <div class="home" align="center"
         justify="center">
      <items-table/>
    </div>

  </v-container>
</template>

<script>

import ItemsTable from "@/components/WebShopItems/ItemsTable";
export default {
  name: 'Home',
  components: {ItemsTable},
  data: function(){ return{
        dialog:true,
        active: [],
        requests: []
        }
  },
  mounted(){
  },
  methods: {}
}
</script>
