import React from "react"
import { PersonCast } from "../../tmdb_api/model/PersonCast"
import { CreditsTable, CreditsTableProps, CreditsTableStyles } from "./CreditsTable"
import { Text } from "react-native-elements"
import R from "../../res/R"
import { MovieClass } from "../../tmdb_api/model/Movie"
import { MediaClass } from "../../tmdb_api/model/Media"

interface CastTableProps extends CreditsTableProps<PersonCast> {
}

export class CastTable extends CreditsTable<PersonCast, CastTableProps> {
    constructor(props: CastTableProps) {
        super(props)
    }

    protected renderDescriptionCell(credit: PersonCast): JSX.Element {
        let styles = CreditsTableStyles
        let title = MediaClass.displayTitle(credit)
        let character = credit.character ?? ""
        let format = character.length ? R.string.person_cast_format : R.string.person_cast_format_none
        let description = format.replace("%s", title).replace("%s", character)

        return <Text style={styles.description}>{description ?? "?"}</Text>
    }
}