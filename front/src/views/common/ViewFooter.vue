<template>
  <div class="views-footer">
    <div class="copyright">
      Copyright
      <span v-html="copyright"></span>
    </div>
  </div>
</template>

<script>
export default {
  name: "ViewFooter",
  props: ["copyright"]
};
</script>

<style lang="scss" scoped>
.views-footer {
  padding: 0 16px;
  margin: 24px 0;
  text-align: center;
  .copyright {
    color: rgba(0, 0, 0, 0.45);
    font-size: 14px;
  }
}
</style>
