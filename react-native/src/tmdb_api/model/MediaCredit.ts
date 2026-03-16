import { Media } from "./Media";
import { Person } from "./Person";

export interface MediaCredit extends Media, Person {
    credit_id: number
}