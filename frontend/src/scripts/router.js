import ProductList from "@/pages/ProductList";
import Login from "@/pages/Login";
import {createRouter, createWebHistory} from "vue-router/dist/vue-router";

const routes = [
    {path: '/', component: ProductList},
    {path: '/login', component: Login}
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;