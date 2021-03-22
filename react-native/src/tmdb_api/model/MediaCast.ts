import { MediaCredit } from "./MediaCredit";

export interface MediaCast extends MediaCredit {
    cast_id: number
    character: string
    order: number
}