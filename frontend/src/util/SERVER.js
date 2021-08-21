import axios from "axios";

export const SERVER = axios.create({
  baseURL: "http://" + process.env.REACT_APP_HOST_IP,
  headers: {
    "Content-Type": "application/json",
  }
});

export const TEST_SERVER = axios.create({
  baseURL: "http://localhost:8080/api",
  headers: {
    "Content-Type": "application/json",
  }
});