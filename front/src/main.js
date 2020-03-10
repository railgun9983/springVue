import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import axios from "axios";
import "@/server/routePromiss";
import "_u/install";
import ElementUI from "element-ui";
import localStore from "_u/localstorage";
import "./assets/scssTheme/element-variables.scss";

Vue.config.productionTip = false;
Vue.use(ElementUI);
Vue.prototype.$axios = axios;
Vue.prototype.$ELEMENT = { size: "small", zIndex: 3000 };

Vue.use({
  install(Vue) {
    Vue.prototype.$localStore = localStore;
  }
});

new Vue({
  data: {
    eventHub: new Vue()
  },
  router,
  store,
  render: h => h(App)
}).$mount("#app");
