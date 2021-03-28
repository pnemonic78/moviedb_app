import React, { Component } from "react"
import { ParamListBase } from "@react-navigation/native"
import { StackScreenProps } from "@react-navigation/stack"
import { Person, PersonClass } from "../../tmdb_api/model/Person"
import { PersonDetailsWidget } from "./PersonDetailsWidget"
import { ScrollView } from "react-native-gesture-handler"
import { ScreenName } from "../main/ScreenName"

interface PersonPageParams extends ParamListBase {
    person: Person
}

interface PersonPageProps extends StackScreenProps<PersonPageParams> {
    person: Person
}

export class PersonPage extends Component<PersonPageProps> {
    constructor(props: PersonPageProps) {
        super(props)
    }

    private getPerson(): Person {
        let routeParams = this.props.route.params as PersonPageParams
        return this.props.person ?? routeParams?.person
    }

    private setHeaderTitle() {
        let person = this.getPerson()
        let navigation = this.props.navigation
        navigation.setOptions({
            headerTitle: PersonClass.displayTitle(person),
        })
    }

    componentDidMount() {
        this.setHeaderTitle()
    }

    private onTapPoster(person: Person) {
        let navigation = this.props.navigation
        navigation.navigate(ScreenName.PERSON_POSTER, { person })
    }

    render() {
        let person = this.getPerson()

        let content = <PersonDetailsWidget
            person={person}
            onPosterPress={this.onTapPoster.bind(this)}
        />

        return <ScrollView>
            {content}
        </ScrollView>
    }
}