import React, { Component } from 'react';
import { Image, ImageBackground, Text } from 'react-native';

export default class LoadingImage extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return <ImageBackground
            source={this.props.defaultSource}
            style={this.props.style}
        >
            <Image source={this.props.source} style={this.props.style} />
        </ImageBackground>;
    }
}