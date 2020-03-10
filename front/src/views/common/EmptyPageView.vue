<template>
  <keep-alive v-if="true">
    <router-view ref="page" />
  </keep-alive>
  <router-view ref="page" v-else />
</template>
<script>
import ViewMain from "./ViewMain";
export default {
  name: "EmptyPageView",
  components: { ViewMain }
};
</script>
