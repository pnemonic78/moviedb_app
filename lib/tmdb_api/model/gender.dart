enum Gender { unknown, female, male }

Gender Gender_fromJson(int value) {
  return (value != null) ? Gender.values[value] : Gender.unknown;
}
