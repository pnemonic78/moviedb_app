import ScreenName from "@/app/ScreenName";
import { Column } from '@/components/column';
import { Rating } from '@/components/rating';
import { Row } from '@/components/row';
import { ThemedText } from '@/components/themed-text';
import { ThemedView } from '@/components/themed-view';
import { Res } from '@/res/Res';
import TMDBApi from '@/tmdb_api/TMDBApi';
import { Image } from 'expo-image';
import { useRouter } from 'expo-router';
import { Pressable, ScrollView, StyleSheet, View } from "react-native";
import { formatCurrency } from "react-native-format-currency";

const imageWidth = Res.dimen.posterDetailsWidth;
const imageHeight = Res.dimen.posterDetailsHeight;
const ratingSize = 40;

export const MoviesDetails = ({ movie }) => {
    const route = useRouter();

    const posterUrl = TMDBApi.generatePosterUrl(movie.poster_path, imageWidth, imageHeight);

    function onTapPoster() {
        console.log(`Tapped poster for movie: ${movie.title} (${movie.id})`);
        const url = TMDBApi.generatePosterUrl(movie.poster_path, Number.MAX_SAFE_INTEGER, Number.MAX_SAFE_INTEGER);
        route.push({
            pathname: ScreenName.MOVIE_POSTER,
            params: { movieId: movie.id, title: movie.title, url: url },
        });
    }

    function label(text) {
        return (
            <View style={{ alignSelf: "stretch" }}>
                <ThemedText style={styles.label}>{text}</ThemedText>
            </View>
        );
    }

    const tagline = () => {
        if (movie.tagline) {
            return (<ThemedText style={styles.tagline}>{movie.tagline}</ThemedText>);
        } else {
            return null;
        }
    }

    const rating = () => {
        if (movie.vote_average) {
            const rating = (movie.vote_average * 10).toFixed(0);
            return (
                <Row>
                    <Rating rating={rating} radius={ratingSize} fontSize={24} />
                    {label(Res.string.vote_average_label)}
                </Row>
            );
        } else {
            return null;
        }
    }

    const releaseDate = () => {
        if (movie.release_date) {
            const releaseDate = new Date(movie.release_date);
            const text = releaseDate.toLocaleDateString();
            return (
                <Column>
                    {label(Res.string.release_date_label)}
                    <ThemedText style={styles.text}>{text}</ThemedText>
                </Column>
            );
        } else {
            return null;
        }
    }

    const runtime = () => {
        if (movie.runtime) {
            const duration = movie.runtime;
            const hours = Math.floor(duration / 60);
            const minutes = duration % 60;
            return (
                <Column>
                    {label(Res.string.runtime_label)}
                    <ThemedText style={styles.text}>{hours}h {minutes}m</ThemedText>
                </Column>
            );
        } else {
            return null;
        }
    }

    const genres = () => {
        if (movie.genres && movie.genres.length > 0) {
            const genreNames = movie.genres.map(genre => genre.name).join(', ');
            return (
                <Column>
                    {label(Res.string.genres_label)}
                    <ThemedText style={styles.text}>{genreNames}</ThemedText>
                </Column>
            );
        } else {
            return null;
        }
    }

    const budget = () => {
        if (movie.budget) {
            const [budget] = formatCurrency({ amount: movie.budget, code: "USD", });
            return (
                <Column>
                    {label(Res.string.budget_label)}
                    <ThemedText style={styles.text}>{budget}</ThemedText>
                </Column>
            );
        } else {
            return null;
        }
    }

    const revenue = () => {
        if (movie.revenue) {
            const [revenue] = formatCurrency({ amount: movie.revenue, code: "USD", });
            return (
                <Column>
                    {label(Res.string.revenue_label)}
                    <ThemedText style={styles.text}>{revenue}</ThemedText>
                </Column>
            );
        } else {
            return null;
        }
    }

    const overview = () => {
        if (movie.overview) {
            return (
                <Column>
                    {label(Res.string.summary_label)}
                    <ThemedText style={styles.text}>{movie.overview}</ThemedText>
                </Column>
            );
        } else {
            return null;
        }
    }

    return (
        movie.overview ? (
            <ScrollView>
                <ThemedView style={styles.details}>
                    <Row>
                        <Pressable onPress={() => onTapPoster()}>
                            <Image source={{ uri: posterUrl }} style={styles.poster} placeholder={require('@/assets/images/movie.png')} />
                        </Pressable>
                        <Column style={{ flex: 1 }}>
                            <ThemedText style={styles.title}>{movie.title}</ThemedText>
                            {tagline()}
                            {rating()}
                            {releaseDate()}
                            {runtime()}
                            {budget()}
                            {revenue()}
                            {genres()}
                        </Column>
                    </Row>
                    {overview()}
                </ThemedView>
            </ScrollView>
        ) : (
            <ThemedView style={styles.details}>
                <ThemedText>No details found for movie {movie.title}</ThemedText>
            </ThemedView>
        )
    );
}

const styles = StyleSheet.create({
    details: {
        flex: 1,
        flexDirection: "column",
        padding: 8,
    },
    label: {
        flexWrap: "wrap",
        fontSize: 17,
        fontWeight: "bold",
        marginTop: 8,
    },
    poster: {
        borderRadius: Res.dimen.cardRadius,
        marginEnd: 8,
        width: imageWidth,
        height: imageHeight,
    },
    tagline: {
        fontSize: 18,
        paddingBottom: 8,
    },
    text: {
        flexWrap: "wrap",
        fontSize: 16,
    },
    title: {
        flexWrap: "wrap",
        fontSize: 24,
        fontWeight: "bold",
        marginBottom: 16,
    },
    vote: {
        paddingTop: 8,
        width: 100, // 5 * 20
    },
});