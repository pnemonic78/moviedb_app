import React, { useEffect, useState } from 'react';
import { Text, View, FlatList, Image, Pressable } from 'react-native';
import axios from 'axios';

const MoviesPage = ({ navigation }) => {
    const [movies, setMovies] = useState([]);

    useEffect(() => {
        setMovies(["A", "B", "C"]);
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