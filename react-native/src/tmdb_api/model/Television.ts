import { Media } from "./Media";

export class Television extends Media {
    episode_count: number = 0;
    first_air_date: Date | null = null;

    date(): Date | null {
        return this.first_air_date;
    }
}