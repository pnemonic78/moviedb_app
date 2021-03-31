import { PersonCreditsResponse } from "../PersonCreditsResponse"
import { Gender } from "./Gender"
import { Media, MediaClass } from "./Media"
import { PersonExternalIds } from "./PersonExternalIds"

export interface Person extends Media {
    also_known_as: string[]
    biography: string
    birthday?: Date
    combined_credits: PersonCreditsResponse
    place_of_birth?: string
    deathday?: Date
    external_ids?: PersonExternalIds
    gender: Gender
    homepage?: string
    imdb_id?: string
    known_for_department: string
    name: string
    original_name: string
    profile_path: string
}

export class PersonClass extends MediaClass {
    static date(person: Person): Date | null {
        let date = person.birthday
        if (date) {
            if (typeof date === 'string' || date instanceof String) {
                if (date.length) {
                    date = new Date(date)
                    person.birthday = date
                } else {
                    return null
                }
            }
            return date
        }
        return null
    }

    static displayTitle(person: Person): string {
        return person.name ?? person.original_name
    }
}