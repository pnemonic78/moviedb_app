import React from 'react';
import { ScrollView, StyleSheet } from 'react-native';
import MoviesAllSection from './MoviesAllSection';
import MoviesSlider from "./MoviesSlider";
import R from '../../res/R';

const styles = StyleSheet.create({
    scroller: {
        padding: 8,
    },
});

const MoviesAllPage = ({ navigation }) => {

    return (
        <ScrollView style={styles.scroller}>
            <MoviesAllSection label={ R.strings.popular }/>
            <MoviesSlider/>
            <MoviesAllSection label={R.strings.now_playing}/>
            <MoviesSlider/>
            <MoviesAllSection label={R.strings.upcoming}/>
            <MoviesSlider/>
            <MoviesAllSection label={R.strings.top_rated}/>
            <MoviesSlider/>
        </ScrollView>
    );
};

export default MoviesAllPage;