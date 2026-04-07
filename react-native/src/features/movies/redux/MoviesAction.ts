import { Movie } from "@/tmdb_api/model/Movie";
import { PayloadAction } from "@reduxjs/toolkit";

export type MoviesAction = PayloadAction<Movie[]>

export type ToggleAction = PayloadAction<boolean>
