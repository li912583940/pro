import Qs from 'qs';
import axios from 'axios';
import {MessageBox} from 'element-ui';

// 创建axios实例
const service = axios.create({
    baseURL: 'http://127.0.0.1', // api的base_url
    timeout: 5000,                // 请求超时时间
    withCredentials: true,   //加了这段就可以跨域了
    transformRequest: [function (data) {
        data = Qs.stringify(data);
        return data
    }],
});

// http request 拦截器
service.interceptors.request.use(
    config => {
    	//debugger
      	config.headers['Accept'] = 'text/plain';
        config.headers['Content-Type'] = 'application/x-www-form-urlencoded';
        
        //对于/auth/**的请求路径，默认不添加token认证
//      if (config.url.indexOf("/auth") <0 && store.state.token) {
//          config.headers.Authorization = `Bearer ${store.state.token}`;
//      }
	if (config.method == 'post') {
        config.data = {
            ...config.data
        }
    } else if (config.method == 'get') {
        config.params = {
            ...config.params
        }
    }
        return config;
    },
    err => {
        return Promise.reject(err);
    }
);

// http response 拦截器
service.interceptors.response.use(
    response => {
    	//console.log(response);
        return response;
    },
    error => {
        if (error.response) {
            switch (error.response.status) {
                case 401:            /**token未授权或token授权失败，过期等等**/
                    // 401 清除token信息并跳转到登录页面
                    store.commit(types.LOGOUT);
                    break;
                case 404:            /**未找到页面**/
                  // 404 跳转到404页面
                  
                  break;
                case 406:
                	MessageBox({
	                    message: '提交参数不合法，无法通过效验请求',
	                    type: 'error',
	                    duration: 5 * 1000
                    });
                    break;
            }
        }
        // console.log(JSON.stringify(error));//console : Error: Request failed with status code 402
        //console.log('error  :'+error);
        return Promise.reject(error)
    }
);

export default service;
