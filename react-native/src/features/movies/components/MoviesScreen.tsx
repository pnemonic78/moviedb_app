import AppState from '@/app/redux/AppState';
import ScreenName from '@/app/ScreenName';
import { ThemedView } from '@/components/themed-view';
import { showAsList } from "@/features/movies/redux/MoviesReducer";
import { Movie } from "@/tmdb_api/model/Movie";
import { MaterialCommunityIcons } from '@expo/vector-icons';
import { NativeStackHeaderItemProps } from "@react-navigation/native-stack";
import { Stack, useRouter } from 'expo-router';
import React from 'react';
import { GestureResponderEvent, Pressable } from "react-native";
import { useDispatch, useSelector } from "react-redux";
import { MoviesGridPage } from './MoviesGridPage';
import { MoviesListPage } from './MoviesListPage';

export interface MoviesScreenParams {
    title: string
    movies: Movie[]
}

export const MoviesScreen = (params: MoviesScreenParams) => {

    const router = useRouter();
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

    /// Navigates to the movie details.
    function navigateToMovie(movie: Movie) {
        console.log(`Navigating to movie details: ${movie.title} (${movie.id})`);
        router.push({
            pathname: ScreenName.MOVIE_DETAILS,
            params: { id: movie.id, title: movie.title },
        });
    }

    function onTapMovie(movie: Movie) {
        navigateToMovie(movie);
    }

    return (
        <>
            <Stack.Screen options={{ headerRight: actionBar }} />
            <ThemedView style={{ flex: 1, flexDirection: 'column' }}>
                {isShowAsList ?
                    <MoviesListPage movies={params.movies} onPress={onTapMovie} />
                    :
                    <MoviesGridPage movies={params.movies} onPress={onTapMovie} />}
            </ThemedView>
        </>
    );
}