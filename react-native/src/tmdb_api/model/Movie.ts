import { Utils } from "../../components/main/Utils"
import { Media, MediaClass } from "./Media"

export interface Movie extends Media {
    backdrop_path: string
    genre_ids: number[]
    origin_country: string
    original_language: string
    original_title: string
    overview: string
    poster_path: string
    release_date?: Date
    title: string
    video: boolean
    vote_average: number
    vote_count: number
}

export class MovieClass extends MediaClass {
    static date(movie: Movie): Date | null {
        let date = movie.release_date
        if (date) {
            if (Utils.isString(date)) {
                let s = date as unknown as string
                if (s.length) {
                    date = new Date(s)
                    movie.release_date = date
                } else {
                    return null
                }
            }
            return date
        }
        return null
    }

    static displayTitle(movie: Movie): string {
        return movie.title ?? movie.original_title
    }
}