<template>
    <div
    class="w-100 d-flex justify-content-center align-items-center"
    >
    <b-table :items="items"></b-table>
    </div>
</template>
<style></style>
<script>
import userService from '../../service/User.js';
export default {
    name: 'Users',
    data() {
        return {
            items: []
        }
    },
    methods: {
        async getUsers() {
            this.items = await userService.getUsers();
        }
    },
    mounted() {
        this.getUsers();
    }
}
</script>