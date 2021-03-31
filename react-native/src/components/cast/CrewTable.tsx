import React from "react"
import { PersonCrew } from "../../tmdb_api/model/PersonCrew"
import { CreditsTable, CreditsTableProps, CreditsTableStyles } from "./CreditsTable"
import { Text } from "react-native-elements"
import { MediaClass } from "../../tmdb_api/model/Media"
import R from "../../res/R"

interface CrewTableProps extends CreditsTableProps<PersonCrew> {
}

export class CrewTable extends CreditsTable<PersonCrew, CrewTableProps> {
    constructor(props: CrewTableProps) {
        super(props)
    }

    protected renderDescriptionCell(credit: PersonCrew): JSX.Element {
        let styles = CreditsTableStyles
        let title = MediaClass.displayTitle(credit)
        let job = credit.job ?? ""
        let format = job.length ? R.string.person_cast_format : R.string.person_cast_format_none
        let description = format.replace("%s", title).replace("%s", job)

        return <Text style={styles.description}>{description ?? "?"}</Text>

    }
}