import { MediaCast } from "../../tmdb_api/model/MediaCast"
import { MediaCrew } from "../../tmdb_api/model/MediaCrew"
import { PersonCast } from "../../tmdb_api/model/PersonCast"
import { PersonCredit } from "../../tmdb_api/model/PersonCredit"
import { PersonCrew } from "../../tmdb_api/model/PersonCrew"

export declare type OnCastPress = null | ((cast: MediaCast) => void)

export declare type OnCrewPress = null | ((crew: MediaCrew) => void)

export declare type OnCreditPress<C extends PersonCredit> = null | ((credit: C) => void)

export declare type OnPersonCastPress = OnCreditPress<PersonCast>

export declare type OnPersonCrewPress = OnCreditPress<PersonCrew>
