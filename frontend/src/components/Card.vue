<template>
  <div class="card mb-4 product-wap rounded-0">
    <div class="card rounded-0">
      <img class="card-img rounded-0 img-fluid" :src="item.image">
      <div class="card-img-overlay rounded-0 product-overlay d-flex align-items-center justify-content-center">
        <ul class="list-unstyled">
          <li><a class="btn btn-success text-white" href="#"><i class="far fa-heart"></i></a></li>
          <li><a class="btn btn-success text-white mt-2" href="#"><i class="far fa-eye"></i></a></li>
          <li><a class="btn btn-success text-white mt-2" @click="addToCart(item.denimId)"><i
              class="fas fa-cart-plus"></i></a>
          </li>
        </ul>
      </div>
    </div>
    <div class="card-body">
      <a href="shop-single.html" class="h3 text-decoration-none">{{ item.productCode }}</a>
      <ul class="w-100 list-unstyled d-flex justify-content-between mb-0">
        <li>{{ item.description }}</li>
        <li class="pt-2">
          <span class="product-color-dot color-dot-red float-left rounded-circle ml-1"></span>
          <span class="product-color-dot color-dot-blue float-left rounded-circle ml-1"></span>
          <span class="product-color-dot color-dot-black float-left rounded-circle ml-1"></span>
          <span class="product-color-dot color-dot-light float-left rounded-circle ml-1"></span>
          <span class="product-color-dot color-dot-green float-left rounded-circle ml-1"></span>
        </li>
      </ul>
      <ul class="list-unstyled d-flex justify-content-center mb-1">
        <li>
          <i class="text-warning fa fa-star"></i>
          <i class="text-warning fa fa-star"></i>
          <i class="text-warning fa fa-star"></i>
          <i class="text-muted fa fa-star"></i>
          <i class="text-muted fa fa-star"></i>
        </li>
      </ul>
      <p class="text-center mb-0">{{ item.price }}원</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Card",
  props: {
    item: Object
  },
  setup() {
    const addToCart = (itemId) => {
      axios.post(`http://localhost:8080/api/v1/cart/new/denims/${itemId}`,
          {}, {withCredentials: true}).then(() => {
        alert("장바구니에 담겼습니다");
      }).catch(error => {
        if (error.response.status === 401) {
          alert("로그인 해주세요!");
        }
      })
    };
    return {addToCart}
  }
}
</script>

<style scoped>
</style>