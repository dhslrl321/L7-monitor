import axios from "axios";

export const SERVER = axios.create({
  baseURL: "http://backend:8080/api",
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