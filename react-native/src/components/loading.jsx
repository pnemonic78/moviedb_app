import { Res } from "@/res/Res";
import { ActivityIndicator, View } from "react-native";

export const Loading = () => {
    return (
        <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
            <ActivityIndicator size="large" color={Res.color.primary} />
        </View>
    );
}