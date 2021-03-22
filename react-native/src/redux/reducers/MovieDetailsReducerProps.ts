import { DispatchProp } from "react-redux";
import { MovieDetailsAction } from "../actions/MovieDetailsAction";

export default interface MovieDetailsReducerProps extends DispatchProp<MovieDetailsAction> {
}