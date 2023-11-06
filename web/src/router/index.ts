import { createRouter, createWebHistory } from "vue-router";
import AppRoot from "../AppRoot.vue";
import About from "../pages/About.vue";
import Forecasts from "../pages/Forecasts.vue";


export const ROUTES = {
    APP_ROOT:{
        path: "/",
        name: "",
        component: AppRoot,
        children: [
            {
                path: '/',
                name: 'Forecasts',
                component: Forecasts,
            },
            {
                path: '/about',
                name: 'About',
                component: About,
            }
        ]
    }
}

const router = createRouter({
    history: createWebHistory(),
    routes: Object.values(ROUTES),
});


export default router;
