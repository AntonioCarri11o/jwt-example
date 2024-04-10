<template>
    <div class="w-100 h-100">
        <div class="w-100 shadow p-4 d-flex">
            <h4>Normal user</h4>
            <b-button
            class="ml-auto"
            @click="logOut"
            >
                <b-icon
                icon="power"
                ></b-icon>
                Sign out
            </b-button>
        </div>
        <router-view></router-view>
    </div>
</template>
<script>
import authService from '../../service/Auth.js';
export default {
    name: 'Common',
    data() {
        return {
        }
    },
    methods: {
        logOut() {
            authService.logOut();
            this.$router.push('/login');
        }
    }
}
</script>