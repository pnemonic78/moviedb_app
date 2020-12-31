import React from 'react';
import { Text, View } from 'react-native';

const MoviesPage = ({ navigation }) => {
    const now = new Date();

    return (
        <View>
            <Text>Now: {now.toLocaleString()}</Text>
        </View>
    );
};

export default MoviesPage;