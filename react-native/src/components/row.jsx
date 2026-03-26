import { View } from "react-native";

export const Row = ({ children, style }) => {
    return (
        <View style={{ ...style, flexDirection: "row" }} >
            {children}
        </View>
    );
}
