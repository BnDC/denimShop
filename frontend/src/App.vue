<template>
  <Header/>
  <!-- Modal -->
  <div class="modal fade bg-white" id="templatemo_search" tabindex="-1" role="dialog"
       aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
      <div class="w-100 pt-1 mb-5 text-right">
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form action="" method="get" class="modal-content modal-body border-0 p-0">
        <div class="input-group mb-2">
          <input type="text" class="form-control" id="inputModalSearch" name="q" placeholder="Search ...">
          <button type="submit" class="input-group-text bg-success text-light">
            <i class="fa fa-fw fa-search text-white"></i>
          </button>
        </div>
      </form>
    </div>
  </div>
  <RouterView/>
</template>

<script>
import Header from './components/Header.vue'
import store from "@/scripts/store";
import {useRoute} from "vue-router/dist/vue-router";
import {watch} from "vue";
import axios from "axios";

export default {
  name: 'App',
  components: {
    Header
  },
  setup() {
    const check = () => {
      axios.get("http://localhost:8080/api/v1/members/check", {withCredentials: true})
          .then(({data}) => {
        store.commit("setAccount", data || 0);
      })
    };

    const route = useRoute();

    watch(route, () => {
      check();
    })
  },
}
</script>

<style>
@import '../src/assets/css/fontawesome.css';
@import '../src/assets/css/slick.min.css';
@import '../src/assets/css/slick-theme.css';
@import '../src/assets/css/templatemo.css';
</style>