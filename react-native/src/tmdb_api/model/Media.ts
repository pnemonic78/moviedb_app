import { MediaType } from "./MediaType"
import { Movie, MovieClass } from "./Movie"
import { Person, PersonClass } from "./Person"
import { Television, TelevisionClass } from "./Television"

export interface Media {
 
    adult: boolean
    id: number
    media_type: MediaType
    popularity: number

// factory Media.fromJsonType(Map < String, dynamic > json) {
//     if (json == null) return null

//     let mediaType = media_type ?? MediaType.all

//     switch (mediaType) {
//         case MediaType.movie:
//             return Movie.fromJson(json)
//         case MediaType.person:
//             return Person.fromJson(json)
//         case MediaType.tv:
//             return Television.fromJson(json)
//         default:
//             return Media.fromJson(json)
//     }
// }
}

export abstract class MediaClass {
    static date(media: Media): Date | null {
        let mediaType = media.media_type
        switch (mediaType) {
            case MediaType.movie:
                return MovieClass.date(media as Movie)
            case MediaType.person:
                return PersonClass.date(media as Person)
            case MediaType.tv:
                return TelevisionClass.date(media as Television)
            default:
                return null
        }
    }

    static displayTitle(media: Media): string {
        let mediaType = media.media_type
        switch (mediaType) {
            case MediaType.movie:
                return MovieClass.displayTitle(media as Movie)
            case MediaType.person:
                return PersonClass.displayTitle(media as Person)
            case MediaType.tv:
                return TelevisionClass.displayTitle(media as Television)
            default:
                return ""
        }
    }
}