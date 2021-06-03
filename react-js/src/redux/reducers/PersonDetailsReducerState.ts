import { Person } from "../../tmdb_api/model/Person";

export default interface PersonDetailsReducerState {
    loading: boolean
    people: Map<number, Person>
}
