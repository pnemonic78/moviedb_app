import { MoviesScreen } from '@/features/movies/components/movies-screen';
import { Res } from '@/res/Res';

const MoviesPopularScreen = () => {
    return (
        <MoviesScreen title={Res.string.popular_movies} />
    );
}
export default MoviesPopularScreen;