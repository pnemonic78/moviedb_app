import { MoviesScreen } from '@/features/movies/components/movies-screen';
import { Res } from '@/res/Res';

const MoviesNowPlayingScreen = () => {
    return (
        <MoviesScreen title={Res.string.now_playing_movies} />
    );
}
export default MoviesNowPlayingScreen;