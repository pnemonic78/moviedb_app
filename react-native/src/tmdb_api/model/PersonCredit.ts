import { Media } from "./Media";
import { Person } from "./Person";

export interface PersonCredit extends Media, Person {
    credit_id: number
}