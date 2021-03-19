import React, { Component } from "react";
import { ImageStyle, Pressable, StyleSheet, Text, View } from "react-native";
import { Rating } from "react-native-ratings";
import R from "../../res/R";
import { Genre } from "../../tmdb_api/model/Genre";
import { MovieDetails } from "../../tmdb_api/model/MovieDetails";
import TMDBApi from "../../tmdb_api/TMDBApi";
import { LoadingImage } from "../LoadingImage";
import { Utils } from "../main/Utils";

const posterDetailsWidth = R.dimen.posterDetailsWidth;
const posterDetailsHeight = R.dimen.posterDetailsHeight;

const _summaryLinesMin = 5;
const _summaryLinesMax = 1000;

interface MovieDetailsWidgetProps {
    movie: MovieDetails;
// final ValueChanged < MovieDetails > onTapPoster;
// final ValueChanged < MovieVideo > onVideoTap;
// final ValueChanged < MediaCast > onCastTap;
}

interface MovieDetailsWidgetState {
    summaryLinesExpanded: boolean;
// VideosList _videoList;
// CastList _castList;
}

export class MovieDetailsWidget extends Component<MovieDetailsWidgetProps, MovieDetailsWidgetState> {
    constructor(props: MovieDetailsWidgetProps) {
        super(props);
        this.state = {
            summaryLinesExpanded: false,
        };
    }

    render() {
        let movie = this.props.movie;
        let styles = styleSheet;

        let gone = <View />

        // final media = MediaQuery.of(context);
        let imageWidth = posterDetailsWidth;
        let imageHeight = posterDetailsHeight;
        let posterUrl = TMDBApi.generatePosterUrl(
            movie.poster_path,
            imageWidth,
            imageHeight
        );
        let poster = <LoadingImage
            defaultSource={R.drawable.outline_image}
            source={{ uri: posterUrl }}
            style={styles.poster as ImageStyle} />;
        let posterWidget = <Pressable>
            {poster}
        </Pressable>

        let hasTagline = movie.tagline?.length;

        let taglineWidget = hasTagline ? <Text style={styles.tagline} numberOfLines={2}>{movie.tagline ?? ""}</Text> : gone;

        let voteAverageLabel = <Text style={styles.label}>{R.string.vote_average_label}</Text>

        let voteAverageWidget = <Rating
            type="custom"
            ratingCount={5}
            imageSize={20}
            readonly={true}
            ratingColor={R.color.ratingColor}
            startingValue={movie.vote_average / 2.0}x
            style={styles.vote} />

        let voteAverageValue = <Text style={[styles.text, { paddingStart: 8 }]}>{Utils.formatPercent(movie.vote_average * 10)}</Text>

        let hasRuntime = (movie.runtime != null) && (movie.runtime > 0);

        let runtimeLabel = hasRuntime ? <Text style={styles.label}>{R.string.runtime_label}</Text> : gone;

        let runtimeWidget = hasRuntime ? <Text style={styles.text}>{Utils.formatDuration(movie.runtime)}</Text> : gone;

        let hasBudget = (movie.budget != null) && (movie.budget > 0);

        let budgetLabel = hasBudget ? <Text style={styles.label}>{R.string.budget_label}</Text> : gone;

        let budgetWidget = hasBudget ? <Text style={styles.text}>{Utils.formatCurrency(movie.budget)}</Text> : gone;

        let hasRevenue = (movie.revenue != null) && (movie.revenue > 0);

        let revenueLabel = hasRevenue ? <Text style={styles.label}>{R.string.revenue_label}</Text> : gone;

        let revenueWidget = hasRevenue ? <Text style={styles.text}>{Utils.formatCurrency(movie.revenue)}</Text> : gone;

        let dateLabel = <Text style={styles.label}>{R.string.release_date_label}</Text>;

        let dateWidget = <Text style={styles.text}>{Utils.formatDate(movie.release_date)}</Text>;

        let hasGenres = movie.genres?.length;

        let genresLabel = hasGenres ? <Text style={styles.label}>{R.string.genres_label}</Text> : gone;

        let genresWidget = hasGenres ? <Text style={styles.text}>{movie.genres.map(genre => genre.name).join(", ")}</Text> : gone;

        let summaryLabel = <Text style={styles.label}>{R.string.summary_label}</Text>

        let summaryLinesExpanded = this.state.summaryLinesExpanded;
        let summaryWidget = <Pressable onPress={() => this.setState({ summaryLinesExpanded: !summaryLinesExpanded})}>
            <Text
                style={styles.text}
                numberOfLines={summaryLinesExpanded ? _summaryLinesMax : _summaryLinesMin}>
                {movie.overview ?? ""}
            </Text>
        </Pressable>

        return <View style={styles.details}>
            {taglineWidget}
            <View style={{ flexDirection: "row" }}>
                {posterWidget}
                <View style={{ flexDirection: "column" }}>
                    {voteAverageLabel}
                    <View style={{ flexDirection: "row" }}>
                        {voteAverageWidget}
                        {voteAverageValue}
                    </View>
                    {runtimeLabel}
                    {runtimeWidget}
                    {budgetLabel}
                    {budgetWidget}
                    {revenueLabel}
                    {revenueWidget}
                    {dateLabel}
                    {dateWidget}
                    {genresLabel}
                    {genresWidget}
                </View>
            </View>
            {summaryLabel}
            {summaryWidget}
        </View>
    }
}

const styleSheet = StyleSheet.create({
    details: {
        flexDirection: "column",
        padding: 8,
    },
    label: {
        fontSize: 18,
        fontWeight: "bold",
        paddingBottom: 4,
    },
    poster: {
        borderRadius: R.dimen.cardRadius,
        marginEnd: 8,
        height: posterDetailsHeight,
        width: posterDetailsWidth,
    },
    tagline: {
        fontSize: 18,
        padding: 8,
    },
    text: {
        fontSize: 18,
        paddingBottom: 8,
    },
    vote: {
        paddingTop: 8,
        width: 100, // 5 * 20
    },
});
