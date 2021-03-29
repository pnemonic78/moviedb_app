import { Person } from "../../tmdb_api/model/Person"

export declare type OnPersonPress = null | ((person: Person) => void)

export declare type OnPersonPressObject = { onPersonPress: OnPersonPress }

export declare type OnPersonSocialPress = null | ((person: Person, url: string) => void)
