import React, { Component, ReactNode } from 'react'
import { Image, ImageBackground, ImageProps, ImageSourcePropType, ImageStyle, StyleProp } from 'react-native'

export interface LoadingImageProps extends ImageProps {
    style?: ImageStyle
}

export class LoadingImage extends Component<LoadingImageProps> {
    constructor(props: LoadingImageProps) {
        super(props)
    }

    render(): ReactNode {
        let props = { ...this.props }
        let width = props.width ?? props.style?.width
        let height = props.height ?? props.style?.height

        return <ImageBackground
            {...props}
            source={props.defaultSource as ImageSourcePropType}
            style={[props.style, { height: height, width: width }]}
        >
            <Image
                {...props}
                style={[props.style, { height: height, width: width }]}
            />
        </ImageBackground>
    }
}