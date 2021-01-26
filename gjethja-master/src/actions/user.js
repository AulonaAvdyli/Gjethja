import * as constants from "../utils/constants";
import axios from 'axios';

const url = process.env.REACT_APP_BACKEND_URL;
const feedbackUrl = process.env.REACT_APP_FEEDBACK_URL;

// are called by the user
// whenever it is needed to activate reducer
// in order to affect state

export const logOut = data => ({
    type: constants.LOGOUT,
    data: data
})

export const getUser = data => {
    const token = localStorage.getItem("token");
    return function(dispatch) {
        return axios({
            method: 'get',
            url: url + "current",
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

export const providerRegister = data => {
    return function(dispatch) {
        return axios({
            method: 'post',
            url: url + "providers/register",
            data: data
        }).then(res => {
            console.log(res);
            dispatch({ type: constants.REGISTER,  data: res.data });
            return res;
        }).catch(err => {
            return err;
        })
    }

}

export const seekerRegister = data => {
    return function(dispatch) {
        return axios({
            method: 'post',
            url: url + "seekers/register",
            data: data
        }).then(res => {
            console.log(res);
            dispatch({ type: constants.REGISTER,  data: res.data });
            return res;
        }).catch(err => {
            return err;
        })
    }

}

export const login = data => {
    return function(dispatch) {
        return axios({
            method: 'post',
            url: url + "login",
            data: data,
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            console.log(res);
            dispatch({ type: constants.LOGIN,  data: res.data });
            return res;
        }).catch(err => {
            return err;
        })
    }

}

export const confirm = data => {
    return function(dispatch) {
        return axios({
            method: 'get',
            url: url + "confirm-account?token=" + data,
        }).then(res => {
            console.log(res);
            dispatch({ type: constants.CONFIRMED,  data: res });
            return res;
        }).catch(err => {
            return err;
        })
    }
}

export const editProfile = data => {
    const token = localStorage.getItem("token");
    return function(dispatch) {
        return axios({
            method: 'put',
            url: url + "edit-profile",
            data: data,
            headers: {
                Authorization: "Bearer " + token
            }
        }).then(res => {
            console.log(res);
            dispatch({ type: constants.GET_PROFILES,  data: res.data });
            return res;
        }).catch(err => {
            return err;
        })
    }
}

export const saveFeedback = data => {
    const token = localStorage.getItem("token");
    return function(dispatch) {
        return axios({
            method: 'post',
            url: feedbackUrl + "feedback",
            data: {
                description: data
            },
            headers: {
                "Content-Type": "application/json",
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

export const getFeedback = data => {
    const token = localStorage.getItem("token");
    return function(dispatch) {
        return axios({
            method: 'get',
            url: feedbackUrl + "feedback",
            data: {
                description: data
            },
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

export const uploadProfilePicture = data => {
    const token = localStorage.getItem("token");
    const formData = new FormData();
    formData.append("profile-picture", data);
    return function(dispatch) {
        return axios({
            method: 'post',
            url: url + "profile-picture",
            data: formData,
            headers: {
                authorization: "Bearer " + token,
                'Content-Type': 'multipart/form-data'
            }
        }).then(res => {
            console.log(res);
            return res;
        }).catch(err => {
            return err;
        })
    }

}