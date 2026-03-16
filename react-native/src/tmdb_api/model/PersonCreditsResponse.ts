import { PersonCast } from "./PersonCast";
import { PersonCrew } from "./PersonCrew";

export interface PersonCreditsResponse {
    id: number
    cast: PersonCast[]
    crew: PersonCrew[]
}