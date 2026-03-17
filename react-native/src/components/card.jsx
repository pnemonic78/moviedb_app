import { ThemedView } from '@/components/themed-view';
import { Res } from '@/res/Res';

export const Card = ({ children, style }) => {
    return (
        <ThemedView style={[styles.card, style]} >
            {children}
        </ThemedView>
    );
}

const styles = {
    card: {
        borderRadius: Res.dimen.cardRadius,
        elevation: 4,
        margin: 4,
        shadowColor: 'black',
        shadowOffset: { width: 0, height: 2 },
        shadowOpacity: 0.25,
        shadowRadius: 4,
    }
}