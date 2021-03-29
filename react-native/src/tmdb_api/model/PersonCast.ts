import { PersonCredit } from "./PersonCredit";

export interface PersonCast extends PersonCredit {
    cast_id: number
    character: string
    order?: number
}