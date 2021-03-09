import React, { Component, ReactNode } from 'react';
import { Image, ImageBackground, ImageProps, ImageSourcePropType, ImageStyle } from 'react-native';

export interface LoadingImageProps extends ImageProps {
    style?: ImageStyle,
}

export default class LoadingImage extends Component<LoadingImageProps> {
    constructor(props: LoadingImageProps) {
        super(props);
    }

    render(): ReactNode {
        return <ImageBackground
            source={this.props.defaultSource as ImageSourcePropType}
            style={this.props.style}>
            <Image source={this.props.source} style={this.props.style} />
        </ImageBackground>;
    }
}