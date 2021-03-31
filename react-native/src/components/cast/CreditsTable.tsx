import React, { Component } from "react"
import { Pressable, StyleProp, StyleSheet, Text, TextStyle, ViewStyle } from "react-native"
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

        return <Text style={styles.year}>{year}</Text>
    }

    protected abstract renderDescriptionCell(credit: C): JSX.Element

    private renderCell(credit: C, cell: JSX.Element): JSX.Element {
        return <Pressable onPress={() => this.onTapCredit(credit)}>
            {cell}
        </Pressable>
    }

    private buildData(): any[][] {
        let credits = this.props.credits
        credits.sort((a, b) => this.compareTo(a, b));
        let data: any[][] = []

        for (var c of credits) {
            let credit = c as unknown as C
            let yearWidget = this.renderYearCell(credit)
            let descWidget = this.renderDescriptionCell(credit)
            data.push([
                this.renderCell(credit, yearWidget),
                this.renderCell(credit, descWidget)
            ])
        }

        return data
    }

    private compareTo(a: C, b: C): number {
        let t1 = this.getDate(a)?.getTime() ?? Number.MAX_VALUE
        let t2 = this.getDate(b)?.getTime() ?? Number.MAX_VALUE
        return t2 - t1
    }

    private getDate(credit: C): Date | null {
        return MediaClass.date(credit)
    }

    protected boldDescription(s: string, style: TextStyle): JSX.Element {
        let indexStart = s.indexOf("<b>")
        let indexEnd = s.indexOf("</b>")
        if ((indexStart >= 0) && (indexStart < indexEnd)) {
            let s1 = s.substring(0, indexStart)
            let s2 = s.substring(indexStart + 3, indexEnd)
            let s3 = s.substring(indexEnd + 4)

            return <Text>
                <Text style={style}>{s1}</Text>
                <Text style={{fontWeight: "bold"}}>{s2}</Text>
                <Text style={style}>{s3}</Text>
            </Text>
        }
        return <Text style={style}>{s}</Text>
    }

    private onTapCredit(credit: C) {
        this.props.onCreditPress?.(credit)
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