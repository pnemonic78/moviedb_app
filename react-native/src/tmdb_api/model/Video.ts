export class MovieVideo {
    id: string = "";
    iso_639_1: string = "";
    key: string = "";
    name: string = "";
    site: string = "";
    size: number = 0;
    type: string = "";

    public toString(): string {
        return `{id: ${this.id}, name: "${this.name}", type: ${this.type}}`;
    }
}