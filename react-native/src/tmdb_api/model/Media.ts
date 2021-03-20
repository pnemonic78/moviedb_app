import { MediaType } from "./MediaType"

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

    // date(): Date | null

    // displayTitle(): string
}

export abstract class MediaClass {
    static date(media: Media): Date | null {
        return null
    }

    static displayTitle(media: Media): string {
        return ""
    }
}