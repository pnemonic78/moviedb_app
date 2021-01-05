import React from 'react';
import { NavigationContainer } from '@react-navigation/native'
import { createStackNavigator } from '@react-navigation/stack';
import { Text, View } from 'react-native';

import AppStyles from './AppStyles';
import MoviesAllPage from '../movies/MoviesAllPage';
import MoviesPage from '../movies/MoviesPage';

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
            title: "The Movies Database Demo",
            headerStyle: styles.header,
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
