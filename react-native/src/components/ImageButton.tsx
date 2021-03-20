import React, { Component, ReactNode } from 'react'
import { GestureResponderEvent, Image, ImageProps, Pressable } from 'react-native'

export interface ImageButtonProps extends ImageProps {
    onPress?: null | ((event: GestureResponderEvent) => void)
}

export class ImageButton extends Component<ImageButtonProps> {
    constructor(props: ImageButtonProps) {
        super(props)
    }

    render(): ReactNode {
        return <Pressable
            onPress={this.props.onPress?.bind(this)}>
            <Image {...this.props} />
        </Pressable>
    }
}