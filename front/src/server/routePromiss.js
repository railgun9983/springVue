/* eslint-disable no-unused-vars */
import router from "../router";
import localStore from "_u/localstorage";
import request from "./request";
import EmptyPageView from "_v/common/EmptyPageView";
import ViewHome from "_v/common/ViewHome";
import store from "../store";

const whiteList = ["/login"]; // 白名单

let asyncRouter; // 新增路由

// 导航守卫，渲染动态路由
router.beforeEach((to, from, next) => {
  if (whiteList.indexOf(to.path) !== -1) {
    next();
  } else {
    let token = localStore.get("USER_TOKEN");
    let user = localStore.get("USER");
    let userRouter = get("USER_ROUTER");
    if (token.length && user) {
      if (!asyncRouter) {
        if (!userRouter) {
          request
            .get(`menu/${user.userId}`)
            .then(res => {
              asyncRouter = res.data.routers;
              save("USER_ROUTER", asyncRouter);
              store.commit("account/setPermissions", res.data.permissions);
              go(to, next);
            })
            .catch(err => {
              console.error(err);
            });
        } else {
          asyncRouter = userRouter;
          go(to, next);
        }
      } else {
        next();
      }
    } else {
      next("/login");
    }
  }
});

function go(to, next) {
  asyncRouter = filterAsyncRouter(asyncRouter);
  router.addRoutes(asyncRouter);
  next({ ...to, replace: true });
}

function save(name, data) {
  localStorage.setItem(name, JSON.stringify(data));
}

function get(name) {
  if (typeof localStorage.getItem(name) == "undefined") {
    return null;
  } else {
    return JSON.parse(localStorage.getItem(name));
  }
}

function filterAsyncRouter(routes) {
  return routes.filter(route => {
    let component = route.component;
    if (component) {
      switch (route.component) {
        case "ViewMain":
          route.component = ViewHome;
          break;
        case "EmptyPageView":
          route.component = EmptyPageView;
          break;
        // case "HomePageView":
        //   route.component = HomePageView;
        //   break;
        default:
          route.component = view(component);
      }
      if (route.children && route.children.length) {
        route.children = filterAsyncRouter(route.children);
      }
      return true;
    }
  });
}

function view(path) {
  return function(resolve) {
    import(`@/views/${path}.vue`).then(mod => {
      resolve(mod);
    });
  };
}
