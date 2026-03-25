import { MoviesScreen } from '@/components/movies/movies-screen';
import { Res } from '@/res/Res';

const MoviesTopRatedScreen = () => {

    return (
        <MoviesScreen title={Res.string.top_rated_movies} />
    );
}
export default MoviesTopRatedScreen;