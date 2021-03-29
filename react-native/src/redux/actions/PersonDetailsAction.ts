import { Action } from "redux";
import { Person } from "../../tmdb_api/model/Person";
import { MoviesActionType } from "./ActionTypes";

export interface PersonDetailsAction extends Action<MoviesActionType> {
    payload: Person | null,
}

export function fetchedPersonDetails(person: Person): PersonDetailsAction {
    return {
        type: MoviesActionType.PersonResponse,
        payload: person,
    }
}
