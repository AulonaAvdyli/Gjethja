import axios from "axios";
import * as constants from "../utils/constants";

const url = process.env.REACT_APP_PAYMENT_URL;

export const makePayment = (id, data) => {
    const token = localStorage.getItem("token");
    return function(dispatch) {
        return axios({
            method: 'post',
            url: url + `posts/${id}/boost`,
            data: data,
            headers: {
                authorization: "Bearer " + token,
                "Content-Type": "application/json"
            }
        }).then(res => {
            console.log(res);
            return res;
        }).catch(err => {
            return err;
        })
    }
}