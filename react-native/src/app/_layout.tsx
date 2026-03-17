import { AnimatedSplashOverlay } from '@/components/animated-icon';
import { MoviesAllScreen } from '@/screens/movies-all';
import { DarkTheme, DefaultTheme, ThemeProvider } from '@react-navigation/native';
import React from 'react';
import { useColorScheme } from 'react-native';

export default function AppLayout() {
  const colorScheme = useColorScheme();
  return (
    <ThemeProvider value={colorScheme === 'dark' ? DarkTheme : DefaultTheme}>
      <AnimatedSplashOverlay />
      <MoviesAllScreen />
    </ThemeProvider>
  );
}
