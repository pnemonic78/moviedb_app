import MaterialIcons from "@expo/vector-icons/MaterialIcons";
import { Pressable, StyleSheet, Text } from "react-native";

const styles = StyleSheet.create({
    row: {
        display: 'flex',
        flexDirection: 'row',
        alignItems: 'center',
        padding: 8,
    },
    headline5: {
        fontSize: 22,
        width: '90%',
    },
})

export const MoviesSection = ({ label, onPress }) => {
    return (
        <Pressable style={styles.row} onPress={onPress}>
            <Text style={styles.headline5}>{label}</Text>
            <MaterialIcons name="chevron-right" size={32} color="black" />
        </Pressable>
    );
}