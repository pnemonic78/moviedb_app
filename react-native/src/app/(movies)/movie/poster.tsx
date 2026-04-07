import { ThemedView } from "@/components/themed-view";
import { Poster } from "@/features/movies/components/Poster";
import { Stack, useLocalSearchParams } from "expo-router";
import { StyleSheet } from "react-native";

const PosterPage = () => {
    const params = useLocalSearchParams();
    const title = params.title;
    const url = params.url;

    return (
        <ThemedView style={styles.page}>
            <Stack.Screen.Title>{title}</Stack.Screen.Title>
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
