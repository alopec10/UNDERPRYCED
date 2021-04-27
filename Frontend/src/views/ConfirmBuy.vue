<template>
  <div>
    <button @click="confirmBuy">
      CONFIRMAR
    </button>
  </div>

</template>

<script>
export default {
  name: "ConfirmBuy",
  data() {
    return {
      product: {
        title: "",
        url: "",
        retailPrice: null,
        date: "",
        ref: "",
        brand: "",
        colorway: "",
        name: "",
        model: "",
        year: "",
        gender: "",
        categoryType: "",
        productDetails: []
      },
      size: "",
      price: 340,
      fees: 0,
      shipping: 15,
      totalPrice: 0,
      idPayMethod: null,
    }
  },
  mounted() {
    this.getProduct()
    this.size = this.$route.params.size
  },
  methods: {
    getProduct() {
      const dat = {
        "specifications": [
          {
            "key": "ref",
            "value": this.$route.params.ref,
            "operator": "=="
          }
        ]
      }
      axios({url: 'http://localhost:8888/product/specification', data: dat, method: 'POST'})
          .then(resp => {
            this.product = resp.data[0]
            for (let pd of this.product.productDetails) {
              if (this.size == pd.size) {
                this.price = pd.lowestAsk
                this.fees = 0.1 * this.price
                this.totalPrice = this.price + this.fees + this.shipping
              }
            }
          })
          .catch(err => {
          })
    },
    confirmBuy() {
      //MODIFY WHEN IMPLEMENTED
      const dat = {
        "idPayMethod": this.idPayMethod,
        "ref": this.ref,
        "size": this.size,
        "address": this.address,
        "country": this.country,
        "zipCode": this.zipCode,
      }
      axios({url: 'http://localhost:8888/order/createBuy', data: dat, method: 'POST'})
          .then(resp => {
            alert("Venta creada correctamente")
          })
          .catch(err => {
          })
    },
  }

}
</script>

<style scoped>

</style>