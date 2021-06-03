import { Reducer } from 'redux'
import { Person } from '../../tmdb_api/model/Person'
import { MoviesActionType } from '../actions/ActionTypes'
import { PersonDetailsAction } from '../actions/PersonDetailsAction'
import PersonDetailsReducerState from './PersonDetailsReducerState'

const initialState: PersonDetailsReducerState = {
    loading: false,
    people: new Map(),
}

const defaultAction: PersonDetailsAction = {
    type: MoviesActionType.None,
    payload: null,
}

export const personDetailsReducer: Reducer<PersonDetailsReducerState, PersonDetailsAction> = (
    state: PersonDetailsReducerState = initialState,
    action: PersonDetailsAction = defaultAction
): PersonDetailsReducerState => {
    switch (action.type) {
        case MoviesActionType.MovieError:
            return state
        case MoviesActionType.PersonResponse:
            let person = action.payload
            if (person != null) {
                let people = new Map<number, Person>(state.people)
                people.set(person.id, person)
                return { ...state, people: people }
            }
            return state
        default:
            return state
    }
}
