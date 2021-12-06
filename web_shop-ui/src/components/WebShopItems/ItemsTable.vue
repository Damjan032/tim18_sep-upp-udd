<template>
  <div>
    <v-data-table
        :headers="headers"
        :items="webShopItems"
        item-key="name"
        class="elevation-1"
        :search="search"
        :custom-filter="filterOnlyCapsText"
    >
      <template v-slot:top>
        <v-text-field
            v-model="search"
            label="Search"
            class="mx-4"
        ></v-text-field>
      </template>
      <template v-slot:item.basket="{ item }">
        <v-icon
            color="gray"
            @click="addToBasket(item)"
        >
          mdi-basket
        </v-icon>
      </template>

      <template v-slot:body.append>
        <tr>
          <td></td>
          <td>
            <v-text-field
                v-model="price"
                type="number"
                label="Less than"
            ></v-text-field>
          </td>
          <td colspan="4"></td>
        </tr>
      </template>
    </v-data-table>
  </div>
</template>

<script>
import {mapActions, mapState} from "vuex";

export default {
  name: "ItemsTable",
  data () {
    return {
      search: '',
      price: '',
    }
  },

  computed: {
    ...mapState('webShopItems', ['webShopItems']),
    headers () {
      return [
        {
          text: 'Name',
          align: 'start',
          sortable: false,
          value: 'name',
        },
        {
          text: 'Price \t$',
          value: 'price',
          filter: value => {
            if (!this.price) return true

            return value < parseInt(this.price)
          },
        },
        { text: 'Type', value: 'type'},
        { text: 'Description', value: 'description' },
        { text: 'Add to basket', value: 'basket' },
      ]
    },
  },
  methods: {
    ...mapActions('basket', ['addBasketItemApi']),
    addToBasket(item){
      console.log(item)
      this.addBasketItemApi(item)
      console.log("ADD TO BASKET")
    },
    filterOnlyCapsText (value, search) {
      return value != null &&
          search != null &&
          typeof value === 'string' &&
          value.toString().toLocaleUpperCase().indexOf(search.toLocaleUpperCase()) !== -1
    },
  },
}
</script>

<style scoped>

</style>