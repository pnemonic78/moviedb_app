import CircularProgress from 'react-native-circular-progress-indicator';

export const ratingSize = 25;

export const Rating = ({ rating, style }) => {
    return (
        <CircularProgress
            progressValueColor={'white'}
            style={style}
            circleBackgroundColor={'black'}
            radius={ratingSize}
            maxValue={100}
            value={rating}
            valueSuffix={'%'}
            progressValueFontSize={16}
            valueSuffixStyle={{ fontSize: 10, lineHeight: 12, textAlignVertical: 'top' }}
            showProgressValue={true}
            activeStrokeWidth={5}
            inActiveStrokeOpacity={0.1} />
    );
}
