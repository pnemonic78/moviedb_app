import { PersonCast } from "./model/PersonCast";
import { PersonCrew } from "./model/PersonCrew";

export interface PersonCreditsResponse {
    id: number
    cast: PersonCast[]
    crew: PersonCrew[]
}