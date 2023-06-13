
<template>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <carousel :autoplay="2000" :itemsToShow="1.5" :wrapAround="true" :pauseAutoplayOnHover="true"
                    :transition="1000">
                    <slide v-for="photo in photos" :key="index">
                        <div class="carousel__item">
                            <img :src="photo.url" alt="" width="1000">
                        </div>
                    </slide>
                    <template #addons>
                        <navigation />
                        <pagination />
                    </template>
                </carousel>
            </div>
        </div>
    </div>
</template>
<script>
// If you are using PurgeCSS, make sure to whitelist the carousel CSS classes
import 'vue3-carousel/dist/carousel.css'
import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel'
import axios from 'axios';
const API_URL = "http://localhost:8080/api/v1/"
export default {
    name: 'App',
    components: {
        Carousel,
        Slide,
        Pagination,
        Navigation,
    },
    data() {
        return {
            photos: {},
        }
    },
    methods: {
        getSlides() {
            axios
                .get(API_URL + "photos", {
                })
                .then((response) => {
                    if (response.status = 200) {
                        console.log(response.data)
                        if (response.data.length != 0) {
                            this.photos = response.data;
                        }
                    }
                })
                .catch((error) => {
                    console.log(error)
                })
        }
    },
    mounted() {
        this.getSlides();
    },
}
</script>
<style lang="scss" scoped>
.carousel__slide {
    padding: 5px;
}

.carousel__viewport {
    perspective: 2000px;
}

.carousel__track {
    transform-style: preserve-3d;
}

.carousel__slide--sliding {
    transition: 1s;
}

.carousel__slide {
    opacity: 0.9;
    transform: rotateY(-20deg) scale(0.9);
}

.carousel__slide--active~.carousel__slide {
    transform: rotateY(20deg) scale(0.9);
}

.carousel__slide--prev {
    opacity: .5;
    transform: rotateY(-10deg) scale(0.95);
}

.carousel__slide--next {
    opacity: .5;
    z-index: -1;
    transform: rotateY(10deg) scale(0.95);
}

.carousel__slide--active {
    opacity: 1;
    transform: rotateY(0) scale(1.1);
}
</style>