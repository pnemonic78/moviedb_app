import React from 'react';
import { ScrollView, StyleSheet, Text } from 'react-native';
import MoviesSlider from "./MoviesSlider";

const MoviesAllPage = ({ navigation }) => {

    const Section = (props) => {
        return <Text style={styles.headline5}>{ props.label }</Text>;
    }

    return (
        <ScrollView style={styles.scroller}>
            <Section label="Popular"/>
            <MoviesSlider/>
            <Section label="Now Playing"/>
            <MoviesSlider/>
            <Section label="Upcoming"/>
            <MoviesSlider/>
            <Section label="Top Rated"/>
            <MoviesSlider/>
        </ScrollView>
    );
};

const styles = StyleSheet.create({
    headline5: {
        fontSize: 25,
        padding: 8,
    },
    scroller: {
        padding: 8,
    },
  });

export default MoviesAllPage;