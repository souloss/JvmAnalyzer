"use strict";

import Vue from 'vue';
import axios from "axios";

// Full config:  https://github.com/axios/axios#request-config
// axios.defaults.baseURL = process.env.baseURL || process.env.apiUrl || '';
// axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

let config = {
  // baseURL: process.env.baseURL || process.env.apiUrl || "127.0.0.1:8089",
  baseURL: 'http://localhost:8089',
  timeout: 60 * 1000, // Timeout
  withCredentials: true, // Check cross-site Access-Control
};

const _axios = axios.create(config);

_axios.interceptors.request.use(
  config=>{
    // Do something before request is sent
    return config;
  },
  error=>{
    // Do something with request error
    console.log(error) // for debug
    return Promise.reject(error);
  }
);

// Add a response interceptor
_axios.interceptors.response.use(
  response=>{
    // Do something with response data
    return response;
  },
  error=>{
    console.log(error) // for debug
    return Promise.reject(error);
  }
);


//插件安装,将axios实例放在Vue和windows中
Plugin.install = function(Vue, options) {
  Vue.axios = _axios;
  window.axios = _axios;

  Object.defineProperties(Vue.prototype, {
    axios: {
      get() {
        return _axios;
      }
    },
    $axios: {
      get() {
        return _axios;
      }
    },
  });
  
};

Vue.use(Plugin)

export default Plugin;
