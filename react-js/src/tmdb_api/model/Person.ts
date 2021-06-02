import { PersonCreditsResponse } from "../PersonCreditsResponse"
import { Gender } from "./Gender"
import { Media } from "./Media"
import { PersonExternalIds } from "./PersonExternalIds"

export interface Person extends Media {
    also_known_as: string[]
    biography: string
    birthday?: string // Date
    combined_credits: PersonCreditsResponse
    place_of_birth?: string
    deathday?: string // Date
    external_ids?: PersonExternalIds
    gender: Gender
    homepage?: string
    imdb_id?: string
    known_for_department: string
    name: string
    original_name: string
    profile_path: string
}

export class PersonClass {
    static date(person: Person): Date | null {
        let s = person.birthday
        if (s?.length) {
            return new Date(s)
        }
        return null
    }

    static displayTitle(person: Person): string {
        return person.name ?? person.original_name
    }
}