<template>
  <div class="home">
    <h2>Payment Options</h2>
    <table>
      <tbody>
        <tr :key="option.id" v-for="option in paymentOptions">
          <td>
            <img v-bind:src="option.src">
          </td>
          <td class="name">
            {{option.name}}
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
// @ is an alias to /src
import axios from "axios";
import {gateway, api_invoice} from "../utils/paths";

export default {
  name: 'Home',
  data() {
    return{
      paymentOptions: [
        {id: 1, src: require('../assets/card.jpg'), name: "Credit card" },
        {id: 2, src: require('../assets/qr.jpg'), name: "QR code" },
        {id: 3, src: require('../assets/paypal.jpg'), name: "PayPal" },
        {id: 4, src: require('../assets/bitcoin.jpg'), name: "BitCoin" }],
      invoiceId: ""
    }
  },
  methods: {
    payInvoice() {
      axios
      .post(`${gateway}/${api_invoice}/${this.invoiceId}`)
      .then((response) => {
        console.log(response.data);
      });
    }
  },
  created() {
    this.invoiceId = this.$route.params.invoice
    console.log(this.$route.params);
    console.log(this.$route.params.invoice);
  }

}
</script>

<style>
body {
  font-family: "lato", sans-serif;
}
.home {
  max-width: 500px;
  margin-left: auto;
  margin-right: auto;
  margin-top: 50px;
  padding-left: 10px;
  padding-right: 10px;
}

table{
  margin-top: 30px;
}

h2 {
  font-size: 26px;
  margin: 20px 0;
  text-align: center;
}

td.name{
  text-align: center;
  width: 100%;
  color: honeydew;
}

tr {
    background-color: #363636;
}

img{
    width:100px;
    height: auto;
}
</style>
