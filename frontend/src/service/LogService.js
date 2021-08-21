import { SERVER, TEST_SERVER } from "util/SERVER";

export const fetchAllLog = async () => {
  const { data } = await SERVER.get("/api/logs/all");

  return data;
}

export const fetchUnknownLog = async () => {
  const { data } = await SERVER.get("/api/logs/unknown");

  return data;
}