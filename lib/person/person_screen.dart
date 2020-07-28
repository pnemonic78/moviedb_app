import 'dart:math';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:tmdb/res/dimens.dart';
import 'package:tmdb/res/i18n.dart';
import 'package:tmdb/tmdb_api/api.dart';
import 'package:tmdb/tmdb_api/model/gender.dart';
import 'package:tmdb/tmdb_api/model/person.dart';

class PersonDetailsWidget extends StatelessWidget {
  final Person person;
  final ValueChanged<Person> onPosterTap;

  const PersonDetailsWidget({
    Key key,
    @required this.person,
    this.onPosterTap,
  })  : assert(person != null),
        super(key: key);

  @override
  Widget build(BuildContext context) {
    final media = MediaQuery.of(context);
    final imageWidth = castDetailsWidth;
    final imageHeight = castDetailsHeight;
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
      onTap: () => onPosterTap(person),
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

    final birthdayValue =
        hasBirthday ? Text(person.birthday, style: textStyle) : gone;

    final hasBirthplace = (person.birthday != null);

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

    final deathdayValue =
        hasDeathday ? Text(person.deathday, style: textStyle) : gone;

    final hasAliases = (person.aliases != null) && person.aliases.isNotEmpty;

    final aliasesMargin = hasDeathday ? SizedBox(height: padding_8) : gone;

    final aliasesLabel =
        hasAliases ? Text(string.also_known_as_label, style: labelStyle) : gone;

    final aliasesValue =
        hasAliases ? Text(person.aliases.join(", "), style: textStyle) : gone;

    final biographyMargin = SizedBox(height: padding_8);

    final biographyLabel = Text(
      string.biography_label,
      style: labelStyle,
    );

    final biographyWidget = Text(
      person.biography ?? "",
      maxLines: 1000,
      overflow: TextOverflow.ellipsis,
      style: textStyle,
    );

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
              posterWidget,
              SizedBox(width: padding_16),
              Expanded(
                child: Column(
                  mainAxisSize: MainAxisSize.max,
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
        ],
      ),
    );

    return details;
  }
}
