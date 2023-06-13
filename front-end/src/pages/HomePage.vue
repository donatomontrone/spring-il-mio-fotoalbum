<template>
    <main>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-8">
                    <h1 class="text-center mb-5 fw-bold title">Fatti <span style="color:#5A2FC7">incantare</span> da questi
                        <span style="color:#F84FCB">splendidi</span> scatti!</h1>
                </div>
                <div class="col-12">
                    <AppSlider />
                </div>
            </div>
        </div>
        <section>
            <div class="container mt-4">
                <div class="row justify-content-center">
                    <div class="col-12 row align-items-center pb-5">
                        <h1 class="col-md-6 text-end m-0"> Cerchi qualche <span class="fw-semibold"
                                style="color:#4B5485;">foto</span> in particolare?</h1>
                        <div class="col-md-4">
                            <input type="text" class="form-control" id="search" v-model="title" @keyup="getPhotos">
                        </div>
                    </div>
                </div>
            </div>
            <div v-if="isEmpty" class="col-12 text-center">
                <img src="https://www.beevidhya.com/assets/images/no_result.gif" alt="Logo" class="img-fluid">
                <h5>{{ message }}</h5>
            </div>
            <PhotoContainer v-else :photos="photos" />
        </section>
    </main>
</template>
<script>
import AppSlider from '../components/AppSlider.vue';
import PhotoContainer from '../components/PhotoContainer.vue';
import axios from "axios";
const API_URL = "http://localhost:8080/api/v1/"
export default {
    components: { PhotoContainer, AppSlider },
    data() {
        return {
            photos: {},
            title: '',
            isEmpty: true,
            message: '',
        }
    },
    methods: {
        getPhotos() {
            axios
                .get(API_URL + "photos", {
                    params: {
                        title: this.title
                    }
                })
                .then((response) => {
                    if (response.status = 200) {
                        console.log(response.data)
                        if (response.data.length === 0) {
                            this.message = "Nessuna foto trovata."
                            this.isEmpty = true
                        } else {
                            this.isEmpty = false;
                            this.photos = response.data;
                            this.message = "";
                        }
                    }
                })
                .catch((error) => {
                    console.log(error)
                })
        }
    },
    mounted() {
        this.getPhotos();
    },
}
</script>
<style lang="scss">
h1.title {
    font-size: 5rem;
}
</style>