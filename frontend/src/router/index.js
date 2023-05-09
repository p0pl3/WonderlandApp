import {createWebHistory, createRouter} from "vue-router";
import HomeView from "@/views/HomeView";
import ProductsCatalog from "@/views/ProductsCatalog";
import confirmOrder from "@/views/NewOrder";
import ProductsCart from "@/views/ProductsCart";
import ProductView from "@/views/ProductView";
import ProfileView from "@/views/ProfileView";
import LoginView from "@/views/LoginView";
import SignUpView from "@/views/SignUpView";
import FeedView from "@/views/FeedView.vue";
import FeedDetailView from "@/views/FeedDetailView.vue";
import OrderDetail from "@/views/OrderDetail";
import AdminPage from "@/views/AdminPage";


const routes = [
    {
        path: "/",
        name: "HomeView",
        component: HomeView,
    },
    {
        path: "/catalog/:id?",
        name: "ProductsCatalog",
        component: ProductsCatalog,
    },
    {
        path: "/confirm-order",
        name: "confirmOrder",
        component: confirmOrder,
    },
    {
        path: "/cart",
        name: "ProductsCart",
        component: ProductsCart,
    },
    {
        path: "/product/:id",
        name: "ProductView",
        component: ProductView,
    },
    {
        path: "/feed/:id?",
        name: "FeedView",
        component: FeedView,
    },
    {
        path: "/feed-detail/:id",
        name: "FeedDetailView",
        component: FeedDetailView,
    },
    {
        path: "/profile",
        name: "ProfileView",
        component: ProfileView,
    },
    {
        path: "/login",
        name: "LoginView",
        component: LoginView,
    },
    {
        path: "/signup",
        name: "SignUpView",
        component: SignUpView,
    },
    {
        path: "/order/:id",
        name: "OrderDetail",
        component: OrderDetail,
    },
    {
        path: "/admin",
        name: "AdminPage",
        component: AdminPage,
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;