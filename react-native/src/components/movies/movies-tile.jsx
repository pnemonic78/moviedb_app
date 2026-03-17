import { Card } from '@/components/card';
import { ThemedText } from '@/components/themed-text';
import { Res } from '@/res/Res';
import TMDBApi from '@/tmdb_api/TMDBApi';
import { Image } from 'expo-image';
import { Pressable, StyleSheet, View } from "react-native";

export const MovieTile = ({ movie, onPress }) => {
    const url = TMDBApi.generatePosterUrl(movie.poster_path, Res.dimen.posterListWidth, Res.dimen.posterListHeight);

    return (
        <Card style={{ width: Res.dimen.posterListWidth }}>
            <Pressable onPress={(event) => onPress(movie)} style={{ flex: 1 }} >
                <View style={styles.container} >
                    <Image
                        source={{ uri: url }}
                        placeholder={require('@/assets/images/movie.png')}
                        style={styles.image}
                    />
                    <ThemedText style={styles.text} numberOfLines={2}>
                        {movie.title}
                    </ThemedText>
                </View>
            </Pressable>
        </Card>
    );
}

const styles = StyleSheet.create({
    container: {
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
    text: {
        paddingHorizontal: 8,
    }
});