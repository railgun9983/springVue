<template>
  <view-main>
    <keep-alive v-if="true">
      <router-view />
    </keep-alive>
    <router-view v-else />
  </view-main>
</template>

<script>
import ViewMain from "./ViewMain";
export default {
  name: "ViewHome",
  components: { ViewMain }
};
</script>

<style></style>
