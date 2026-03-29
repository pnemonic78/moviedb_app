import { MovieDetails } from "@/tmdb_api/model/MovieDetails";
import { PayloadAction } from "@reduxjs/toolkit";

type MovieDetailsAction = PayloadAction<MovieDetails>
export default MovieDetailsAction