import ProductList from "@/pages/ProductList";
import Login from "@/pages/Login";
import {createRouter, createWebHistory} from "vue-router/dist/vue-router";
import Signup from "@/pages/Signup";
import Cart from "@/pages/Cart";
import Orders from "@/pages/Orders";
import Order from "@/pages/Order";

const routes = [
    {path: '/', component: ProductList},
    {path: '/login', component: Login},
    {path: '/signup', component: Signup},
    {path: '/cart', component: Cart},
    {path: '/order', component: Order},
    {path: '/orders', component: Orders},
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;