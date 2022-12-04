<template>
  <!-- Header -->
  <nav class="navbar navbar-expand-lg navbar-light shadow">
    <div class="container d-flex justify-content-between align-items-center">

      <a class="navbar-brand text-success logo h1 align-self-center" href="index.html">
        Zay
      </a>

      <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse"
              data-bs-target="#templatemo_main_nav" aria-controls="navbarSupportedContent" aria-expanded="false"
              aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="align-self-center collapse navbar-collapse flex-fill  d-lg-flex justify-content-lg-between"
           id="templatemo_main_nav">
        <div class="flex-fill">
          <ul class="nav navbar-nav d-flex justify-content-between mx-lg-auto">
            <li class="nav-item">
              <a class="nav-link" href="index.html">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="about.html">About</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="shop.html">Shop</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="contact.html">Contact</a>
            </li>
          </ul>
        </div>
        <div class="navbar align-self-center d-flex">
          <!--          <div class="d-lg-none flex-sm-fill mt-3 mb-4 col-7 col-sm-auto pr-3">-->
          <!--            <div class="input-group">-->
          <!--              <input type="text" class="form-control" id="inputMobileSearch" placeholder="Search ...">-->
          <!--              <div class="input-group-text">-->
          <!--                <i class="fa fa-fw fa-search"></i>-->
          <!--              </div>-->
          <!--            </div>-->
          <!--          </div>-->
          <!--          <a class="nav-icon d-none d-lg-inline" href="#" data-bs-toggle="modal" data-bs-target="#templatemo_search">-->
          <!--            <i class="fa fa-fw fa-search text-dark mr-2"></i>-->
          <!--          </a>-->
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
  <!-- Close Header -->
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

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
