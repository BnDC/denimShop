import ProductList from "@/pages/ProductList";
import Login from "@/pages/Login";
import {createRouter, createWebHistory} from "vue-router/dist/vue-router";
import Signup from "@/pages/Signup";

const routes = [
    {path: '/', component: ProductList},
    {path: '/login', component: Login},
    {path: '/signup', component: Signup}
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;