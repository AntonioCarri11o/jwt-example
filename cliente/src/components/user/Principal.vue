<template>
    <div class="w-100 d-flex justify-content-center">
        <div class="principal row">
            <div v-for="hero in heroes"class="col-lg-4 col-md-6 col-sm-12">
                <div class="w-100 position-relative">
                    <img :src="hero.url_image" width="100%">
                    <div class="info w-100">
                        <h4>
                            {{ hero.name }}
                        </h4>
                        <p>Habilities: {{ hero.descrition }}</p>                      
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<style>
.principal {
    width: 80%;
}
.info {
    background-color: rgba(113, 113, 113, 0.714);
    position: absolute;
    bottom: 0;
    color: #fff
}
</style>
<script>
import heroService from '../../service/Hero.js';
export default {
    name: 'Principal',
    mounted() {
        this.getHeroes();
    },
    data() {
        return {
            heroes: []
        }
    },
    methods: {
        async getHeroes() {
            this.heroes = await heroService.getHeroes();
            console.log(this.heroes);
        }
    }
}
</script>