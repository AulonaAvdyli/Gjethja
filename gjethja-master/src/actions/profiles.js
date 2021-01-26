import * as constants from "../utils/constants";
import axios from 'axios';

const url = process.env.REACT_APP_BACKEND_URL;

export const getProfiles = filters => {
    const token = localStorage.getItem("token");

    let path = "";
    if (filters) {
        const {startAgeFilter, endAgeFilter, cityFilter, educationFilter} = filters;
        if (startAgeFilter) {
            path = `/filter?startAge=${startAgeFilter}`;
        }
        if (endAgeFilter) {
            path += `&endAge=${endAgeFilter}`;
        }
        if (cityFilter) {
            path += `&city=${cityFilter}`;
        }
        if (educationFilter) {
            path += `&education=${educationFilter}`;
        }
    }

    return function (dispatch) {
        return axios({
            method: 'get',
            url: url + `providers` + path,
            headers: {
                authorization: "Bearer " + token,
                contentType: "application/json; charset=utf-8",
            }
        }).then(res => {
            console.log(res);
            dispatch({type: constants.GET_PROFILES, data: res.data});
            return res;
        }).catch(err => {
            return err;
        })
    }
}