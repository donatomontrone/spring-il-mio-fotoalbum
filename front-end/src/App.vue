<script>
import HeaderComponent from './components/HeaderComponent.vue';

export default {
  components: {
    HeaderComponent
  },
  data() {
    return {
      searchText: '',
    }
  },
  methods: {
    search(searchText) {
      this.searchText = searchText;
    }
  },
}
</script>

<template>
  <HeaderComponent @search="search()" />
  <router-view :searchText="searchText"></router-view>
</template>

<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200;0,300;0,400;0,500;0,600;0,700;1,200;1,300;1,400;1,500;1,600;1,700&display=swap');

body {
  font-family: 'Nunito', sans-serif;
}
</style>
