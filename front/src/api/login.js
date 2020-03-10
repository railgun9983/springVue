import request from "@/server/request";

export function login(data) {
  return request.post("/user/login", data);
}

export function getUser(data) {
  return request.get("/user/selectOne", data);
}

export function getMenu(data) {
  return request.post(`/menu/${data}`, "");
}

export function updatePwd(data) {
  return request.post("/user/updatePwd", data);
}

export function updateUser(data) {
  return request.post("/user/updateUser", data);
}

export function selectLoginLog(data) {
  return request.post("/loginLog/selectLog", data);
}
