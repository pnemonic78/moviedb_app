import React from 'react';
import { NavigationContainer } from '@react-navigation/native'
import { createStackNavigator } from '@react-navigation/stack';

import AppStyles from './AppStyles';
import MoviesAllPage from '../movies/MoviesAllPage';
import MoviesPage from '../movies/MoviesPage';
import R from '../../res/R';

const App: () => React$Node = () => {
  const stack = createStackNavigator();
  const styles = AppStyles;

  return (
    <NavigationContainer>
      <stack.Navigator>
        <stack.Screen
          name="MoviesAllPage"
          component={ MoviesAllPage }
          options={{
            title: R.strings.title,
            headerStyle: styles.header,
            headerTitleStyle: styles.headerTitleStyle,
          }} />
        <stack.Screen
          name="MoviesPage"
          component={ MoviesPage }
          options={{
            headerStyle: styles.header,
          }} />
      </stack.Navigator>
    </NavigationContainer>
  );
};

export default App;
