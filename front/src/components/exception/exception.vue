<template>
  <div class="exception">
    <div class="img">
      <slot name="image"></slot>
    </div>
    <div class="content">
      <slot name="title"></slot>
      <div class="desc">
        <slot name="desc"></slot>
      </div>
      <div class="action"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "exception"
};
</script>

<style lang="scss">
.exception {
  min-height: 500px;
  height: 80%;
  align-items: center;
  text-align: center;
  margin-top: 150px;
  .img {
    display: inline-block;
    padding-right: 52px;
    zoom: 1;
    img {
      height: 360px;
      max-width: 430px;
    }
  }
  .content {
    display: inline-block;
    flex: auto;
    h1 {
      color: #434e59;
      font-size: 72px;
      font-weight: 600;
      line-height: 72px;
      margin-bottom: 24px;
    }
    .desc {
      color: rgba(0, 0, 0, 0.45);
      font-size: 20px;
      line-height: 28px;
      margin-bottom: 16px;
    }
  }
}
</style>
