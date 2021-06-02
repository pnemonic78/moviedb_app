import { MediaCast } from "./model/MediaCast";
import { MediaCrew } from "./model/MediaCrew";

export interface CreditsResponse {
    id: number
    cast: MediaCast[]
    crew: MediaCrew[]
}