<template>
  <div :class="{ 'el-menu--horizontal': menuModel === 'horizontal' }">
    <template v-for="(menu, key) in menuData">
      <template v-if="menu.meta.isShow === '1'">
          <el-submenu
                  :key="`${key}`"
                  :index="menu.path"
                  v-if="menu.children && menu.children.length > 0"
          >
            <template slot="title">
              <i :class="menu.icon"></i>
              <span slot="title"> {{ menu.name }}</span>
            </template>
            <menu-item :menuData="menu.children" :keyParent="key"></menu-item>
          </el-submenu>
          <el-menu-item
                  :key="`${keyParent < 0 ? key : keyParent + '-' + menu.order}`"
                  :index="menu.path"
                  :route="menu.path"
                  v-else
          >
            <i :class="menu.icon"></i>
            <span slot="title"> {{ menu.name }}</span>
          </el-menu-item>
        </template>
    </template>
  </div>
</template>

<script>
import menuItem from "./menuItem";
export default {
  name: "menuItem",
  components: { menuItem },
  props: {
    menuData: {
      type: Array,
      default() {
        return [];
      }
    },
    keyParent: {
      type: Number,
      default: -1
    },
    menuModel: {
      type: String,
      default: "horizontal"
    }
  }
};
</script>

<style lang="scss" scoped>
.el-menu--horizontal > .el-menu-item.is-active {
  border-bottom: none !important;
}
.el-menu--horizontal > .el-menu-item {
  transition: none !important;
}
</style>
