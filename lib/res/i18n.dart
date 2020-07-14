import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';

import 'strings.dart';
import 'strings_he.dart';

class AppLocalizations {
  AppLocalizations(this.locale);

  final Locale locale;

  static AppLocalizations of(BuildContext context) {
    return Localizations.of<AppLocalizations>(context, AppLocalizations);
  }

  static Map<String, Map<String, String>> _localizedValues = {
    'en': R.string,
    'he': R_he.string,
  };

  String get title {
    return _localizedValues[locale.languageCode]['title'];
  }

  // Now Playing
  String get now_playing {
    return _localizedValues[locale.languageCode]['now_playing'];
  }

  // Movie Details
  String get budget_label {
    return _localizedValues[locale.languageCode]['budget_label'];
  }

  String get genres_label {
    return _localizedValues[locale.languageCode]['genres_label'];
  }

  String get movie_details {
    return _localizedValues[locale.languageCode]['movie_details'];
  }

  String get popularity_label {
    return _localizedValues[locale.languageCode]['popularity_label'];
  }

  String get release_date_label {
    return _localizedValues[locale.languageCode]['release_date_label'];
  }

  String get revenue_label {
    return _localizedValues[locale.languageCode]['revenue_label'];
  }

  String get runtime_label {
    return _localizedValues[locale.languageCode]['runtime_label'];
  }

  String get summary_label {
    return _localizedValues[locale.languageCode]['summary_label'];
  }

  String get videos_label {
    return _localizedValues[locale.languageCode]['videos_label'];
  }

  String get vote_average_label {
    return _localizedValues[locale.languageCode]['vote_average_label'];
  }
}

class AppLocalizationsDelegate
    extends LocalizationsDelegate<AppLocalizations> {
  const AppLocalizationsDelegate();

  static const _languages = ['en', 'he'];

  @override
  bool isSupported(Locale locale) => _languages.contains(locale.languageCode);

  @override
  Future<AppLocalizations> load(Locale locale) {
    return SynchronousFuture<AppLocalizations>(AppLocalizations(locale));
  }

  @override
  bool shouldReload(AppLocalizationsDelegate old) => false;
}
