import { API_BASE } from './constants';
import axios from 'axios';

export default class Utils {

  static baseUrl = API_BASE[process.env.REACT_APP_ENVIRONMENT];


  static fetchData(method = 'GET', endpoint = '', data, isMultiPart = false, suppliedHeaders) {
    let body = (method === 'POST' || method === 'PATCH') ? data : undefined; // Only POST/PATCH can have a body.

    let headers = suppliedHeaders;
    if (!headers) {
      headers = {
        Accept: 'application/json, text/javascript, */*',
        'Content-Type': 'application/json',
      };
    }

    if (isMultiPart) {
      body = data;
      delete headers['Content-Type'];
    }

    const options = {
      method,
      body,
      credentials: 'include',
      headers: new Headers(headers),
    };

    const axiosOptions = {
      method:method,
      data:body,
      url:endpoint,
      headers: headers,
    };

    if (method === 'GET') {
      delete options.body;
    }

    return axios(axiosOptions)
      .then((response) => {
        // if (!response.ok) {
        //   throw response;
        // }
        // return response.json();
        console.log("axios.response");
        console.log(response);
        return response;  
        

      }).catch(error => ({ 
        // console.log(error);
        error 
      }));


  }

}
