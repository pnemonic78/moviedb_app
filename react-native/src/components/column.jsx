import { View } from "react-native";

export const Column = ({ children, style }) => {
    return (
        <View style={{ ...style, flexDirection: "column" }} >
            {children}
        </View>
    );
}
