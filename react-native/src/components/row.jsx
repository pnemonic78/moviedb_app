import { View } from "react-native";

export const Row = ({ style, children }) => {
    return (
        <View style={{ ...style, flexDirection: "row" }} >
            {children}
        </View>
    );
}
