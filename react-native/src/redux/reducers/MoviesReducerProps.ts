import { DispatchProp } from "react-redux";
import { MoviesAction } from "../actions/MoviesAction";

export default interface MoviesReducerProps extends DispatchProp<MoviesAction> {
}