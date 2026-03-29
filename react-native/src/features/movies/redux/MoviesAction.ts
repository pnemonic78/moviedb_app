import { Movie } from "@/tmdb_api/model/Movie";
import { PayloadAction } from "@reduxjs/toolkit";

type MoviesAction = PayloadAction<Movie[]>
export default MoviesAction
