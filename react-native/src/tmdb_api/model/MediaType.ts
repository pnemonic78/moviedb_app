export enum MediaType {
    all = "all",
    movie = "movie",
    tv = "tv",
    person = "person",
}

export function toMediaType(value: Object): MediaType {
    let index = value.valueOf()
    return Object.values(MediaType)[index as number]
}