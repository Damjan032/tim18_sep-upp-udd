<template>
  <div>
    <v-data-table
        :headers="headers"
        :items="basketItems"
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
            @click="deleteFromBasket(item)"
        >
          mdi-delete
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
  name: "BasketTable",
  data () {
    return {
      search: '',
      price: '',
    }
  },
  computed: {
    ...mapState('basket', ['basketItems']),
    headers () {
      return [
        {
          text: 'Name',
          align: 'center',
          sortable: false,
          value: 'name',
        },
        {
          text: 'Price \t$',
          value: 'price',
          align: 'center',
          filter: value => {
            if (!this.price) return true

            return value < parseInt(this.price)
          },
        },
        { text: 'Type', value: 'type', align: 'center'},
        { text: 'Description', value: 'description', align: 'center' },
        { text: 'Remove from the basket', value: 'basket', align: 'center' },
      ]
    },
  },
  methods: {
    ...mapActions('basket', ['deleteBasketItemApi']),
    deleteFromBasket(item){
      this.deleteBasketItemApi(item)
      console.log(item)
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