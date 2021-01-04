import React from 'react';
import { ScrollView, StyleSheet, Text, View } from 'react-native';

const MoviesAllPage = ({ navigation }) => {

    const _buildTitle = (title) => {
        return <Text style={styles.headline5}>{ title }</Text>;
    }

    const _buildPopularList = () => {
        return <View></View>;
    }

    const _buildNowPlayingList = () => {
        return <View></View>;
    }

    const _buildUpcomingList = () => {
        return <View></View>;
    }

    const _buildTopRatedList = () => {
        return <View></View>;
    }

    const popularTitle = _buildTitle("Popular");

    const popularList = _buildPopularList();

    const nowPlayingTitle = _buildTitle("Now Playing");

    const nowPlayingList = _buildNowPlayingList();

    const upcomingTitle = _buildTitle("Upcoming");

    const upcomingList = _buildUpcomingList();

    const topRatedTitle = _buildTitle("Top Rated");

    const topRatedList = _buildTopRatedList();

    return (
        <ScrollView>
            { popularTitle }
            { popularList }
            { nowPlayingTitle }
            { nowPlayingList }
            { upcomingTitle }
            { upcomingList }
            { topRatedTitle }
            { topRatedList }
        </ScrollView>
    );
};

const styles = StyleSheet.create({
    headline5: {
      fontSize: 25,
    }
});
  
export default MoviesAllPage;