import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';

import 'strings.dart';
import 'strings_he.dart';

const ISO639_ENGLISH = 'en';
const ISO639_HEBREW = 'he';

class AppLocalizations {
  AppLocalizations(this.locale);

  final Locale locale;

  static AppLocalizations? of(BuildContext context) {
    return Localizations.of<AppLocalizations>(context, AppLocalizations);
  }

  static final Map<String, Map<String, String>> _localizedValues = {
    ISO639_ENGLISH: R.string,
    ISO639_HEBREW: R_he.string,
  };

  String? get title {
    return _localizedValues[locale.languageCode]?['title'];
  }

  // Now Playing
  String? get now_playing {
    return _localizedValues[locale.languageCode]?['now_playing'];
  }

  // Movie Details
  String? get budget_label {
    return _localizedValues[locale.languageCode]?['budget_label'];
  }

  String? get cast_label {
    return _localizedValues[locale.languageCode]?['cast_label'];
  }

  String? get genres_label {
    return _localizedValues[locale.languageCode]?['genres_label'];
  }

  String? get movie_details {
    return _localizedValues[locale.languageCode]?['movie_details'];
  }

  String? get popularity_label {
    return _localizedValues[locale.languageCode]?['popularity_label'];
  }

  String? get release_date_label {
    return _localizedValues[locale.languageCode]?['release_date_label'];
  }

  String? get revenue_label {
    return _localizedValues[locale.languageCode]?['revenue_label'];
  }

  String? get runtime_label {
    return _localizedValues[locale.languageCode]?['runtime_label'];
  }

  String? get summary_label {
    return _localizedValues[locale.languageCode]?['summary_label'];
  }

  String? get videos_label {
    return _localizedValues[locale.languageCode]?['videos_label'];
  }

  String? get vote_average_label {
    return _localizedValues[locale.languageCode]?['vote_average_label'];
  }

  // Person
  String? get personal_info_label {
    return _localizedValues[locale.languageCode]?['personal_info_label'];
  }

  String? get known_for_label {
    return _localizedValues[locale.languageCode]?['known_for_label'];
  }

  String? get known_credits_label {
    return _localizedValues[locale.languageCode]?['known_credits_label'];
  }

  String? get gender_label {
    return _localizedValues[locale.languageCode]?['gender_label'];
  }

  String? get birthday_label {
    return _localizedValues[locale.languageCode]?['birthday_label'];
  }

  String? get place_of_birth_label {
    return _localizedValues[locale.languageCode]?['place_of_birth_label'];
  }

  String? get deathday_label {
    return _localizedValues[locale.languageCode]?['deathday_label'];
  }

  String? get also_known_as_label {
    return _localizedValues[locale.languageCode]?['also_known_as_label'];
  }

  String? get biography_label {
    return _localizedValues[locale.languageCode]?['biography_label'];
  }

  String? get gender_female {
    return _localizedValues[locale.languageCode]?['gender_female'];
  }

  String? get gender_male {
    return _localizedValues[locale.languageCode]?['gender_male'];
  }

  String? get person_cast_label {
    return _localizedValues[locale.languageCode]?['person_cast_label'];
  }

  String? get person_crew_label {
    return _localizedValues[locale.languageCode]?['person_crew_label'];
  }

  String? get person_cast_format {
    return _localizedValues[locale.languageCode]?['person_cast_format'];
  }

  String? get person_cast_format_none {
    return _localizedValues[locale.languageCode]?['person_cast_format_none'];
  }

  String? get person_crew_format {
    return _localizedValues[locale.languageCode]?['person_crew_format'];
  }

  String? get person_crew_format_none {
    return _localizedValues[locale.languageCode]?['person_crew_format_none'];
  }

  String? get popular {
    return _localizedValues[locale.languageCode]?['popular'];
  }

  String? get upcoming {
    return _localizedValues[locale.languageCode]?['upcoming'];
  }

  String? get top_rated {
    return _localizedValues[locale.languageCode]?['top_rated'];
  }
}

class AppLocalizationsDelegate extends LocalizationsDelegate<AppLocalizations> {
  const AppLocalizationsDelegate();

  static const _languages = [ISO639_ENGLISH, ISO639_HEBREW];

  static const locales = [
    Locale(ISO639_ENGLISH, ''), // English, no country code
    Locale(ISO639_HEBREW, ''), // Hebrew, no country code
  ];

  @override
  bool isSupported(Locale locale) => _languages.contains(locale.languageCode);

  @override
  Future<AppLocalizations> load(Locale locale) {
    return SynchronousFuture<AppLocalizations>(AppLocalizations(locale));
  }

  @override
  bool shouldReload(AppLocalizationsDelegate old) => false;
}
