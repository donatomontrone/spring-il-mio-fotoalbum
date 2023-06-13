<template>
    <div class="container-fluid">
        <div class="row g-4">
            <div class="col-12">
                <h1 class="text-center mb-5 fw-bold">Fatti incantare da questi splendidi scatti</h1>
            </div>
            <div class="col-12 row align-items-center">
                <h class="col-md-6 text-end m-0"> Cerchi qualche foto in particolare?</h>
                <div class="col-md-4">
                    <input type="text" class="form-control" id="search" v-model="title" @keyup="getPhotos">
                </div>
            </div>
            <div v-if="isEmpty" class="col-12 text-center">
                <img src="https://www.beevidhya.com/assets/images/no_result.gif" alt="Logo" class="img-fluid">
                <h5>{{ message }}</h5>
            </div>
            <SinglePhoto v-else v-for="(photo, index) in photos" :photo="photo" :key="index" />
        </div>
    </div>
</template>
<script>
import SinglePhoto from './SinglePhoto.vue';
import axios from "axios";
const API_URL = "http://localhost:8080/api/v1/"
export default {
    components: {
        SinglePhoto
    },
    data() {
        return {
            photos: [],
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
                            this.message = "Nessuna pizzeria trovata."
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
<style lang="scss" scoped></style>