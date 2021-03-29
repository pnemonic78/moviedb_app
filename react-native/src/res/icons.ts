import { IconType } from "react-native-elements"

export interface IconResource {
  name: string,
  type: IconType
}

const icons = {
  camera: { name: "camera", type: "material" },
  error: { name: "error-outline", type: "material" },
  face: { name: "face", type: "material" },
  grid: { name: "grid-view", type: "material" },
  image: { name: "image", type: "material" },
  list: { name: "list", type: "material" },
  movie: { name: "movie", type: "material" },
  person: { name: "person-outline", type: "material" },
  play: { name: "play", type: "material" },
  play_circle: { name: "play-circle-outline", type: "material" },
  stop: { name: "stop", type: "material" },
  stop_circle: { name: "stop-circle", type: "material" },

  homepage: { name: "home", type: "simple-line-icon" },
  facebook: { name: "social-facebook", type: "simple-line-icon" },
  imdb: { name: "storage", type: "material" },
  instagram: { name: "social-instagram", type: "simple-line-icon" },
  twitter: { name: "social-twitter", type: "simple-line-icon" },
}
export default icons