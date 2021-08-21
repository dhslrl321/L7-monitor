import { TEST_SERVER, SERVER } from "util/SERVER";


export const fetchSummary = async () => {
  const { data } = await SERVER.get("/api/traffics/summaries");
  return data;
}

export const fetchRealtimeTrafficsFiveMinutes = async () => {
  const { data } = await SERVER.get("/api/traffics/five_minute");
  return data;
}

export const fetchRealtimeTrafficsDays = async () => {
  const { data } = await SERVER.get("/api/traffics/day");
  return data;
}

export const fetchRealtimeTrafficsWeeks = async () => {
  const { data } = await SERVER.get("/api/traffics/week");
  return data;
}
