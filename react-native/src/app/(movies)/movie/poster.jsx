import { Poster } from "@/components/movies/movie-poster";
import { ThemedView } from "@/components/themed-view";
import { Stack, useLocalSearchParams } from "expo-router";
import { StyleSheet } from "react-native";

const PosterPage = () => {
    const params = useLocalSearchParams();
    const movieId = parseInt(params.movieId);
    let title = params.title;
    let url = params.url;

    return (
        <ThemedView style={styles.page}>
            <Stack.Screen options={{ title: title }} />
            <Poster url={url} />
        </ThemedView>
    );
}
export default PosterPage;

const styles = StyleSheet.create({
    page: {
        flex: 1,
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
    },
    title: {
        fontSize: 24,
        fontWeight: "bold",
    },
});
