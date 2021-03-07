import React, { Component } from 'react';
import { StyleSheet, Text } from 'react-native';

const styles = StyleSheet.create({
    headline5: {
        fontSize: 25,
        padding: 8,
    },
});

export default class MoviesAllSection extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return <Text style={styles.headline5}>{this.props.label}</Text>;
    }
}
