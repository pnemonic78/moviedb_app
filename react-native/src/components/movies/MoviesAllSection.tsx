import React, { Component } from 'react';
import { StyleSheet, Text } from 'react-native';

const styleSheet = StyleSheet.create({
    headline5: {
        fontSize: 25,
        padding: 8,
    },
});

interface MoviesAllSectionProps {
    label: string,
}

export default class MoviesAllSection extends Component<MoviesAllSectionProps> {
    constructor(props: MoviesAllSectionProps) {
        super(props);
    }

    render() {
        return <Text style={styleSheet.headline5}>{this.props.label}</Text>;
    }
}
