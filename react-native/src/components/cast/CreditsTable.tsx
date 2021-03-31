import React, { Component } from "react"
import { Pressable, StyleProp, StyleSheet, Text, ViewStyle } from "react-native"
import { Card } from "react-native-elements"
import { Rows, Table } from "react-native-table-component"
import { MediaClass } from "../../tmdb_api/model/Media"
import { PersonCredit } from "../../tmdb_api/model/PersonCredit"
import { OnCreditPress } from "./CastClickListener"

export interface CreditsTableProps<C extends PersonCredit> {
    credits: C[]
    onCreditPress?: OnCreditPress<C>
    style?: StyleProp<ViewStyle>
}

export abstract class CreditsTable<C extends PersonCredit, P extends CreditsTableProps<C>> extends Component<P> {
    constructor(props: P) {
        super(props)
    }

    render() {
        let table = this.renderTable()

        return <Card containerStyle={ this.props.style }>
            {table}
        </Card>
    }

    renderTable(): JSX.Element {
        let styles = CreditsTableStyles
        let tableData = this.buildData()

        return <Table borderStyle={styles.tableBorder}>
            <Rows data={tableData} flexArr={[1, 5]} />
        </Table>
    }

    protected renderYearCell(credit: C): JSX.Element {
        let styles = CreditsTableStyles

        let date = MediaClass.date(credit)
        let year = date?.getFullYear()?.toString() ?? "—"

        return <Pressable onPress={() => console.log("1")}>
            <Text style={styles.year}>{year}</Text>
        </Pressable>
    }

    protected abstract renderDescriptionCell(credit: C): JSX.Element

    private buildData(): any[][] {
        let credits = this.props.credits
        credits.sort((a, b) => this.compareTo(a, b));
        let data: any[][] = []

        for (var c of credits) {
            let credit = c as unknown as C
            let yearWidget = this.renderYearCell(credit)
            let descWidget = this.renderDescriptionCell(credit)
            data.push([yearWidget, descWidget])
        }

        return data
    }

    private compareTo(a: C, b: C): number {
        let d1 = this.getDate(a)
        let d2 = this.getDate(b)
        if (d1 === d2) return 0
        if (d1 == null) return -1
        if (d2 == null) return -1
        return d2.getTime() - d1.getTime()
    }

    private getDate(credit: C): Date | null {
        return MediaClass.date(credit)
    }
}

export const CreditsTableStyles = StyleSheet.create({
    description: {
        padding: 8,
    },
    year: {
        padding: 8,
    },
    table: {
    },
    tableBorder: {
        borderWidth: 0,
    },
})