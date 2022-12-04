<template>
  <nav class="navbar navbar-expand-lg navbar-light shadow">
    <div class="container d-flex justify-content-between align-items-center">

      <router-link to="/" class="navbar-brand text-success logo h1 align-self-center">
        DenimShop
      </router-link>

      <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse"
              data-bs-target="#templatemo_main_nav" aria-controls="navbarSupportedContent" aria-expanded="false"
              aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="align-self-center collapse navbar-collapse flex-fill  d-lg-flex justify-content-lg-between"
           id="templatemo_main_nav">
        <div class="flex-fill">
          <ul class="nav navbar-nav d-flex justify-content-between mx-lg-auto">
          </ul>
        </div>
        <div class="navbar align-self-center d-flex">
          <router-link to="/cart" class="nav-icon position-relative text-decoration-none"
                       v-if="$store.state.account.id">
            <i class="fa fa-fw fa-cart-arrow-down text-dark mr-1"></i>
          </router-link>
          <router-link to="/signup" class="btn btn-toolbar" v-if="!$store.state.account.id">signup</router-link>
          <a class="nav-icon position-relative text-decoration-none" href="#">
            <router-link to="/login" class="btn btn-toolbar" v-if="!$store.state.account.id">login</router-link>
            <a to="/logout" class="btn btn-toolbar" @click="logout()" v-else>logout</a>
          </a>
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
import store from "@/scripts/store";
import router from "@/scripts/router";
import axios from "axios";

export default {
  name: 'Header',

  setup() {

    const state = {};

    const logout = () => {
      axios.post("http://localhost:8080/api/v1/members/logout", state,
          {withCredentials: true}).then(() => {
        store.commit('setAccount', 0);
        sessionStorage.removeItem("id");
        router.push({path: "/"});
        window.alert("로그아웃 하였습니다.");
      })
    }
    return {logout};
  }
}
</script>

<style scoped>
</style>
