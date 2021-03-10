import { Gender } from "./Gender";
import { Media } from "./Media";
import { PersonExternalIds } from "./PersonExternalIds";

export class Person extends Media {
    also_known_as: string[] = [];
    biography: string = "";
    birthday: Date | null = null;
    //@JsonKey(name: 'combined_credits')
    //PersonCreditsResponse credits;
    place_of_birth: string = "";
    deathday: Date | null = null;
    external_ids: PersonExternalIds | null = null;
    gender: Gender  = Gender.unknown;
    homepage: string = "";
    imdb_id: string = "";
    known_for_department: string = "";
    name: string = "";
    original_name: string = "";
    profile_path: string = "";

    public toString(): string {
        return `{id: ${this.id}, name: "${this.name}"}`;
    }

    date(): Date | null {
        return this.birthday;
    }

    getTitle(): string | null {
        return this.name ?? this.original_name;
    }
}