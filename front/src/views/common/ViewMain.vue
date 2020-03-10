<template>
  <div class="view-main-wrapper">
    <el-container>
      <el-header>
        <view-header></view-header>
      </el-header>
      <el-container>
        <el-aside :width="sidebar ? '0' : '180px'" style="border-top: 1px solid #DCDFE6;">
          <view-menu :menuModel="'vertical'"></view-menu>
        </el-aside>
        <el-main>
          <div style="height: 100%;width: 100%; font-size: 14px;">
            <slot></slot>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import ViewHeader from "./ViewHeader";
import ViewMenu from "./ViewMenu";
export default {
  name: "ViewMain",
  components: { ViewMenu, ViewHeader },
  computed: {
    sidebar() {
      return this.$store.state.setting.sidebar;
    }
  }
};
</script>

<style lang="scss">
.view-main-wrapper {
  width: 100%;
  height: 100%;
  & > .el-container {
    height: 100%;
    & > .el-container {
      height: calc(100% - 60px);
    }
    .el-main {
      padding: 0 !important;
    }
  }
  .el-header {
    padding: 0;
  }
}
</style>
