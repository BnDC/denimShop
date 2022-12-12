<template>
  <div>
    <div class="py-5 text-center">
      <h2>결제</h2>
    </div>
    <div class="row g-5">
      <div class="col-md-5 col-lg-4 order-md-last">
        <h4 class="d-flex justify-content-between align-items-center mb-3">
          <span class="text-primary">구입 목록</span>
          <span class="badge bg-primary rounded-pill">{{state.items.length}}</span>
        </h4>
        <ul class="list-group mb-3">
          <li class="list-group-item d-flex justify-content-between lh-sm" v-for="(i, idx) in state.items" :key="idx">
            <div>
              <h6 class="my-0"></h6>
              <span class="name">{{ i["denimDto"]["productCode"] }}</span>
            </div>
            <span class="text-muted">{{ i["denimDto"]["price"] }} 원</span>
            <span class="quantity">{{ i["quantity"] }} 개</span>
          </li>
        </ul>

        <h2 class="total-price">
          최종 가격 : {{ computedPrice }} 원
        </h2>
      </div>
      <div class="col-md-7 col-lg-8">
        <h4 class="mb-3">주문 정보</h4>
        <form class="needs-validation" novalidate>
          <div class="row g-3">

            <div class="col-12">
              <label for="username" class="form-label">사용자 이름</label>
              <div class="input-group has-validation">
                <span class="input-group-text">@</span>
                <input type="text" class="form-control"
                       id="username"
                       v-model="state.form.orderer"
                >
                <div class="invalid-feedback">
                  Your username is required.
                </div>
              </div>
            </div>

            <div class="col-12">
              <label for="address" class="form-label">Address</label>
              <input type="text"
                     class="form-control"
                     id="address"
                     v-model="state.form.address"
              >
              <div class="invalid-feedback">
                Please enter your shipping address.
              </div>
            </div>

<!--            <div class="col-md-3">-->
<!--              <label for="zip" class="form-label">Zip</label>-->
<!--              <input type="text" class="form-control" id="zip" placeholder="" required>-->
<!--              <div class="invalid-feedback">-->
<!--                Zip code required.-->
<!--              </div>-->
<!--            </div>-->
          </div>
          <hr class="my-4">

          <button class="w-100 btn btn-primary btn-lg" @click="submit()">구입 하기</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {computed, reactive} from "vue";

export default {
  setup() {
    const state = reactive({
      items: [],
      form : {
        "orderer" : "",
        "address" : ""
      }
    })

    const load = () => {
      axios.get("http://localhost:8080/api/v1/cart/list", {withCredentials: true})
          .then(({data}) => {
            state.items = data;
          });
    }

    const submit = () => {
      axios.post("http://localhost:8080/api/v1/orders/new", state.form, {withCredentials: true})
          .then(({data}) => {
            state.items = data;
          });
    }

    const computedPrice = computed(() => {
      let result = 0;

      for (let i of state.items) {
        result += i.quantity * i["denimDto"]["price"];
      }

      return result;
    })

    load();
    return {state, computedPrice, submit};
  }
}
</script>

<style scoped>
.cart ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.cart ul li {
  border: 1px solid #eee;
  margin-top: 25px;
  margin-bottom: 25px;
}

.cart ul li img {
  width: 150px;
  height: 150px;
}

.cart ul li img {
  width: 150px;
  height: 150px;
}

.cart ul li .name {
  margin-left: 25px;
}

.cart ul li .price {
  margin-left: 25px;
}

.cart ul li i {
  float: right;
  font-size: 20px;
  margin-top: 65px;
  margin-right: 50px;
}

.cart .btn {
  width: 300px;
  display: block;
  margin: auto;
  padding: 30px 50px;
  font-size: 20px;
}

</style>