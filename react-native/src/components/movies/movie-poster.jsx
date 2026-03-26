import { StyleSheet, useWindowDimensions } from "react-native";
import { Gesture, GestureDetector, GestureHandlerRootView } from "react-native-gesture-handler";
import Animated, { useAnimatedStyle, useSharedValue } from 'react-native-reanimated';

export const Poster = ({ url }) => {
    const windowSize = useWindowDimensions();
    const screenWidth = windowSize.width;
    const screenHeight = windowSize.height;
    const maxX = screenWidth - 100;
    const maxY = screenHeight - 100;
    const minX = -screenWidth + 100;
    const minY = -screenHeight + 100;

    const offset = useSharedValue({ x: 0, y: 0 });
    const scale = useSharedValue(1);
    const offsetBefore = useSharedValue({ x: 0, y: 0 }); // `useSharedValue` to persist rendering.
    const scaleBefore = useSharedValue(1); // `useSharedValue` to persist rendering.

    const animatedStyle = useAnimatedStyle(() => ({
        transform: [
            { translateX: offset.value.x },
            { translateY: offset.value.y },
            { scale: scale.value },
        ],
    }));

    const panGesture = Gesture.Pan()
        .onStart(() => {
            offsetBefore.value = offset.value;
        })
        .onUpdate((event) => {
            offset.value = {
                x: Math.min(Math.max(minX, offsetBefore.value.x + event.translationX), maxX),
                y: Math.min(Math.max(minY, offsetBefore.value.y + event.translationY), maxY)
            };
        });
    const pinchGesture = Gesture.Pinch()
        .onStart(() => {
            scaleBefore.value = scale.value;
        })
        .onUpdate((event) => {
            scale.value = Math.min(Math.max(scaleBefore.value * event.scale, 0.5), 6);
        });
    const composed = Gesture.Simultaneous(pinchGesture, panGesture);

    return (
        <GestureHandlerRootView style={styles.container}>
            <GestureDetector gesture={composed}>
                <Animated.Image
                    source={{ uri: url }}
                    resizeMode="contain"
                    style={[styles.poster, animatedStyle]}
                />
            </GestureDetector>
        </GestureHandlerRootView>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignSelf: 'stretch',
    },
    poster: {
        flex: 1,
    },
});
