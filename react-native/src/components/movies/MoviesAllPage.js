import React from 'react';
import { ScrollView, StyleSheet } from 'react-native';
import MoviesAllSection from './MoviesAllSection';
import MoviesSlider from "./MoviesSlider";

const styles = StyleSheet.create({
    scroller: {
        padding: 8,
    },
});

const MoviesAllPage = ({ navigation }) => {

    return (
        <ScrollView style={styles.scroller}>
            <MoviesAllSection label="Popular"/>
            <MoviesSlider/>
            <MoviesAllSection label="Now Playing"/>
            <MoviesSlider/>
            <MoviesAllSection label="Upcoming"/>
            <MoviesSlider/>
            <MoviesAllSection label="Top Rated"/>
            <MoviesSlider/>
        </ScrollView>
    );
};

export default MoviesAllPage;