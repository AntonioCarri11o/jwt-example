<template>
    <section class="w-100 h-100 d-flex justify-content-center align-items-center">
        <b-form
        @submit.prevent="login"
        class="shadow p-4 d-flex flex-column"
        >
            <h4 class="mb-4 mx-auto" >Iniciar sesión</h4>

            <b-form-group
            label="Username"
            label-for="email"            
            >
                <b-form-input
                id="email"
                name="email"
                v-model="email"
                placeholder="tony"
                type="text"
                >                
                </b-form-input>
            </b-form-group>
            <b-form-group
            label="Password"
            label-for="pass"            
            >
            <div class="d-flex align-items-center rounded"  style="background-color: #C1C1C1;">
                <b-form-input
                id="pass"
                name="pass"
                v-model="password"
                placeholder="*******"
                :type="showPass ? 'text' : 'password'"
                >                
                </b-form-input>
                <b-icon
                :icon="showPass ? 'eye' : 'eye-slash'"
                class="mx-2"
                style="cursor: pointer;"
                @click="showPass = !showPass"
                ></b-icon>
            </div>
            </b-form-group>
            <b-button
            type="submit"
            variant="primary"
            >Log in </b-button>
        </b-form>
    </section>
</template>
<script>
import authService from '../../service/Auth.js';
export default {
    name: 'Login',
    data() {
        return {
            email: '',
            password: '',
            showPass: false,
        }
    },
    methods: {
        login() {
            const payload = {
                username: this.email,
                password: this.password
            }
            this.send(payload);
        },
        async send(payload) {
            await authService.generateToken(payload).then(() => {
                const role = authService.returnRole();
                if (role === 'ROLE_ADMIN') {
                    this.$router.push('/users');
                } else if (role === 'ROLE_COMMON') {
                    this.$router.push('/principal');
                }
            });
        }
    }
}
</script>