import { TEST_SERVER } from "util/SERVER";

export const fetchRealtimeTrafficsFiveMinutes = async () => {
  const { data } = await TEST_SERVER.get("/traffics/five_minutes");
  return data;
}

export const fetchRealtimeTrafficsDays = async () => {
  const { data } = await TEST_SERVER.get("/traffics/five_minutes");
  return data;
}

export const fetchRealtimeTrafficsWeeks = async () => {
  const { data } = await TEST_SERVER.get("/traffics/five_minutes");
  return data;
}