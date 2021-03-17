import { MediaType } from "./MediaType";

export class Media {
 
    adult: boolean = false;
    id: number = 0;
    media_type: MediaType = 0;
    popularity: number = 0;

// factory Media.fromJsonType(Map < String, dynamic > json) {
//     if (json == null) return null;

//     final mediaType = media_type ?? MediaType.all;

//     switch (mediaType) {
//         case MediaType.movie:
//             return Movie.fromJson(json);
//         case MediaType.person:
//             return Person.fromJson(json);
//         case MediaType.tv:
//             return Television.fromJson(json);
//         default:
//             return Media.fromJson(json);
//     }
// }

    date(): Date | null {
        return null;
    }

    displayTitle(): string {
        return "";
    }
}