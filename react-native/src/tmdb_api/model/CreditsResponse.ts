import { MediaCast } from "./MediaCast";
import { MediaCrew } from "./MediaCrew";

export interface CreditsResponse {
    id: number
    cast: MediaCast[]
    crew: MediaCrew[]
}