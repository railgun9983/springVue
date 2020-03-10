import Vue from "vue";
import Vuex from "vuex";
import setting from "./modules/setting";
import account from "./modules/account";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    setting,
    account
  }
});
