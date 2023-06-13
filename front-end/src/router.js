import { createRouter, createWebHistory } from 'vue-router';
import HomePage from "./pages/HomePage.vue";
import ContactForm from "./pages/ContactForm.vue";
const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'home',
            component: HomePage
        },
        {
            path: '/contact',
            name: 'contact',
            component: ContactForm
        },
        {
            path: '/pizzas/create',
            name: 'create',
            component: ''
        }
    ]
});
export { router }