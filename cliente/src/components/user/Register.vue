<template>
    <div class="w-100 h-100 d-flex justify-content-center align-items-center">
        <b-form
        class="p-4 shadow d-flex flex-column"
        @submit.prevent="submit"
        >
        <h4 class="mx-auto mb-4">Register</h4>
            <b-form-group
            label="Username"
            label-for="username"
            >
                <b-form-input
                id="username"
                name="username"
                v-model="username"
                placeholder="tony"
                type="text"
                >
                </b-form-input>
            </b-form-group>
            <b-form-group
            label="Password"
            label-for="password"
            >
                <div class="w-100 bg-gray d-flex align-items-center rounded">
                    <b-form-input
                id="password"
                name="password"
                v-model="password"
                placeholder="*****"
                :type="showPassword ? 'text' : 'password'"
                >
                </b-form-input>
                <b-icon
                :icon="showPassword ? 'eye' : 'eye-slash'"
                class="mx-2"
                style="cursor: pointer;"
                @click="showPassword = !showPassword"
                ></b-icon>
                </div>
            </b-form-group>
            <b-form-group
            label="Role"
            label-for="role"
            >
                <b-form-select
                class="form-control custom-select"
                :options="options"
                v-model="role"
                ></b-form-select>
            </b-form-group>
            <b-button
            type="submit"
            variant="primary"
            >Save</b-button>
        </b-form>
    </div>
</template>
<style>
.bg-gray {
    background-color: #C1C1C1;
}
</style>
<script>
import userService from '../../service/User.js';
import Swal from 'sweetalert2';
export default {
    name: 'Register',
    data() {
        return {
            username: '',
            password: '',
            role:'ROLE_COMMON',
            showPassword: false,
            options: [
                {
                    text: 'Admin',
                    value: 'ROLE_ADMIN'
                },
                {
                    text: 'Normal',
                    value: 'ROLE_COMMON'
                }
            ]
        }
    },
    methods: {
        submit() {
            const payload = {
                username: this.username,
                password: this.password,
                role: this.role,
            }
            this.saveUser(payload);
        },
        async saveUser(payload) {
            const response = await userService.saveUser(payload);
            if (response) {
                alert("Error")
            } else {
                Swal.fire({
                    title: 'Éxito!',
                    text: 'Usuario registrado',   
                    icon: 'success',
                    confirmButtonText: 'OK'
                }).then(() => this.$router.push('/login'));
            }
        }
    }
}
</script>
