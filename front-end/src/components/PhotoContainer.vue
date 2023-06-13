<template>
    <div class="container-fluid">
        <div class="row g-4 justify-content-center">
            <SinglePhoto v-for="(photo, index) in photos" :photo="photo" :key="index" />
        </div>
    </div>
</template>
<script>
import SinglePhoto from './SinglePhoto.vue';
export default {
    components: {
        SinglePhoto
    },
    props: {
        photos: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            message: ''
        }
    },
    methods: {
    },
}
</script>
<style lang="scss" scoped></style>