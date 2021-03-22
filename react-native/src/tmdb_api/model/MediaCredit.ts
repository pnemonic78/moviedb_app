import { Media } from "./Media";
import { Person } from "./Person";

export interface MediaCredit extends Person {
    credit_id: string
    media: Media
}