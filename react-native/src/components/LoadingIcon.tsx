import React, { Component, ReactNode } from 'react'
import { Image, ImageProps, ImageStyle, View } from 'react-native'
import { Icon } from 'react-native-elements'
import { IconResource } from '../res/icons'

export interface LoadingIconProps extends ImageProps {
    style?: ImageStyle
    placeholder: IconResource
}

export class LoadingIcon extends Component<LoadingIconProps> {
    constructor(props: LoadingIconProps) {
        super(props)
    }

    render(): ReactNode {
        let props = { ...this.props }
        let width = (props.width ?? props.style?.width) as number
        let height = (props.height ?? props.style?.height) as number
        let size = Math.min(width, height)

        let image = <Image
            {...props}
            style={[props.style, {
                height: height,
                width: width,
                position: 'absolute',
            }]}
        />

        return <View>
            <Icon
                name={props.placeholder.name}
                type={props.placeholder.type}
                size={size}
                containerStyle={[props.style, {
                    height: height,
                    width: width,
                    alignContent: 'center',
                    justifyContent: 'center',
                }]}
            />
            {image}
        </View>
    }
}