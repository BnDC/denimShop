<template>
  <div class="container py-5">
    <div class="row">
      <div class="col-lg-3">
        <h1 class="h2 pb-4">Categories</h1>
        <ul class="list-unstyled templatemo-accordion">
          <li class="pb-3">
            <a class="collapsed d-flex justify-content-between h3 text-decoration-none" href="#">
              Selvedge
              <i class="fa fa-fw fa-chevron-circle-down mt-1"></i>
            </a>
          </li>
          <li class="pb-3">
            <a class="collapsed d-flex justify-content-between h3 text-decoration-none" href="#">
              Raw
              <i class="pull-right fa fa-fw fa-chevron-circle-down mt-1"></i>
            </a>
          </li>
          <li class="pb-3">
            <a class="collapsed d-flex justify-content-between h3 text-decoration-none" href="#">
              Washed
              <i class="pull-right fa fa-fw fa-chevron-circle-down mt-1"></i>
            </a>
          </li>
        </ul>
      </div>

      <div class="col-lg-9">
        <div class="row">
          <div class="col-md-4" v-for="(item, idx) in state.items" :key="idx">
            <Card :item="item"/>
          </div>
        </div>
        <div div="row">
          <ul class="pagination pagination-lg justify-content-end">
            <li class="page-item disabled">
              <a class="page-link active rounded-0 mr-3 shadow-sm border-top-0 border-left-0" href="#"
                 tabindex="-1">1</a>
            </li>
            <li class="page-item">
              <a class="page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark" href="#">2</a>
            </li>
            <li class="page-item">
              <a class="page-link rounded-0 shadow-sm border-top-0 border-left-0 text-dark" href="#">3</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Card from '@/components/Card.vue'
import axios from 'axios';
import {reactive} from "vue";

export default {
  name: "ProductList",
  components: {
    Card
  },
  setup() {
    const state = reactive({
      items: []
    })

    axios.get("http://localhost:8080/api/v1/denims/list").then(({data}) => {
      state.items = data;
    });
    return {state}
  }
}
</script>

<style scoped>

</style>