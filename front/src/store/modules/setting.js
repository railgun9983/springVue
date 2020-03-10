/*配置系统的设置*/
import localStore from "_u/localstorage";

export default {
  namespaced: true,
  state: {
    sidebar: localStore.get("SIDEBAR", true), // true: 上下布局， false: 左右布局
    theme: localStore.get("THEME", "#409EFF"), // 主题 dark light
    systemName: "NOVEL后台管理系统", // 系统名称
    copyright: "",
    browserType: 0, // 浏览器类型
    isCollapse: false // 菜单展开
  },
  mutations: {
    setSidebar(state, val) {
      localStore.save("SIDEBAR", val);
      state.sidebar = val;
    },
    setTheme(state, value) {
      state.theme = value;
    },
    setBrowserType(state, val) {
      state.browserType = val;
    },
    setCollapse(state, val) {
      state.isCollapse = val;
    }
  }
};
