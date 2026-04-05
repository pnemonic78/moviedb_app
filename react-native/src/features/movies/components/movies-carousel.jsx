import { Loading } from "@/components/loading";
import { MovieTile } from "@/features/movies/components/movies-tile";
import { Res } from "@/res/Res";
import { FlatList, View } from "react-native";

export const MoviesCarousel = ({ movies, onPress }) => {
    return (
        movies?.length > 0 ? (
            <FlatList
                data={movies}
                horizontal={true}
                renderItem={({ item }) => <MovieTile movie={item} onPress={onPress} />}
                keyExtractor={(item, index) => item.id}
                ItemSeparatorComponent={() => <View style={{ width: 4 }} />}
            />
        ) : (
            <View style={{ height: Res.dimen.posterListHeight }}>
                <Loading />
            </View>
        )
    );
}