import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

import AppStyles from './AppStyles';
import { MoviesAllPage } from '../movies/MoviesAllPage';
import R from '../../res/R';
import { MovieDetailsHomePage } from '../movie_details/MovieDetailsHomePage';
import { NowPlayingPage } from '../movies/NowPlayingPage';
import { UpcomingPage } from '../movies/UpcomingPage';
import { TopRatedPage } from '../movies/TopRatedPage';
import { PopularPage } from '../movies/PopularPage';
import { MoviesPage } from '../movies/MoviesPage';

const App: () => React.ReactElement = () => {
  const stack = createStackNavigator();
  const styles = AppStyles;

  return (
    <NavigationContainer>
      <stack.Navigator>
        <stack.Screen
          name="MoviesAllPage"
          component={MoviesAllPage}
          options={{
            headerStyle: styles.header,
            headerTitleStyle: styles.headerTitleStyle,
            title: R.strings.title,
          }} />
        <stack.Screen
          name="NowPlayingPage"
          component={NowPlayingPage}
          options={{
            headerStyle: styles.header,
            headerTitleStyle: styles.headerTitleStyle,
            title: R.strings.now_playing,
          }} />
        <stack.Screen
          name="PopularPage"
          component={PopularPage}
          options={{
            headerStyle: styles.header,
            headerTitleStyle: styles.headerTitleStyle,
            title: R.strings.popular,
          }} />
        <stack.Screen
          name="TopRatedPage"
          component={TopRatedPage}
          options={{
            headerStyle: styles.header,
            headerTitleStyle: styles.headerTitleStyle,
            title: R.strings.top_rated,
          }} />
        <stack.Screen
          name="UpcomingPage"
          component={UpcomingPage}
          options={{
            headerStyle: styles.header,
            headerTitleStyle: styles.headerTitleStyle,
            title: R.strings.upcoming,
          }} />
        <stack.Screen
          name="MovieDetails"
          component={MovieDetailsHomePage}
          options={{
            headerStyle: styles.header,
            headerTitleStyle: styles.headerTitleStyle,
            title: R.strings.title,
          }} />
      </stack.Navigator>
    </NavigationContainer>
  );
}

export default App;