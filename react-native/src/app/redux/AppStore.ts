import { configureStore } from "@reduxjs/toolkit"
import { appReducers } from "./AppReducer"

export const store = configureStore({
    reducer: appReducers
})
