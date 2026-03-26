import { Card } from '@/components/card';
import { Rating, ratingSize } from '@/components/rating';
import { ThemedText } from '@/components/themed-text';
import { Res } from '@/res/Res';
import TMDBApi from '@/tmdb_api/TMDBApi';
import { Image } from 'expo-image';
import { Pressable, StyleSheet, View } from "react-native";

export const MovieTile = ({ movie, onPress }) => {
    const url = TMDBApi.generatePosterUrl(movie.poster_path, Res.dimen.posterListWidth, Res.dimen.posterListHeight);
    const rating = movie.vote_average ? (movie.vote_average * 10).toFixed(0) : null;
    const releaseDate = movie.release_date ? new Date(movie.release_date) : null;

    return (
        <Pressable onPress={(event) => onPress(movie)} style={{ width: Res.dimen.posterListWidth }}>
            <Card>
                <View style={styles.container}>
                    <View style={styles.column}>
                        <Image
                            source={{ uri: url }}
                            placeholder={require('@/assets/images/movie.png')}
                            style={styles.image}
                        />
                        <View style={{ height: ratingSize }} />
                        <ThemedText style={styles.title} numberOfLines={2}>
                            {movie.title + "\n\n"}
                        </ThemedText>
                        <ThemedText style={styles.date} numberOfLines={1}>
                            {releaseDate ? releaseDate.toLocaleDateString() : ""}
                        </ThemedText>
                    </View>
                    <View style={styles.ratingContainer}>
                        <Rating rating={rating} />
                    </View>
                </View>
            </Card>
        </Pressable>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
    column: {
        flex: 1,
        flexDirection: 'column',
    },
    image: {
        height: Res.dimen.posterListHeight,
        width: Res.dimen.posterListWidth,
        borderTopLeftRadius: Res.dimen.cardRadius,
        borderTopRightRadius: Res.dimen.cardRadius,
        // Ensure the corners clip the image
        overflow: 'hidden',
    },
    title: {
        paddingHorizontal: 8,
        fontWeight: 'bold',
    },
    date: {
        paddingHorizontal: 8,
    },
    ratingContainer: {
        position: 'absolute',
        top: Res.dimen.posterListHeight - ratingSize,
        left: 8,
    },
});