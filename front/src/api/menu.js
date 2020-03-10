import request from "@/server/request";

export function selectMenu(data) {
  return request.post("/menu/select", data);
}

export function updateMenu(data) {
  return request.post("/menu/update", data);
}

export function addMenu(data) {
  return request.post("/menu/add", data);
}

export function deleteMenu(data) {
  return request.delete("/menu/delete", data);
}

export function selectRole(data) {
  return request.post("/role/select", data);
}

export function getMenu(data) {
  return request.post("/role/getMenu", data);
}

export function updateRole(data) {
  return request.postJson("/role/update", data);
}

export function addRole(data) {
  return request.postJson("/role/add", data);
}

export function deleteRole(data) {
  return request.delete("/role/delete", data);
}

export function selectDept() {
  return request.post("/dept/select", "");
}

export function selectByDept(data) {
  return request.post("/dept/selectByDept", data);
}

export function addDept(data) {
  return request.post("/dept/add", data);
}

export function updateDept(data) {
  return request.post("/dept/update", data);
}

export function deleteDept(data) {
  return request.delete("/dept/delete", data);
}

export function selectUser(data) {
  return request.post("/user/select", data);
}

export function deleteUser(data) {
  return request.delete("/user/delete", data);
}

export function updateUser(data) {
  return request.post("/user/update", data);
}

export function addUser(data) {
  return request.post("/user/add", data);
}

