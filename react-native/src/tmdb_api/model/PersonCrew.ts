import { PersonCredit } from "./PersonCredit";

export interface PersonCrew extends PersonCredit {
    department: string
    job: string
}