import React from 'react';
import { NavigationContainer } from '@react-navigation/native'
import { createStackNavigator } from '@react-navigation/stack';
import { Text, View } from 'react-native';

import AppStyles from './AppStyles';
import MoviesAllPage from '../movies/MoviesAllPage';
import MoviesPage from '../movies/MoviesPage';

const App: () => React$Node = () => {
  const stack = createStackNavigator();

  return (
    <NavigationContainer>
      <stack.Navigator>
        <stack.Screen name="MoviesAllPage" component={ MoviesAllPage } />
        <stack.Screen name="MoviesPage" component={ MoviesPage } />
      </stack.Navigator>
    </NavigationContainer>
  );
};

export default App;
