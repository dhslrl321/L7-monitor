import { TEST_SERVER } from "util/SERVER";

export const fetchThreatsCount = async () => {
  const { data } = await TEST_SERVER.get("/threats");

  return data;
}