import { Person } from "../../tmdb_api/model/Person"

export declare type OnPersonPress = null | ((person: Person) => void)

export declare type OnPersonPressObject = { onPersonPress: OnPersonPress }
