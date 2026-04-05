import { MoviesScreen } from '@/features/movies/components/movies-screen';
import { Res } from '@/res/Res';

const MoviesUpcomingScreen = () => {
    return (
        <MoviesScreen title={Res.string.upcoming_movies} />
    );
}
export default MoviesUpcomingScreen;