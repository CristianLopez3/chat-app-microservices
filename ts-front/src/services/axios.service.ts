import axios, { AxiosInstance } from "axios";

const BASE_URL = "http://localhost:8089/chat";

export const axiosInstance = axios.create({
  baseURL: BASE_URL,
})