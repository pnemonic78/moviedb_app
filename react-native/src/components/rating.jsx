import CircularProgress from 'react-native-circular-progress-indicator';

export const ratingSize = 25;

export const Rating = ({ rating, radius, fontSize, style }) => {
    const valueFontSize = fontSize ?? 16;

    return (
        <CircularProgress
            progressValueColor={'white'}
            style={style}
            circleBackgroundColor={'black'}
            radius={radius ?? ratingSize}
            maxValue={100}
            value={rating}
            valueSuffix={'%'}
            progressValueFontSize={valueFontSize}
            valueSuffixStyle={{ fontSize: (valueFontSize * 0.5), lineHeight: valueFontSize, textAlignVertical: 'top' }}
            showProgressValue={true}
            activeStrokeWidth={5}
            inActiveStrokeOpacity={0.1} />
    );
}
