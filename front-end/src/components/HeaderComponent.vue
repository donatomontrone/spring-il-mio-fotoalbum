<template>
  <header>
    <nav class="navbar bg-body-tertiary">
      <div class="container-fluid">
        <router-link to="/" class="navbar-brand">
          <img src="albums.svg" alt="Albums logo" width="150">
        </router-link>
        <div class="d-flex" role="search">
          <a class="btn btn-outline-dark me-2" href="http://localhost:8080/"><i class="fa-solid fa-globe"></i> Album
            Community</a>
          <router-link to="/contact" class="btn btn-outline-dark" href="http://localhost:8080/admin/photos"><i
              class="fa-solid fa-circle-info"></i> Ti serve aiuto? </router-link>
        </div>
      </div>
    </nav>
  </header>
</template>
<script>
export default {
  data() {
    return {
    }
  },
  methods: {
  },
}
</script>
<style lang="scss" scoped></style>