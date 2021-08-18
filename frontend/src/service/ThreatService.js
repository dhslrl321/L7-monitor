import { TEST_SERVER, SERVER } from "util/SERVER";

export const fetchThreatsCount = async () => {
  const { data } = await SERVER.get("/threats");

  return data;
}