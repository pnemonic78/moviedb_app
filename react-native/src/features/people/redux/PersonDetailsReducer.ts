import { Person } from '@/tmdb_api/model/Person';
import { createSlice } from '@reduxjs/toolkit';
import PersonDetailsAction from "./PersonDetailsAction";
import PersonDetailsState from "./PersonDetailsState";

const initialState: PersonDetailsState = {
    loading: false,
    people: new Map(),
}

export const personSlice = createSlice({
    name: 'person',
    initialState,
    reducers: {
        details: (state: PersonDetailsState, action: PersonDetailsAction) => {
            state.loading = false
            let person = action.payload
            if (person != null) {
                let people = state.people ?? new Map<number, Person>()
                people.set(person.id, person)
                state.people = people
            }
        }
    }
})

export const { details } = personSlice.actions

export default personSlice.reducer