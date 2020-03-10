<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
import themeColorMixin from "_m/themeColorMixin";
export default {
  mixins: [themeColorMixin],
  created() {
    this.changeThem(this.$store.state.setting.theme);
  }
};
</script>

<style lang="scss">
#app {
  height: 100%;
}
</style>
