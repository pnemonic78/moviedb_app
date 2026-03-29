import { Person } from "@/tmdb_api/model/Person";
import { PayloadAction } from "@reduxjs/toolkit";

type PersonDetailsAction = PayloadAction<Person>
export default PersonDetailsAction
