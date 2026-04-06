import { Person } from "@/tmdb_api/model/Person";

export default interface PersonDetailsState {
    loading: boolean
    people: Array<Person>
}
