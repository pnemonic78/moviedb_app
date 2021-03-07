import React, { useEffect, useState } from 'react';
import { Text, View, FlatList, Image, Pressable } from 'react-native';
import axios from 'axios';

const MoviesPage = ({ navigation }) => {
    const [movies, setMovies] = useState([]);

    useEffect(() => {
        let data = require('./data.json');
        setMovies(data.results);
    }, []);

    const renderItem = ({ item }) => {
        return (
            <Text>{item.toString()}</Text>
        );
    }

    return (
        <View>
            <FlatList
                data={ movies }
                renderItem={ renderItem }
                keyExtractor={ (item, i) => i.toString() }
                />
        </View>
    );
};

export default MoviesPage;