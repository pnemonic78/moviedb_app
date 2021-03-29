import React, { Component } from "react"
import { GestureResponderEvent, Pressable, StyleSheet, Text, View } from "react-native"
import { Icon } from "react-native-elements"
import R from "../../res/R"
import { Gender, toGender } from "../../tmdb_api/model/Gender"
import { Person, PersonClass } from "../../tmdb_api/model/Person"
import TMDBApi from "../../tmdb_api/TMDBApi"
import { LoadingIcon } from "../LoadingIcon"
import { OnPersonPress } from "./PersonClickListener"

const posterDetailsWidth = R.dimen.personDetailsWidth
const posterDetailsHeight = R.dimen.personDetailsHeight

const _summaryLinesMin = 5
const _summaryLinesMax = 1000

const iconColor = R.color.iconColor

interface PersonDetailsWidgetProps {
    person: Person
    onPosterPress?: OnPersonPress
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

    private gotoHomepage() {
        console.log("go to home")
    }

    private gotoFacebook() {
        console.log("go to fb")
    }

    private gotoImdb() {
        console.log("go to imdb")
    }

    private gotoInstagram() {
        console.log("go to insta")
    }

    private gotoTwitter() {
        console.log("go to twitter")
    }

    render() {
        let person = this.props.person
        let styles = styleSheet
        let personIconSize = R.dimen.personIconSize / 2

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

        let birthdayLabel = gone
        let birthdayValue = gone
            
        let birthplaceLabel = gone
        let birthplaceValue = gone
            
        let deathdayLabel = gone
        let deathdayValue = gone
            
        let aliasesLabel = gone
        let aliasesValue = gone

        let hasHomepage = (person.homepage != null)

        let homepageWidget = hasHomepage ? <Icon
            raised={true}
            reverse={true}
            name="home"
            type="simple-line-icon"
            color={iconColor}
            size={personIconSize}
            containerStyle={styles.icon}
            onPress={this.gotoHomepage.bind(this)}
        /> : gone

        let hasImdb = (person.imdb_id != null) || (person.external_ids?.imdb_id != null)

        let imdbWidget = hasImdb ? <Icon
            raised={true}
            reverse={true}
            name="storage"
            type="material"
            color={iconColor}
            size={personIconSize}
            containerStyle={styles.icon}
            onPress={this.gotoImdb.bind(this)}
        /> : gone

        let hasFacebook = (person.external_ids?.facebook_id != null)

        let facebookWidget = hasFacebook ? <Icon
            raised={true}
            reverse={true}
            name="social-facebook"
            type="simple-line-icon"
            color={iconColor}
            size={personIconSize}
            containerStyle={styles.icon}
            onPress={this.gotoFacebook.bind(this)}
        /> : gone

        let hasInstagram = (person.external_ids?.instagram_id != null)

        let instagramWidget = hasInstagram ? <Icon
            raised={true}
            reverse={true}
            name="social-instagram"
            type="simple-line-icon"
            color={iconColor}
            size={personIconSize}
            containerStyle={styles.icon}
            onPress={this.gotoInstagram.bind(this)}
        /> : gone

        let hasTwitter = (person.external_ids?.twitter_id != null)

        let twitterWidget = hasTwitter ? <Icon
            raised={true}
            reverse={true}
            name="social-twitter"
            type="simple-line-icon"
            color={iconColor}
            size={personIconSize}
            containerStyle={styles.icon}
            onPress={this.gotoTwitter.bind(this)}
        /> : gone

        return <View style={styles.details}>
            {nameWidget}
            <View style={{ flexDirection: "row" }}>
                <View>
                    {posterWidget}
                    <View style={{ flexDirection: "row" }}>
                        {homepageWidget}
                        {imdbWidget}
                        {facebookWidget}
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
    },
    poster: {
        borderRadius: R.dimen.cardRadius,
        marginEnd: 8,
        height: posterDetailsHeight,
        width: posterDetailsWidth,
    },
    name: {
        fontSize: 32,
        paddingBottom: 8,
    },
    text: {
        flexWrap: "wrap",
        fontSize: 18,
        paddingBottom: 4,
        paddingTop: 4,
    },
})
