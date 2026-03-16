import { MediaCredit } from "./MediaCredit";

export interface MediaCrew extends MediaCredit {
    department: string
    job: string
}