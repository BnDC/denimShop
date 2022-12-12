<template>
  <div class="cart">
    <div class="container">
      <ul>
        <li v-for="(i, idx) in state.items" :key="idx">
          <img :src="i['denimDto']['image']" alt=""/>
          <span class="name"> {{ i["denimDto"]["productCode"] }} </span>
          <span class="name"> {{ i["denimDto"]["price"] }}원 </span>
          <span class="name"> {{ i["quantity"] }} 개 </span>
          <i class="fa fa-minus" @click="update(i['denimDto']['denimId'], true)"></i>
          <i class="fa fa-plus" @click="update(i['denimDto']['denimId'], false)"></i>
        </li>
      </ul>
      <router-link to="/order" class="btn btn-primary">구입하기</router-link>
    </div>
  </div>
</template>

<script>
import {reactive} from "vue";
import axios from "axios";

export default {
  setup() {
    const state = reactive({
      items: []
    })

    const load = () => {
      axios.get("http://localhost:8080/api/v1/cart/list",
          {withCredentials: true}).then(({data}) => {
        state.items = data;
      })
    };

    const update = (itemId, isMinus) => {
      axios.patch(`http://localhost:8080/api/v1/cart/denims/${itemId}?isMinus=${isMinus}`
          , {}, {withCredentials: true}).then(({data}) => {
        load();
        state.items = data;
      });
    }
    load();

    return {state, update}
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