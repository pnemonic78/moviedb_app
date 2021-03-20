import { Media, MediaClass } from "./Media";

export interface Television extends Media {
    episode_count: number;
    first_air_date: Date | null;
}

export class TelevisionClass extends MediaClass {
    static date(tv: Television): Date | null {
        return tv.first_air_date;
    }

    static displayTitle(tv: Television): string {
        return "";
    }
}