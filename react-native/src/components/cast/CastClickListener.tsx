import { MediaCast } from "../../tmdb_api/model/MediaCast"

export declare type OnCastPress = null | ((cast: MediaCast) => void)

export declare type OnCastPressObject = { onCastPress: OnCastPress }