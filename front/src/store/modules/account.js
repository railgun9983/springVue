import localStore from "_u/localstorage";

export default {
  namespaced: true,
  state: {
    token: localStore.get("USER_TOKEN"),
    expireTime: localStore.get("EXPIRE_TIME"),
    user: localStore.get("USER"),
    permissions: localStore.get("PERMISSIONS"),
    roles: localStore.get("ROLES")
  },
  mutations: {
    setToken(state, val) {
      localStore.save("USER_TOKEN", val);
      state.token = val;
    },
    setExpireTime(state, val) {
      localStore.save("EXPIRE_TIME", val);
      state.expireTime = val;
    },
    setUser(state, val) {
      localStore.save("USER", val);
      state.user = val;
    },
    setPermissions(state, val) {
      localStore.save("PERMISSIONS", val);
      state.permissions = val;
    },
    setRoles(state, val) {
      localStore.save("ROLES", val);
      state.roles = val;
    }
  }
};
