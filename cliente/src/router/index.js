import Vue from 'vue';
import VueRouter from 'vue-router';
import HomeView from '../views/HomeView.vue';
import Login from '../components/user/Login.vue';
import authService from '../service/Auth.js';
import Users from '../components/user/Users.vue';
import Admin from '../components/user/Admin.vue';
import Common from '../components/user/Common.vue';
import Principal from '../components/user/Principal.vue';
import Register from '../components/user/Register.vue';
Vue.use(VueRouter);

const routes = [
  {
    path:'/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
  },
  {
    path: '/admin',
    name: 'Admin',
    component: Admin,
    meta: {
      roles: ['ROLE_ADMIN']
    },
    children: [
      {
        path:'/users',
        name: 'Users',
        component: Users,
        meta: {
          roles: ['ROLE_ADMIN']
        }
      }
    ]
  },
  {
    path: '/common',
    name: 'Common',
    component: Common,
    meta: {
      roles: ['ROLE_COMMON']
    },
    children: [
      {
        path: '/principal',
        name: 'Principal',
        component: Principal,
        meta: {
          roles: [
            'ROLE_COMMON'
          ]
        }
      }
    ]
  }
]
const router = new VueRouter({
  routes,
  mode: 'history',
  base: import.meta.env.BASE_URL,
});
router.beforeEach((to, from, next) => {
  const publicPages = ['/login',];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = authService.returnToken();
  if (authRequired && !loggedIn) {
    return next('/login');
  }
  const role = authService.returnRole();
  if (loggedIn) {
    if (to.meta.roles && !to.meta.roles.includes(role)) {
      authService.clearAuth();
      return next('/login');
    }
  }
  if (role === 'ROLE_ADMIN' && to.matched.length === 0) {
    return next('/admin');
  }
  if (role === 'ROLE_COMMON' && to.matched.length === 0) {
    return next('/home');
  }
  next();
})
export default router;
