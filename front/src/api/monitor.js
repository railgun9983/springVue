import request from "@/server/request";

export function selectLog(data) {
  return request.post("/log/select", data);
}
