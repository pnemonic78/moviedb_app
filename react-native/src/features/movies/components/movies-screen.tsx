import AppState from '@/app/redux/AppState';
import { ThemedView } from '@/components/themed-view';
import { MoviesCarousel } from '@/features/movies/components/movies-carousel';
import { showAsList } from "@/features/movies/redux/MoviesReducer";
import { Movie } from "@/tmdb_api/model/Movie";
import { MaterialCommunityIcons } from '@expo/vector-icons';
import { NativeStackHeaderItemProps } from "@react-navigation/native-stack";
import { Stack } from 'expo-router';
import { GestureResponderEvent, Pressable } from "react-native";
import { useDispatch, useSelector } from "react-redux";

export interface MoviesScreenParams {
    title: string,
    movies: Movie[],
    onMoviePress: (movie: Movie) => void
}

export const MoviesScreen = (params: MoviesScreenParams) => {

    const dispatch = useDispatch();
    const isShowAsList = useSelector((state: AppState) => state.movies.showAsList);

    function toggleGrid(event: GestureResponderEvent) {
        dispatch(showAsList(!isShowAsList))
    }

    function actionBar(props: NativeStackHeaderItemProps) {
        const iconName = isShowAsList ? "view-grid" : "list-box"

        return (
            <Pressable onPress={toggleGrid}>
                <MaterialCommunityIcons name={iconName} size={32} />
            </Pressable>
        );
    }

    return (
        <>
            <Stack.Screen options={{ headerRight: actionBar }} />
            <ThemedView style={{ flex: 1, flexDirection: 'column' }}>
                <MoviesCarousel
                    movies={params.movies}
                    onPress={params.onMoviePress}
                />
            </ThemedView>
        </>
    );
}