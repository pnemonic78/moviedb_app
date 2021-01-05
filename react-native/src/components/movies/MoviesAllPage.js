import React from 'react';
import { ScrollView, StyleSheet, Text, View } from 'react-native';

const MoviesAllPage = ({ navigation }) => {

    const Title = (props) => {
        return <Text style={styles.headline5}>{ props.title }</Text>;
    }

    function _buildPopularList() {
        return <Text>PopularList</Text>;
    }

    function _buildNowPlayingList() {
        return <Text>NowPlayingList</Text>;
    }

    function _buildUpcomingList() {
        return <Text>UpcomingList</Text>;
    }

    function _buildTopRatedList() {
        return <Text>TopRatedList</Text>;
    }

    const popularList = _buildPopularList();

    const nowPlayingList = _buildNowPlayingList();

    const upcomingList = _buildUpcomingList();

    const topRatedList = _buildTopRatedList();

    return (
        <ScrollView>
            <Title title="Popular"/>
            { popularList }
            <Title title="Now Playing"/>
            { nowPlayingList }
            <Title title="Upcoming"/>
            { upcomingList }
            <Title title="Top Rated"/>
            { topRatedList }
        </ScrollView>
    );
};

const styles = StyleSheet.create({
    headline5: {
      fontSize: 25,
      padding: 8,
    }
});
  
export default MoviesAllPage;