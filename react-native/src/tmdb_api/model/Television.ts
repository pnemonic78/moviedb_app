import { Genre } from "./Genre"
import { Media, MediaClass } from "./Media"

// TODO add mnissing proprties from https://developers.themoviedb.org/3/tv/get-tv-details
export interface Television extends Media {
    backdrop_path?: string
    episode_count: number
    first_air_date: Date | null
    genres: Genre[]
    name: string
    origin_country: string
    original_language: string
    original_name: string
    overview: string
    poster_path?: string
    vote_average: number
    vote_count: number
}

export class TelevisionClass extends MediaClass {
    static date(tv: Television): Date | null {
        let date = tv.first_air_date
        if (date) {
            if (typeof date === 'string' || date instanceof String) {
                if (date.length) {
                    date = new Date(date)
                    tv.first_air_date = date
                } else {
                    return null
                }
            }
            return date
        }
        return null
    }

    static displayTitle(tv: Television): string {
        return tv.name ?? tv.original_name
    }
}