import { Loading } from "@/components/loading";
import { MovieTile } from "@/features/movies/components/MovieTile";
import { Res } from "@/res/Res";
import { FlatList, View } from "react-native";
import { useSelector } from "react-redux";

export const MoviesCarousel = ({ movies, onPress }) => {
    const loading = useSelector((state) => state.movies.loading);

    return (
        (loading || !movies.length) ? (
            <View style={{ height: Res.dimen.posterListHeight }}>
                <Loading />
            </View>
        ) : (
            <FlatList
                data={movies}
                horizontal={true}
                renderItem={({ item }) => <MovieTile movie={item} onPress={onPress} />}
                keyExtractor={(item, index) => item.id}
                ItemSeparatorComponent={() => <View style={{ width: 4 }} />}
            />
        )
    );
}