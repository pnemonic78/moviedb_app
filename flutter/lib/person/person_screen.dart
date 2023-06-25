import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:material_design_icons_flutter/material_design_icons_flutter.dart';
import 'package:tmdb/credits/credits_table.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/gender.dart';
import 'package:tmdb/tmdb_api/model/person.dart';
import 'package:tmdb/tmdb_api/model/person_cast.dart';
import 'package:tmdb/tmdb_api/model/person_crew.dart';
import 'package:url_launcher/url_launcher.dart';

final _dateFormat = DateFormat.yMMMd();

const _summaryLinesMin = 5;
const _summaryLinesMax = 1000;

class PersonDetailsWidget extends StatefulWidget {
  final Person person;
  final ValueChanged<Person>? onTapPoster;
  final ValueChanged<PersonCast>? onCastTap;
  final ValueChanged<PersonCrew>? onCrewTap;

  const PersonDetailsWidget({
    super.key,
    required this.person,
    this.onTapPoster,
    this.onCastTap,
    this.onCrewTap,
  });

  @override
  State<PersonDetailsWidget> createState() => _PersonDetailsWidgetState();
}

class _PersonDetailsWidgetState extends State<PersonDetailsWidget> {
  bool _summaryLinesExpanded = false;

  @override
  void initState() {
    super.initState();
    _summaryLinesExpanded = false;
  }

  @override
  Widget build(BuildContext context) {
    final person = widget.person;
    const imageWidth = personDetailsWidth;
    const imageHeight = personDetailsHeight;
    final poster = TMDBApi.generatePoster(
      posterPath: person.profilePath,
      posterWidth: imageWidth,
      posterHeight: imageHeight,
    );
    final posterWidget = InkWell(
      child: ClipRRect(
        borderRadius: borderCircular_8,
        child: poster,
      ),
      onTap: () => widget.onTapPoster?.call(person),
    );

    final textTheme = Theme.of(context).textTheme;
    final groupStyle = textTheme.headlineSmall;
    final labelStyle = textTheme.titleLarge;
    final textStyle = textTheme.bodyLarge;
    final gone = Container();
    final string = AppLocalizations.of(context)!;

    final nameWidget = Text(
      person.getTitle() ?? "",
      maxLines: 2,
      style: textTheme.headlineMedium,
      overflow: TextOverflow.ellipsis,
    );

    final personalInfoLabel = Text(
      string.personal_info_label,
      style: groupStyle,
    );

    const knownForMargin = SizedBox(height: padding_8);

    final knownForLabel = Text(
      string.known_for_label,
      style: labelStyle,
    );

    final knownForWidget = Text(
      person.knownDepartment ?? "",
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

    final hasGender = gender.isNotEmpty;

    final genderMargin = hasGender ? const SizedBox(height: padding_8) : gone;

    final genderLabel =
        hasGender ? Text(string.gender_label, style: labelStyle) : gone;

    final genderWidget = hasGender ? Text(gender, style: textStyle) : gone;

    final hasBirthday = (person.birthday != null);

    final birthdayMargin =
        hasBirthday ? const SizedBox(height: padding_8) : gone;

    final birthdayLabel =
        hasBirthday ? Text(string.birthday_label, style: labelStyle) : gone;

    final birthdayWidget = hasBirthday
        ? Text(_dateFormat.format(person.birthday!), style: textStyle)
        : gone;

    final hasBirthplace = (person.birthplace != null);

    final birthplaceMargin =
        hasBirthplace ? const SizedBox(height: padding_8) : gone;

    final birthplaceLabel = hasBirthplace
        ? Text(string.place_of_birth_label, style: labelStyle)
        : gone;

    final birthplaceWidget =
        hasBirthplace ? Text(person.birthplace!, style: textStyle) : gone;

    final hasDeathday = (person.deathday != null);

    final deathdayMargin =
        hasDeathday ? const SizedBox(height: padding_8) : gone;

    final deathdayLabel =
        hasDeathday ? Text(string.deathday_label, style: labelStyle) : gone;

    final deathdayWidget = hasDeathday
        ? Text(_dateFormat.format(person.deathday!), style: textStyle)
        : gone;

    final hasAliases = person.aliases.isNotEmpty;

    final aliasesMargin = hasAliases ? const SizedBox(height: padding_8) : gone;

    final aliasesLabel =
        hasAliases ? Text(string.also_known_as_label, style: labelStyle) : gone;

    final aliasesWidget =
        hasAliases ? Text(person.aliases.join(", "), style: textStyle) : gone;

    const summaryMargin = SizedBox(height: padding_8);

    final summaryLabel = Text(
      string.summary_label,
      style: labelStyle,
    );

    final summaryText = Text(
      person.biography ?? "",
      maxLines: (_summaryLinesExpanded ? _summaryLinesMax : _summaryLinesMin),
      overflow: TextOverflow.fade,
      style: textStyle,
    );

    final summaryWidget = InkWell(
      child: summaryText,
      onTap: () {
        setState(() {
          _summaryLinesExpanded = !_summaryLinesExpanded;
        });
      },
    );

    final hasHomepage = (person.homepage != null);

    final homepageWidget = hasHomepage
        ? InkWell(
            onTap: _gotoHomepage,
            child: Icon(
              MdiIcons.web,
              size: personIconSize,
            ),
          )
        : gone;

    final hasFacebook = (person.externalIds?.facebookId != null);

    final facebookMargin =
        hasFacebook ? const SizedBox(width: padding_8) : gone;

    final facebookWidget = hasFacebook
        ? InkWell(
            onTap: _gotoFacebook,
            child: Icon(
              MdiIcons.facebook,
              size: personIconSize,
            ),
          )
        : gone;

    final hasImdb = (person.imdbId != null);

    final imdbMargin = hasImdb ? const SizedBox(width: padding_8) : gone;

    final imdbWidget = hasImdb
        ? InkWell(
            onTap: _gotoImdb,
            child: Icon(
              MdiIcons.database,
              size: personIconSize,
            ),
          )
        : gone;

    final hasInstagram = (person.externalIds?.instagramId != null);

    final instagramMargin =
        hasInstagram ? const SizedBox(width: padding_8) : gone;

    final instagramWidget = hasInstagram
        ? InkWell(
            onTap: _gotoInstagram,
            child: Icon(
              MdiIcons.instagram,
              size: personIconSize,
            ),
          )
        : gone;

    final hasTwitter = (person.externalIds?.twitterId != null);

    final twitterMargin = hasTwitter ? const SizedBox(width: padding_8) : gone;

    final twitterWidget = hasTwitter
        ? InkWell(
            onTap: _gotoTwitter,
            child: Icon(
              MdiIcons.twitter,
              size: personIconSize,
            ),
          )
        : gone;

    final hasCredits = person.credits != null;

    final creditsMargin = hasCredits ? const SizedBox(height: padding_8) : gone;

    final creditsLabel = hasCredits
        ? Text(
            string.known_credits_label,
            style: groupStyle,
          )
        : gone;

    final creditsWidget = hasCredits
        ? CreditsTable(
            credits: person.credits!,
            onCastTap: widget.onCastTap ?? (PersonCast person) => {},
            onCrewTap: widget.onCrewTap ?? (PersonCrew person) => {},
          )
        : gone;

    final details = Padding(
      padding: paddingAll_8,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          nameWidget,
          const SizedBox(height: padding_8),
          Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Column(
                children: [
                  posterWidget,
                  const SizedBox(height: padding_8),
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
              const SizedBox(width: padding_16),
              Expanded(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    personalInfoLabel,
                    knownForMargin,
                    knownForLabel,
                    knownForWidget,
                    genderMargin,
                    genderLabel,
                    genderWidget,
                    birthdayMargin,
                    birthdayLabel,
                    birthdayWidget,
                    birthplaceMargin,
                    birthplaceLabel,
                    birthplaceWidget,
                    deathdayMargin,
                    deathdayLabel,
                    deathdayWidget,
                    aliasesMargin,
                    aliasesLabel,
                    aliasesWidget,
                  ],
                ),
              ),
            ],
          ),
          summaryMargin,
          summaryLabel,
          summaryWidget,
          creditsMargin,
          creditsLabel,
          creditsWidget,
        ],
      ),
    );

    return details;
  }

  void _gotoFacebook() async {
    final id = widget.person.externalIds?.facebookId;
    if ((id == null) || id.isEmpty) return;
    final url = TMDBApi.generateFacebookUrl(id);
    _goto(url);
  }

  void _gotoHomepage() async {
    final url = widget.person.homepage;
    _goto(url);
  }

  void _gotoImdb() async {
    final id = widget.person.imdbId ?? widget.person.externalIds?.imdbId;
    if ((id == null) || id.isEmpty) return;
    final url = TMDBApi.generateImdbUrl(id);
    _goto(url);
  }

  void _gotoInstagram() async {
    final id = widget.person.externalIds?.instagramId;
    if ((id == null) || id.isEmpty) return;
    final url = TMDBApi.generateInstagramUrl(id);
    _goto(url);
  }

  void _gotoTwitter() async {
    final id = widget.person.externalIds?.twitterId;
    if ((id == null) || id.isEmpty) return;
    final url = TMDBApi.generateTwitterUrl(id);
    _goto(url);
  }

  void _goto(String? urlString) async {
    if ((urlString == null) || urlString.isEmpty) {
      log('URL expected');
      return;
    }
    final url = Uri.parse(urlString);
    if (await canLaunchUrl(url)) {
      await launchUrl(url);
    }
  }
}
