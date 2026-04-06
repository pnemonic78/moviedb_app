import { createSlice } from '@reduxjs/toolkit';
import PersonDetailsAction from "./PersonDetailsAction";
import PersonDetailsState from "./PersonDetailsState";

const initialState: PersonDetailsState = {
    loading: false,
    people: [],
}

export const personSlice = createSlice({
    name: 'person',
    initialState,
    reducers: {
        details: (state: PersonDetailsState, action: PersonDetailsAction) => {
            state.loading = false
            let person = action.payload
            if (person != null) {
                let people = state.people ?? []
                let index = people.findIndex((p) => p.id == person.id)
                if (index >= 0) {
                    people[index] = person
                } else {
                    people.concat(person)
                }
                state.people = people
            }
        }
    }
})

export const { details } = personSlice.actions

export default personSlice.reducer