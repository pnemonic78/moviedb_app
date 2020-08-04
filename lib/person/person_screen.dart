import 'dart:math';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:material_design_icons_flutter/material_design_icons_flutter.dart';
import 'package:sprintf/sprintf.dart';
import 'package:tmdb/credits/credits_table.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/cast.dart';
import 'package:tmdb/tmdb_api/model/crew.dart';
import 'package:tmdb/tmdb_api/model/gender.dart';
import 'package:tmdb/tmdb_api/model/person.dart';
import 'package:url_launcher/url_launcher.dart';

final _dateFormat = DateFormat.yMMMd();

final _biographyLinesMin = 5;
final _biographyLinesMax = 1000;

class PersonDetailsWidget extends StatefulWidget {
  final Person person;
  final ValueChanged<Person> onPosterTap;
  final ValueChanged<MovieCast> onCastTap;
  final ValueChanged<MovieCrew> onCrewTap;

  const PersonDetailsWidget({
    Key key,
    @required this.person,
    this.onPosterTap,
    this.onCastTap,
    this.onCrewTap,
  })  : assert(person != null),
        super(key: key);

  @override
  _PersonDetailsWidgetState createState() => _PersonDetailsWidgetState();
}

class _PersonDetailsWidgetState extends State<PersonDetailsWidget> {
  bool _biographyLinesExpanded;

  @override
  void initState() {
    super.initState();
    _biographyLinesExpanded = false;
  }

  @override
  Widget build(BuildContext context) {
    final person = widget.person;
    final media = MediaQuery.of(context);
    final imageWidth = personDetailsWidth;
    final imageHeight = personDetailsHeight;
    final posterUrl = TMDBApi.generatePosterUrl(
      person.profilePath,
      imageWidth,
      imageHeight,
      devicePixelRatio: media.devicePixelRatio,
    );
    final poster = CachedNetworkImage(
      imageUrl: posterUrl,
      placeholder: (context, url) => Icon(
        Icons.image,
        size: min(imageWidth, imageHeight),
      ),
      width: imageWidth,
      height: imageHeight,
      fit: BoxFit.contain,
    );
    final posterWidget = InkWell(
      child: ClipRRect(
        borderRadius: borderCircular_8,
        child: poster,
      ),
      onTap: () => widget.onPosterTap(person),
    );

    final textTheme = Theme.of(context).textTheme;
    final groupStyle = textTheme.headline5;
    final labelStyle = textTheme.headline6;
    final textStyle = textTheme.bodyText2.apply(fontSizeFactor: 1.25);
    final gone = Container();
    final string = AppLocalizations.of(context);

    final nameWidget = Text(
      person.name,
      maxLines: 2,
      style: textTheme.headline4,
      overflow: TextOverflow.ellipsis,
    );

    final personalInfoLabel = Text(
      string.personal_info_label,
      style: groupStyle,
    );

    final knownForMargin = SizedBox(height: padding_8);

    final knownForLabel = Text(
      string.known_for_label,
      style: labelStyle,
    );

    final knownForValue = Text(
      person.knownDepartment,
      style: textStyle,
    );

    String gender = "";
    switch (person.gender) {
      case Gender.female:
        gender = string.gender_female;
        break;
      case Gender.male:
        gender = string.gender_male;
        break;
      default:
        break;
    }

    final hasGender = (gender == null) || gender.isNotEmpty;

    final genderMargin = hasGender ? SizedBox(height: padding_8) : gone;

    final genderLabel =
        hasGender ? Text(string.gender_label, style: labelStyle) : gone;

    final genderValue = hasGender ? Text(gender, style: textStyle) : gone;

    final hasBirthday = (person.birthday != null);

    final birthdayMargin = hasBirthday ? SizedBox(height: padding_8) : gone;

    final birthdayLabel =
        hasBirthday ? Text(string.birthday_label, style: labelStyle) : gone;

    final birthdayValue = hasBirthday
        ? Text(_dateFormat.format(person.birthday), style: textStyle)
        : gone;

    final hasBirthplace = (person.birthplace != null);

    final birthplaceMargin = hasBirthplace ? SizedBox(height: padding_8) : gone;

    final birthplaceLabel = hasBirthplace
        ? Text(string.place_of_birth_label, style: labelStyle)
        : gone;

    final birthplaceValue =
        hasBirthplace ? Text(person.birthplace, style: textStyle) : gone;

    final hasDeathday = (person.deathday != null);

    final deathdayMargin = hasDeathday ? SizedBox(height: padding_8) : gone;

    final deathdayLabel =
        hasDeathday ? Text(string.deathday_label, style: labelStyle) : gone;

    final deathdayValue = hasDeathday
        ? Text(_dateFormat.format(person.deathday), style: textStyle)
        : gone;

    final hasAliases = (person.aliases != null) && person.aliases.isNotEmpty;

    final aliasesMargin = hasAliases ? SizedBox(height: padding_8) : gone;

    final aliasesLabel =
        hasAliases ? Text(string.also_known_as_label, style: labelStyle) : gone;

    final aliasesValue =
        hasAliases ? Text(person.aliases.join(", "), style: textStyle) : gone;

    final biographyMargin = SizedBox(height: padding_8);

    final biographyLabel = Text(
      string.biography_label,
      style: labelStyle,
    );

    final biographyWidget = InkWell(
      child: Text(
        person.biography ?? "",
        maxLines:
            (_biographyLinesExpanded ? _biographyLinesMax : _biographyLinesMin),
        overflow: TextOverflow.ellipsis,
        style: textStyle,
      ),
      onTap: () {
        setState(() {
          _biographyLinesExpanded = !_biographyLinesExpanded;
        });
      },
    );

    final hasHomepage = (person.homepage != null);

    final homepageWidget = hasHomepage
        ? InkWell(
            child: Icon(
              MdiIcons.web,
              size: personIconSize,
            ),
            onTap: _gotoHomepage,
          )
        : gone;

    final hasFacebook =
        (person.externalIds != null) && (person.externalIds.facebookId != null);

    final facebookMargin = hasFacebook ? SizedBox(width: padding_8) : gone;

    final facebookWidget = hasFacebook
        ? InkWell(
            child: Icon(
              MdiIcons.facebook,
              size: personIconSize,
            ),
            onTap: _gotoFacebook,
          )
        : gone;

    final hasImdb = (person.imdbId != null);

    final imdbMargin = hasImdb ? SizedBox(width: padding_8) : gone;

    final imdbWidget = hasImdb
        ? InkWell(
            child: Icon(
              MdiIcons.database,
              size: personIconSize,
            ),
            onTap: _gotoImdb,
          )
        : gone;

    final hasInstagram = (person.externalIds != null) &&
        (person.externalIds.instagramId != null);

    final instagramMargin = hasInstagram ? SizedBox(width: padding_8) : gone;

    final instagramWidget = hasInstagram
        ? InkWell(
            child: Icon(
              MdiIcons.instagram,
              size: personIconSize,
            ),
            onTap: _gotoInstagram,
          )
        : gone;

    final hasTwitter =
        (person.externalIds != null) && (person.externalIds.twitterId != null);

    final twitterMargin = hasTwitter ? SizedBox(width: padding_8) : gone;

    final twitterWidget = hasTwitter
        ? InkWell(
            child: Icon(
              MdiIcons.twitter,
              size: personIconSize,
            ),
            onTap: _gotoTwitter,
          )
        : gone;

    final hasCredits = person.credits != null;

    final creditsMargin = hasCredits ? SizedBox(height: padding_8) : gone;

    final creditsLabel = hasCredits
        ? Text(
            string.known_credits_label,
            style: groupStyle,
          )
        : gone;

    final creditsWidget = hasCredits
        ? CreditsTable(
            credits: person.credits,
            onCastTap: widget.onCastTap,
            onCrewTap: widget.onCrewTap,
          )
        : gone;

    final details = Padding(
      padding: paddingAll_8,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          nameWidget,
          SizedBox(height: padding_8),
          Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Column(
                children: [
                  posterWidget,
                  SizedBox(height: padding_8),
                  Row(
                    children: [
                      homepageWidget,
                      imdbMargin,
                      imdbWidget,
                      facebookMargin,
                      facebookWidget,
                      instagramMargin,
                      instagramWidget,
                      twitterMargin,
                      twitterWidget,
                    ],
                  )
                ],
              ),
              SizedBox(width: padding_16),
              Expanded(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    personalInfoLabel,
                    knownForMargin,
                    knownForLabel,
                    knownForValue,
                    genderMargin,
                    genderLabel,
                    genderValue,
                    birthdayMargin,
                    birthdayLabel,
                    birthdayValue,
                    birthplaceMargin,
                    birthplaceLabel,
                    birthplaceValue,
                    deathdayMargin,
                    deathdayLabel,
                    deathdayValue,
                    aliasesMargin,
                    aliasesLabel,
                    aliasesValue,
                  ],
                ),
              ),
            ],
          ),
          biographyMargin,
          biographyLabel,
          biographyWidget,
          creditsMargin,
          creditsLabel,
          creditsWidget,
        ],
      ),
    );

    return details;
  }

  void _gotoFacebook() async {
    final url =
        sprintf(TMDBApi.facebook_url, [widget.person.externalIds.facebookId]);
    _goto(url);
  }

  void _gotoHomepage() async {
    final url = widget.person.homepage;
    _goto(url);
  }

  void _gotoImdb() async {
    final url = sprintf(TMDBApi.imdb_url, [widget.person.imdbId]);
    _goto(url);
  }

  void _gotoInstagram() async {
    final url =
        sprintf(TMDBApi.instagram_url, [widget.person.externalIds.instagramId]);
    _goto(url);
  }

  void _gotoTwitter() async {
    final url =
        sprintf(TMDBApi.twitter_url, [widget.person.externalIds.twitterId]);
    _goto(url);
  }

  void _goto(String url) async {
    if (await canLaunch(url)) {
      await launch(url);
    }
  }
}
