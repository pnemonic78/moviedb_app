import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';

import 'strings.dart';
import 'strings_he.dart';

const iso639English = 'en';
const iso639Hebrew = 'he';

class AppLocalizations {
  AppLocalizations(this.locale);

  final Locale locale;

  static AppLocalizations? of(BuildContext context) {
    return Localizations.of<AppLocalizations>(context, AppLocalizations);
  }

  static AppLocalizations get(BuildContext context) {
    return of(context)!;
  }

  static final Map<String, Map<String, String>> _localizedValues = {
    iso639English: R.string,
    iso639Hebrew: R_he.string,
  };

  static const locales = [
    Locale(iso639English, ''), // English, no country code
    Locale(iso639Hebrew, ''), // Hebrew, no country code
  ];

  String _getValue(String key) {
    return _localizedValues[locale.languageCode]?[key] ??
        _localizedValues[iso639English]![key]!;
  }

  String get title {
    return _getValue('title');
  }

  // Now Playing
  String get now_playing {
    return _getValue('now_playing');
  }

  // Movie Details
  String get budget_label {
    return _getValue('budget_label');
  }

  String get cast_label {
    return _getValue('cast_label');
  }

  String get genres_label {
    return _getValue('genres_label');
  }

  String get movie_details {
    return _getValue('movie_details');
  }

  String get popularity_label {
    return _getValue('popularity_label');
  }

  String get release_date_label {
    return _getValue('release_date_label');
  }

  String get revenue_label {
    return _getValue('revenue_label');
  }

  String get runtime_label {
    return _getValue('runtime_label');
  }

  String get summary_label {
    return _getValue('summary_label');
  }

  String get videos_label {
    return _getValue('videos_label');
  }

  String get vote_average_label {
    return _getValue('vote_average_label');
  }

  // Person
  String get personal_info_label {
    return _getValue('personal_info_label');
  }

  String get known_for_label {
    return _getValue('known_for_label');
  }

  String get known_credits_label {
    return _getValue('known_credits_label');
  }

  String get gender_label {
    return _getValue('gender_label');
  }

  String get birthday_label {
    return _getValue('birthday_label');
  }

  String get place_of_birth_label {
    return _getValue('place_of_birth_label');
  }

  String get deathday_label {
    return _getValue('deathday_label');
  }

  String get also_known_as_label {
    return _getValue('also_known_as_label');
  }

  String get biography_label {
    return _getValue('biography_label');
  }

  String get gender_female {
    return _getValue('gender_female');
  }

  String get gender_male {
    return _getValue('gender_male');
  }

  String get gender_other {
    return _getValue('gender_other');
  }

  String get person_cast_label {
    return _getValue('person_cast_label');
  }

  String get person_crew_label {
    return _getValue('person_crew_label');
  }

  String get person_cast_format {
    return _getValue('person_cast_format');
  }

  String get person_cast_format_none {
    return _getValue('person_cast_format_none');
  }

  String get person_crew_format {
    return _getValue('person_crew_format');
  }

  String get person_crew_format_none {
    return _getValue('person_crew_format_none');
  }

  String get popular {
    return _getValue('popular');
  }

  String get upcoming {
    return _getValue('upcoming');
  }

  String get top_rated {
    return _getValue('top_rated');
  }

  static const delegate = AppLocalizationsDelegate();
}

class AppLocalizationsDelegate extends LocalizationsDelegate<AppLocalizations> {
  const AppLocalizationsDelegate();

  static const _languages = [iso639English, iso639Hebrew];

  @override
  bool isSupported(Locale locale) => _languages.contains(locale.languageCode);

  @override
  Future<AppLocalizations> load(Locale locale) {
    return SynchronousFuture<AppLocalizations>(AppLocalizations(locale));
  }

  @override
  bool shouldReload(AppLocalizationsDelegate old) => false;
}
