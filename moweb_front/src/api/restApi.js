import axios from "axios";

//const baseURL = "https://i7a507.p.ssafy.io/moweb-api";
const baseURL = "http://localhost:8080/moweb-api";

const restApi = {
  instance: axios.create({
    baseURL,
    headers: {
      "Content-Type": "application/json",
    },
  }),
  async create(user_name) {
    const response = await this.instance.post(
      "/room/create",
      JSON.stringify({
        user_name,
      })
    );
    return response.data;
  },
  async join(user_name, url) {
    const response = await this.instance.post(
      "/room/join",
      JSON.stringify({
        user_name,
        url,
      })
    );

    return response.data;
  },
};

export default restApi;
