import * as constants from "../utils/constants";
import axios from 'axios';

const url = process.env.REACT_APP_BACKEND_URL;
const paymentServiceUrl = process.env.REACT_APP_PAYMENT_URL;

export const getPosts = filters => {
    const token = localStorage.getItem("token");
    return function(dispatch) {
        return axios({
            method: 'get',
            url: url + `posts`,
            headers: {
                authorization: "Bearer " + token
            }
        }).then(res => {
            console.log(res);
            dispatch({ type: constants.GET_POSTS,  data: res.data });
            return res;
        }).catch(err => {
            return err;
        })
    }
}

export const getBoostedPosts = () => {
    const token = localStorage.getItem("token");
    return function(dispatch) {
        return axios({
            method: 'get',
            url: paymentServiceUrl + `posts/boosted`,
            headers: {
                authorization: "Bearer " + token
            }
        }).then(res => {
            console.log(res);
            dispatch({ type: constants.GET_BOOSTED_POSTS,  data: res.data });
            return res;
        }).catch(err => {
            return err;
        })
    }
}

export const getMyPosts = filters => {
    const token = localStorage.getItem("token");
    return function(dispatch) {
        return axios({
            method: 'get',
            url: url + `posts/current-user`,
            headers: {
                authorization: "Bearer " + token
            }
        }).then(res => {
            console.log(res);
            dispatch({ type: constants.GET_POSTS,  data: res.data });
            return res;
        }).catch(err => {
            return err;
        })
    }
}

export const createPost = data => {
    const token = localStorage.getItem("token");
    return function(dispatch) {
        return axios({
            method: 'post',
            url: url + "posts",
            data: data,
            headers: {
                authorization: "Bearer " + token
            }
        }).then(res => {
            console.log(res);
            dispatch({ type: constants.CREATE_POST,  data: res.data });
            return res;
        }).catch(err => {
            return err;
        })
    }
}

export const editPost = (data, id) => {
    const token = localStorage.getItem("token");
    return function(dispatch) {
        return axios({
            method: 'put',
            url: url + "posts/" + id,
            data: data,
            headers: {
                authorization: "Bearer " + token
            }
        }).then(res => {
            console.log(res);
            return res;
        }).catch(err => {
            return err;
        })
    }
}

export const deletePost = ( data ) => {
    const token = localStorage.getItem("token");
    return function(dispatch) {
        return axios({
            method: 'delete',
            url: url + "posts/" + data,
            headers: {
                authorization: "Bearer " + token
            }
        }).then(res => {
            console.log(res);
            dispatch({ type: constants.DELETE_POST,  data: res.data });
            return res;
        }).catch(err => {
            return err;
        })
    }
}