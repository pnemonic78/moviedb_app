import React from 'react'
import { NavigationContainer } from '@react-navigation/native'
import { createStackNavigator } from '@react-navigation/stack'
import { Provider } from 'react-redux'

import AppStyles from './AppStyles'
import { MoviesAllPage } from '../movies/MoviesAllPage'
import { MovieDetailsHomePage } from '../movie_details/MovieDetailsHomePage'
import { NowPlayingPage } from '../movies/NowPlayingPage'
import { UpcomingPage } from '../movies/UpcomingPage'
import { TopRatedPage } from '../movies/TopRatedPage'
import { PopularPage } from '../movies/PopularPage'
import { MoviePosterPage } from '../movies/MoviePosterPage'
import { ScreenName } from './ScreenName'
import store from '../../redux/reducers/AppReducer'
import R from '../../res/R'

const App: () => React.ReactElement = () => {
  const stack = createStackNavigator()
  const styles = AppStyles

  return (
    <Provider store={store}>
      <NavigationContainer>
        <stack.Navigator>
          <stack.Screen
            name={ScreenName.MOVIES_ALL}
            component={MoviesAllPage}
            options={{
              headerStyle: styles.header,
              headerTitleStyle: styles.headerTitleStyle,
              title: R.string.title,
            }} />
          <stack.Screen
            name={ScreenName.MOVIES_NOW_PLAYING}
            component={NowPlayingPage}
            options={{
              headerStyle: styles.header,
              headerTitleStyle: styles.headerTitleStyle,
              title: R.string.now_playing,
            }} />
          <stack.Screen
            name={ScreenName.MOVIES_POPULAR}
            component={PopularPage}
            options={{
              headerStyle: styles.header,
              headerTitleStyle: styles.headerTitleStyle,
              title: R.string.popular,
            }} />
          <stack.Screen
            name={ScreenName.MOVIES_TOP_RATED}
            component={TopRatedPage}
            options={{
              headerStyle: styles.header,
              headerTitleStyle: styles.headerTitleStyle,
              title: R.string.top_rated,
            }} />
          <stack.Screen
            name={ScreenName.MOVIES_UPCOMING}
            component={UpcomingPage}
            options={{
              headerStyle: styles.header,
              headerTitleStyle: styles.headerTitleStyle,
              title: R.string.upcoming,
            }} />
          <stack.Screen
            name={ScreenName.MOVIE_DETAILS}
            component={MovieDetailsHomePage}
            options={{
              headerShown: false,
            }} />
          <stack.Screen
            name={ScreenName.MOVIE_POSTER}
            component={MoviePosterPage}
            options={{
              headerStyle: styles.header,
              headerTitleStyle: styles.headerTitleStyle,
              title: R.string.title,
            }} />
        </stack.Navigator>
      </NavigationContainer>
    </Provider>
  )
}

export default App