import { configureStore } from "@reduxjs/toolkit"
import { appReducers } from "../reducers/AppReducer"

const store = configureStore({
    reducer: appReducers
})

export default store