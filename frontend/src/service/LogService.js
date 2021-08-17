import { SERVER, TEST_SERVER } from "util/SERVER";

export const fetchAllLog = async () => {
  const { data } = await TEST_SERVER.get("/logs/all");

  return data;
}

export const fetchUnknownLog = async () => {
  const { data } = await TEST_SERVER.get("/logs/unknown");

  return data;
}