import { AnimatedSplashOverlay } from '@/components/animated-icon';
import { Res } from '@/res/Res';
import { DarkTheme, DefaultTheme, ThemeProvider } from '@react-navigation/native';
import { Stack } from 'expo-router';
import React from 'react';
import { useColorScheme } from 'react-native';
import { Provider } from "react-redux";
import { store } from "./redux/AppStore";

export default function AppLayout() {
  const colorScheme = useColorScheme();
  return (
    <ThemeProvider value={colorScheme === 'dark' ? DarkTheme : DefaultTheme}>
      <AnimatedSplashOverlay />
      <Provider store={store}>
        <Stack>
          <Stack.Screen name="index" options={{ title: Res.string.title }} />
          <Stack.Screen name="(movies)/movie/[movieId]" options={{ headerShown: false }} />
          <Stack.Screen name="(movies)/movie/now-playing" options={{ title: Res.string.now_playing_movies }} />
          <Stack.Screen name="(movies)/movie/popular" options={{ title: Res.string.popular_movies }} />
          <Stack.Screen name="(movies)/movie/top-rated" options={{ title: Res.string.top_rated_movies }} />
          <Stack.Screen name="(movies)/movie/upcoming" options={{ title: Res.string.upcoming_movies }} />
        </Stack>
      </Provider>
    </ThemeProvider>
  );
}
