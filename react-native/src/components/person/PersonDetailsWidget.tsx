import React, { Component } from "react"
import { GestureResponderEvent, Pressable, StyleSheet, Text, View } from "react-native"
import { Icon } from "react-native-elements"
import R from "../../res/R"
import { Gender, toGender } from "../../tmdb_api/model/Gender"
import { Person, PersonClass } from "../../tmdb_api/model/Person"
import TMDBApi from "../../tmdb_api/TMDBApi"
import { CastTable } from "../cast/CastTable"
import { CrewTable } from "../cast/CrewTable"
import { LoadingIcon } from "../LoadingIcon"
import { Utils } from "../main/Utils"
import { OnPersonPress, OnPersonSocialPress } from "./PersonClickListener"

const posterDetailsWidth = R.dimen.personDetailsWidth
const posterDetailsHeight = R.dimen.personDetailsHeight

const _summaryLinesMin = 5
const _summaryLinesMax = 1000

const iconColor = R.color.iconColor

interface PersonDetailsWidgetProps {
    person: Person
    onPosterPress?: OnPersonPress
    onSocialPress?: OnPersonSocialPress
}

interface PersonDetailsWidgetState {
    summaryLinesExpanded: boolean
}

export class PersonDetailsWidget extends Component<PersonDetailsWidgetProps, PersonDetailsWidgetState> {
    constructor(props: PersonDetailsWidgetProps) {
        super(props)
        this.state = {
            summaryLinesExpanded: false,
        }
    }

    private onTapPoster(_event: GestureResponderEvent) {
        let person = this.props.person
        this.props.onPosterPress?.(person)
    }

    private onTapHomepage() {
        let person = this.props.person
        let url = person.homepage ?? ""
        this.props.onSocialPress?.(person, url)
    }

    private onTapFacebook() {
        let person = this.props.person
        let url = TMDBApi.generateFacebookUrl(person.external_ids?.facebook_id)
        this.props.onSocialPress?.(person, url)
    }

    private onTapImdb() {
        let person = this.props.person
        let url = TMDBApi.generateImdbUrl(person.imdb_id ?? person.external_ids?.imdb_id)
        this.props.onSocialPress?.(person, url)
    }

    private onTapInstagram() {
        let person = this.props.person
        let url = TMDBApi.generateInstagramUrl(person.external_ids?.instagram_id)
        this.props.onSocialPress?.(person, url)
    }

    private onTapTwitter() {
        let person = this.props.person
        let url = TMDBApi.generateTwitterUrl(person.external_ids?.twitter_id)
        this.props.onSocialPress?.(person, url)
    }

    render() {
        let person = this.props.person
        let styles = styleSheet
        let personIconSize = R.dimen.personIconSize / 1.2

        let gone = <View />

        let nameValue = PersonClass.displayTitle(person)
        let nameWidget = <Text style={styles.name}>{nameValue}</Text>

        let imageWidth = posterDetailsWidth
        let imageHeight = posterDetailsHeight
        let posterUrl = TMDBApi.generatePosterUrl(
            person.profile_path,
            imageWidth,
            imageHeight
        )
        let poster = <LoadingIcon
            placeholder={R.icon.face}
            source={{ uri: posterUrl }}
            style={styles.poster} />
        let posterWidget = <Pressable onPress={this.onTapPoster.bind(this)}>
            {poster}
        </Pressable>

        let personalInfoLabel = <Text style={styles.group}>{R.string.personal_info_label}</Text>

        let knownForLabel = <Text style={styles.label}>{R.string.known_for_label}</Text>

        let knownForValue = <Text style={styles.text}>{person.known_for_department}</Text>

        let gender = toGender(person.gender)
        var genderText = ""
        switch (gender) {
            case Gender.female:
                genderText = R.string.gender_female
                break
            case Gender.male:
                genderText = R.string.gender_male
                break
            default:
                break
        }
        let hasGender = (genderText?.length)
        let genderLabel = hasGender ? <Text style={styles.label}>{R.string.gender_label}</Text> : gone
        let genderValue = hasGender ? <Text style={styles.text}>{genderText}</Text> : gone

        let hasBirthday = (person.birthday != null)
        let birthdayLabel = hasBirthday ? <Text style={styles.label}>{R.string.birthday_label}</Text> : gone
        let birthdayValue = hasBirthday ? <Text style={styles.text}>{Utils.formatDate(person.birthday!)}</Text> : gone

        let hasBirthplace = (person.place_of_birth?.length)
        let birthplaceLabel = hasBirthplace ? <Text style={styles.label}>{R.string.place_of_birth_label}</Text> : gone
        let birthplaceValue = hasBirthplace ? <Text style={styles.text}>{person.place_of_birth}</Text> : gone

        let hasDeathday = (person.deathday != null)
        let deathdayLabel = hasDeathday ? <Text style={styles.label}>{R.string.deathday_label}</Text> : gone
        let deathdayValue = hasDeathday ? <Text style={styles.text}>{Utils.formatDate(person.deathday!)}</Text> : gone

        let hasAliases = (person.also_known_as?.length)
        let aliasesLabel = hasAliases ? <Text style={styles.label}>{R.string.also_known_as_label}</Text> : gone
        let aliasesValue = hasAliases ? <Text style={styles.text}>{person.also_known_as.join(", ")}</Text> : gone

        let summaryLinesExpanded = this.state.summaryLinesExpanded
        let summaryLabel = <Text style={styles.label}>{R.string.summary_label}</Text>
        let summaryWidget = <Pressable onPress={() => this.setState({ summaryLinesExpanded: !summaryLinesExpanded })}>
            <Text
                style={styles.text}
                numberOfLines={summaryLinesExpanded ? _summaryLinesMax : _summaryLinesMin}>
                {person.biography ?? ""}
            </Text>
        </Pressable>

        let hasHomepage = (person.homepage?.length)
        let homepageWidget = hasHomepage ? <Icon
            raised={true}
            reverse={true}
            name={R.icon.homepage.name}
            type={R.icon.homepage.type}
            color={iconColor}
            size={personIconSize}
            containerStyle={styles.icon}
            onPress={this.onTapHomepage.bind(this)}
        /> : gone

        let hasFacebook = (person.external_ids?.facebook_id?.length)
        let facebookWidget = hasFacebook ? <Icon
            raised={true}
            reverse={true}
            name={R.icon.facebook.name}
            type={R.icon.facebook.type}
            color={iconColor}
            size={personIconSize}
            containerStyle={styles.icon}
            onPress={this.onTapFacebook.bind(this)}
        /> : gone

        let hasImdb = (person.imdb_id?.length) || (person.external_ids?.imdb_id?.length)
        let imdbWidget = hasImdb ? <Icon
            raised={true}
            reverse={true}
            name={R.icon.imdb.name}
            type={R.icon.imdb.type}
            color={iconColor}
            size={personIconSize}
            containerStyle={styles.icon}
            onPress={this.onTapImdb.bind(this)}
        /> : gone

        let hasInstagram = (person.external_ids?.instagram_id?.length)
        let instagramWidget = hasInstagram ? <Icon
            raised={true}
            reverse={true}
            name={R.icon.instagram.name}
            type={R.icon.instagram.type}
            color={iconColor}
            size={personIconSize}
            containerStyle={styles.icon}
            onPress={this.onTapInstagram.bind(this)}
        /> : gone

        let hasTwitter = (person.external_ids?.twitter_id?.length)
        let twitterWidget = hasTwitter ? <Icon
            raised={true}
            reverse={true}
            name={R.icon.twitter.name}
            type={R.icon.twitter.type}
            color={iconColor}
            size={personIconSize}
            containerStyle={styles.icon}
            onPress={this.onTapTwitter.bind(this)}
        /> : gone

        let hasCast = (person.combined_credits?.cast?.length)
        let hasCrew = (person.combined_credits?.crew?.length)
        let hasCredits = hasCast || hasCrew
        let creditsLabel = hasCredits ? <Text style={styles.group}>{R.string.known_credits_label}</Text> : gone

        let castLabel = hasCast ? <Text style={styles.label}>{R.string.cast_label}</Text> : gone
        let castWidget = hasCast ? <CastTable
            credits={person.combined_credits.cast!}
            style={styles.table}
        /> : gone

        let crewLabel = hasCrew ? <Text style={styles.label}>{R.string.person_crew_label}</Text> : gone
        let crewWidget = hasCrew ? <CrewTable
            credits={person.combined_credits.crew}
            style={styles.table}
        /> : gone

        return <View style={styles.details}>
            {nameWidget}
            <View style={{ flexDirection: "row" }}>
                <View>
                    {posterWidget}
                    <View style={{ flexDirection: "row", flexWrap: "wrap", width: imageWidth, }}>
                        {homepageWidget}
                        {facebookWidget}
                        {imdbWidget}
                        {instagramWidget}
                        {twitterWidget}
                    </View>
                </View>
                <View style={{ flex: 1, flexDirection: "column", }}>
                    {personalInfoLabel}
                    {knownForLabel}
                    {knownForValue}
                    {genderLabel}
                    {genderValue}
                    {birthdayLabel}
                    {birthdayValue}
                    {birthplaceLabel}
                    {birthplaceValue}
                    {deathdayLabel}
                    {deathdayValue}
                    {aliasesLabel}
                    {aliasesValue}
                </View>
            </View>
            {summaryLabel}
            {summaryWidget}
            {creditsLabel}
            {castLabel}
            {castWidget}
            {crewLabel}
            {crewWidget}
        </View>
    }
}

const styleSheet = StyleSheet.create({
    details: {
        flexDirection: "column",
        padding: 8,
    },
    group: {
        flexWrap: "wrap",
        fontSize: 24,
        fontWeight: "bold",
        paddingBottom: 4,
    },
    icon: {
        marginBottom: 8,
        marginEnd: 4,
        marginStart: 0,
        marginTop: 8,
    },
    label: {
        flexWrap: "wrap",
        fontSize: 18,
        fontWeight: "bold",
        paddingBottom: 4,
    },
    name: {
        fontSize: 32,
        paddingBottom: 8,
    },
    poster: {
        borderRadius: R.dimen.cardRadius,
        marginEnd: 8,
        height: posterDetailsHeight,
        width: posterDetailsWidth,
    },
    table: {
        marginBottom: 14,
    },
    text: {
        flexWrap: "wrap",
        fontSize: 18,
        paddingBottom: 4,
    },
})
