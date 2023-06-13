<template>
    <div class="col-12 col-sm-6 col-md-4 col-lg-3">
        <div class="card border-0 image-container">
            <img :src="photo.url" :alt="photo.title" class="rounded-1 img-fluid">
            <div class="overlay text-white p-2">
                <h4>{{ photo.title }}</h4>
                <p>{{ photo.description }}</p>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    props: {
        photo: {
            type: Object,
        }
    }
}
</script>
<style lang="scss" scoped>
.image-container {
    position: relative;
}

.overlay {
    position: absolute;
    bottom: 0;
    left: 50;
    width: 100%;
    height: 100%;
    background: linear-gradient(to bottom, rgba(0, 0, 0, 0.9) 0%, rgba(0, 0, 0, -0.3) 100%);
    opacity: 0;
    transition: opacity 1s;
}

.image-container:hover .overlay {
    opacity: 1;
}

.image-container img {
    display: block;
    width: 100%;
    min-height: 250px;
}

/* Aggiungi altri stili personalizzati come margini, dimensioni, ecc. */
</style>