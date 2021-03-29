import React, { Component } from "react"
import { ParamListBase } from "@react-navigation/native"
import { StackScreenProps } from "@react-navigation/stack"
import { Person, PersonClass } from "../../tmdb_api/model/Person"
import { PersonDetailsWidget } from "./PersonDetailsWidget"
import { ScrollView } from "react-native-gesture-handler"
import { ScreenName } from "../main/ScreenName"
import { connect, DispatchProp } from "react-redux"
import { MoviesAction } from "../../redux/actions/MoviesAction"
import { AppReducersState } from "../../redux/reducers/AppReducer"
import TMDBApi from "../../tmdb_api/TMDBApi"
import TMDBApiImpl from "../../tmdb_api/TMDBApiImpl"
import { fetchedPersonDetails } from "../../redux/actions/PersonDetailsAction"
import { SocialIcon, SocialMediaType } from "react-native-elements"
import { Linking } from "react-native"

interface PersonPageParams extends ParamListBase {
    person: Person
}

interface PersonPageProps extends StackScreenProps<PersonPageParams>, DispatchProp<MoviesAction> {
    person?: Person
    people: Map<number, Person>
}

export class PersonPageComponent extends Component<PersonPageProps> {
    constructor(props: PersonPageProps) {
        super(props)

        let person = this.getPerson()
        this.fetchPersonDetails(person)
    }

    private api: TMDBApi = new TMDBApiImpl()

    private getPerson(): Person {
        let routeParams = this.props.route.params as PersonPageParams
        let personProp = this.props.person ?? routeParams?.person
        let personId = personProp?.id ?? 0
        let person = this.props.people.get(personId) ?? (personProp as Person)
        return person
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

    private async fetchPersonDetails(person: Person) {
        let dispatch = this.props.dispatch;
        let people = this.props.people
        if (!people.has(person.id)) {
            this.api.getPerson(person)
                .then(data => dispatch(fetchedPersonDetails(data)))
        }
    }

    private onTapPoster(person: Person) {
        let navigation = this.props.navigation
        navigation.navigate(ScreenName.PERSON_POSTER, { person })
    }

    private onTapSocial(person: Person, url: string) {
        Linking.openURL(url)
    }

    render() {
        let person = this.getPerson()

        let content = <PersonDetailsWidget
            person={person}
            onPosterPress={this.onTapPoster.bind(this)}
            onSocialPress={this.onTapSocial.bind(this)}
        />

        return <ScrollView>
            {content}
        </ScrollView>
    }
}

function mapStateToProps(state: AppReducersState): object {
    const stateReducer = state.personDetailsReducer;
    return {
        people: stateReducer.people,
    }
}

export const PersonPage = connect(mapStateToProps)(PersonPageComponent)
