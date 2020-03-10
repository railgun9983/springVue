import axios from "axios";
import router from "@/router";
import moment from "moment";
import store from "../store";
import localStore from "_u/localstorage";
moment.locale("zh-cn");

// 统一配置
let REQUEST = axios.create({
  baseURL: "http://127.0.0.1:8081/",
  responseType: "json",
  validateStatus(status) {
    return status === 200;
  }
});

// 拦截请求
REQUEST.interceptors.request.use(
  config => {
    let token = store.state.account.token;
    if (config.url.url !== "/user/login" && !token) {
      router.push("/login");
    } else {
      config.headers.Authorization = "Bearer " + token;
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

// 拦截响应
REQUEST.interceptors.response.use(
  config => {
    if (config.data && config.data.code === 400) {
      localStore.clear();
      router.push("/login");
    }
    return config.data;
  },
  error => {
    if (error.response) {
      switch (error.response.status) {
        case 400:
          localStore.clear();
          break;
      }
    }
    return Promise.resolve(error);
  }
);

const request = {
  post(url, params) {
    return REQUEST.post(url, params, {
      transformRequest: [
        params => {
          let result = "";
          Object.keys(params).forEach(key => {
            if (
              !Object.is(params[key], undefined) &&
              !Object.is(params[key], null)
            ) {
              result +=
                encodeURIComponent(key) +
                "=" +
                encodeURIComponent(params[key]) +
                "&";
            }
          });
          return result;
        }
      ],
      headers: {
        "Content-Type": "application/x-www-form-urlencoded"
      }
    });
  },
  put(url, params) {
    return REQUEST.put(url, params, {
      transformRequest: [
        params => {
          let result = "";
          Object.keys(params).forEach(key => {
            if (
              !Object.is(params[key], undefined) &&
              !Object.is(params[key], null)
            ) {
              result +=
                encodeURIComponent(key) +
                "=" +
                encodeURIComponent(params[key]) +
                "&";
            }
          });
          return result;
        }
      ],
      headers: {
        "Content-Type": "application/x-www-form-urlencoded"
      }
    });
  },
  get(url, params) {
    let _params;
    if (Object.is(params, undefined)) {
      _params = "";
    } else {
      _params = "?";
      Object.keys(params).map(item => {
        _params += `${item}=${params[item]}&`;
      });
    }
    return REQUEST.get(`${url}${_params}`);
  },
  delete(url, params) {
    let _params;
    if (Object.is(params, undefined)) {
      _params = "";
    } else {
      _params = "?";
      Object.keys(params).map(item => {
        _params += `${item}=${params[item]}&`;
      });
    }
    return REQUEST.delete(`${url}${_params}`);
  },
  postJson(url, obj) {
    return REQUEST.post(url, obj, {
      headers: {
        "Content-Type": "application/json;charset=utf-8"
      }
    });
  }
};

export default request;
