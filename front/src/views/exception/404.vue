<template>
  <exception>
    <img
      slot="image"
      src="https://gw.alipayobjects.com/zos/rmsportal/KpnpchXsobRgLElEozzI.svg"
      alt=""
    />
    <h1 slot="title">404</h1>
    <p slot="desc">抱歉，你访问的页面不存在或仍在开发中</p>
  </exception>
</template>

<script>
import Exception from "_c/exception/exception";
export default {
  name: "404",
  components: { Exception }
};
</script>

<style></style>
