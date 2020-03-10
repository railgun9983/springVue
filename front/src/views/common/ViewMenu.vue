<template>
  <el-menu
    :default-active="activeIndex"
    :mode="menuModel"
    class="view-menu-detail-wrapper"
    @select="handleSelect"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b"
    :collapse="isCollapse"
    :router="true"
  >
    <menu-item :menuData="menuData" :menuModel="menuModel"></menu-item>
  </el-menu>
</template>

<script>
import MenuItem from "_c/menu/menuItem";

export default {
  name: "ViewMenu",
  components: { MenuItem },
  data() {
    return {
      activeIndex: "1",
      menuData: []
    };
  },
  props: {
    menuModel: {
      type: String,
      default: "horizontal"
    }
  },
  computed: {
    isCollapse() {
      return this.$store.state.setting.isCollapse;
    }
  },
  mounted() {
    let arr = JSON.parse(localStorage.getItem("USER_ROUTER"));
    if (arr && arr.length > 0) {
      this.menuData = arr[0].children;
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    filterMenu(nodes) {
      if (!(nodes && nodes.length)) {
        return [];
      }
      let newChildren = [];
      for (let node of nodes) {
        if (node.type && node.type === "0") {
          // 如果节点符合条件，直接加入新的节点集
          newChildren.push(node);
          node.children = this.filterMenu(node.children);
        } else {
          // 如果当前节点不符合条件，递归过滤子节点，
          // 把符合条件的子节点提升上来，并入新节点集
          newChildren.push(...this.filterMenu(node.children));
        }
      }
      return newChildren;
    }
  }
};
</script>

<style lang="scss">
.view-menu-detail-wrapper {
  height: 100%;
  overflow: hidden;
}
</style>
