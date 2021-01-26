import * as constants from "../utils/constants";


export const setFilter = (filter, value) => ({
    type: constants.SET_FILTERS,
    payload: { filter, value }
})