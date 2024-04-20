import Vue from 'vue';
import VueRouter from 'vue-router';
import HomeView from '../views/HomeView.vue';
import Login from '../components/Login.vue';
import authService from '../service/Auth.js';
import Users from '../components/user/Users.vue';
import Admin from '../components/user/Admin.vue';
import Common from '../components/user/Common.vue';
import Principal from '../components/user/Principal.vue';
import Register from '../components/user/Register.vue';
import Profile from '../views/Profile.vue';
import { authGuard } from '../auth/authGuard';
Vue.use(VueRouter);

const router = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    
    {
      path: '/profile',
      name: 'Profile',
      component: Profile,
      beforeEnter: authGuard
    }
  ]
});
export default router;
