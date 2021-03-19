export class Genre {
    id: number = 0;
    name: string = "";

    constructor(id: number, name: string) {
        this.id = id;
        this.name = name;
    }

    toString(): string {
        return this.name;
    }
}