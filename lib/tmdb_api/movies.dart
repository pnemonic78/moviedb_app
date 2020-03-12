import 'package:tmdb/tmdb_api/movie_details.dart';

import 'movie.dart';

class Movies {
  static final _movies = <Movie>[];
  static final _movieDetails = <MovieDetails>[];

  static List<Movie> getMovies() {
    if (_movies.isEmpty) {
      _movies.add(Movie(
        adult: false,
        backdropPath: "/askg3SMvhqEl4OL52YuvdtY40Yb.jpg",
        genreIds: [12, 16, 35, 10751],
        id: 354912,
        originalLanguage: "en",
        originalTitle: "Coco",
        overview:
            "Despite his family’s baffling generations-old ban on music, Miguel dreams of becoming an accomplished musician like his idol, Ernesto de la Cruz. Desperate to prove his talent, Miguel finds himself in the stunning and colorful Land of the Dead following a mysterious chain of events. Along the way, he meets charming trickster Hector, and together, they set off on an extraordinary journey to unlock the real story behind Miguel's family history.",
        popularity: 258.5775,
        posterPath: "/eKi8dIrr8voobbaGzDpe8w0PVbC.jpg",
        releaseDate: DateTime.utc(2017, 10, 27),
        title: "Coco",
        video: false,
        voteAverage: 7.7,
        voteCount: 2073,
      ));
      _movies.add(Movie(
        adult: false,
        backdropPath: "/kaIfm5ryEOwYg8mLbq8HkPuM1Fo.jpg",
        genreIds: [878, 28, 12, 35],
        id: 284053,
        originalLanguage: "en",
        originalTitle: "Thor: Ragnarok",
        overview:
            "Thor is imprisoned on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the prophecy of destruction to his homeworld and the end of Asgardian civilization, at the hands of an all-powerful new threat, the ruthless Hela.",
        popularity: 156.71509,
        posterPath: "/oSLd5GYGsiGgzDPKTwQh7wamO8t.jpg",
        releaseDate: DateTime.utc(2017, 10, 25),
        title: "Thor: Ragnarok",
        video: false,
        voteAverage: 7.4,
        voteCount: 3845,
      ));
      _movies.add(Movie(
        adult: false,
        backdropPath: "/bd1X5nNrrAHVGG0MxsAeCOPPh1w.jpg",
        genreIds: [10751, 16, 12, 35],
        id: 335777,
        originalLanguage: "en",
        originalTitle: "The Nut Job 2: Nutty by Nature",
        overview:
            "When the evil mayor of Oakton decides to bulldoze Liberty Park and build a dangerous amusement park in its place, Surly Squirrel and his ragtag group of animal friends need to band together to save their home, defeat the mayor, and take back the park.",
        popularity: 124.44905,
        posterPath: "/xOfdQHNF9TlrdujyAjiKfUhxSXy.jpg",
        releaseDate: DateTime.utc(2017, 8, 11),
        title: "The Nut Job 2: Nutty by Nature",
        video: false,
        voteAverage: 5.9,
        voteCount: 109,
      ));
      _movies.add(Movie(
        adult: false,
        backdropPath: "/iF9d73lbtDYeCsPhQmjtkEmlrYG.jpg",
        genreIds: [80, 18, 9648, 53],
        id: 395834,
        originalLanguage: "en",
        originalTitle: "Wind River",
        overview:
            "An FBI agent teams with the town's veteran game tracker to investigate a murder that occurred on a Native American reservation.",
        popularity: 114.252556,
        posterPath: "/pySivdR845Hom4u4T2WNkJxe6Ad.jpg",
        releaseDate: DateTime.utc(2017, 8, 3),
        title: "Wind River",
        video: false,
        voteAverage: 7.5,
        voteCount: 827,
      ));
      _movies.add(Movie(
        adult: false,
        backdropPath: "/stmYfCUGd8Iy6kAMBr6AmWqx8Bq.jpg",
        genreIds: [28, 35, 878, 10751],
        id: 454626,
        originalLanguage: "en",
        originalTitle: "Sonic the Hedgehog",
        overview:
            "Based on the global blockbuster videogame franchise from Sega, Sonic the Hedgehog tells the story of the world’s speediest hedgehog as he embraces his new home on Earth. In this live-action adventure comedy, Sonic and his new best friend team up to defend the planet from the evil genius Dr. Robotnik and his plans for world domination.",
        popularity: 294.837,
        posterPath: "/aQvJ5WPzZgYVDrxLX4R6cLJCEaQ.jpg",
        releaseDate: DateTime.utc(2020, 2, 12),
        title: "Sonic the Hedgehog",
        video: false,
        voteAverage: 7.2,
        voteCount: 862,
      ));
      _movies.add(Movie(
        adult: false,
        backdropPath: "/jiqD14fg7UTZOT6qgvzTmfRYpWI.jpg",
        genreIds: [28, 35, 80],
        id: 495764,
        originalLanguage: "en",
        originalTitle:
            "Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)",
        overview:
            "After her breakup with the Joker, Harley Quinn joins forces with singer Black Canary, assassin Huntress, and police detective Renee Montoya to help a young girl named Cassandra, who had a hit placed on her after she stole a rare diamond from crime lord Roman Sionis.",
        popularity: 205.76,
        posterPath: "/h4VB6m0RwcicVEZvzftYZyKXs6K.jpg",
        releaseDate: DateTime.utc(2020, 2, 5),
        title:
            "Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)",
        video: false,
        voteAverage: 6.8,
        voteCount: 1203,
      ));
    }
    return _movies;
  }

  static List<MovieDetails> getMovieDetails() {
    if (_movieDetails.isEmpty) {
      _movieDetails.add(MovieDetails(
        adult: false,
        backdropPath: "/bd1X5nNrrAHVGG0MxsAeCOPPh1w.jpg",
        genreIds: [10751, 16, 12, 35],
        id: 335777,
        originalLanguage: "en",
        originalTitle: "The Nut Job 2: Nutty by Nature",
        overview:
            "When the evil mayor of Oakton decides to bulldoze Liberty Park and build a dangerous amusement park in its place, Surly Squirrel and his ragtag group of animal friends need to band together to save their home, defeat the mayor, and take back the park.",
        popularity: 124.44905,
        posterPath: "/xOfdQHNF9TlrdujyAjiKfUhxSXy.jpg",
        releaseDate: DateTime.utc(2017, 8, 11),
        title: "The Nut Job 2: Nutty by Nature",
        video: false,
        voteAverage: 5.9,
        voteCount: 109,
      ));
    }
    return _movieDetails;
  }
}
