import React, { Component } from 'react'
import { GestureResponderEvent, Pressable, StyleSheet, Text } from 'react-native'

const styleSheet = StyleSheet.create({
    headline5: {
        fontSize: 25,
        padding: 8,
    },
})

interface MoviesAllSectionProps {
    label: string
    onPress?: null | ((event: GestureResponderEvent) => void)
}

export default class MoviesAllSection extends Component<MoviesAllSectionProps> {
    constructor(props: MoviesAllSectionProps) {
        super(props)
    }

    render() {
        return <Pressable onPress={this.props.onPress?.bind(this)}>
            <Text style={styleSheet.headline5}>{this.props.label}</Text>
        </Pressable>
    }
}
