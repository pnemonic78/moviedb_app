export enum Gender {
    unknown = "unknown",
    female = "female",
    male = "male",
}

export function toGender(value: Object): Gender {
    let index = value.valueOf()
    return Object.values(Gender)[index as number]
}