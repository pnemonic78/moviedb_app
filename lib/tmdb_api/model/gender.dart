enum Gender {
  unknown,
  female,
  male
}

Gender Gender_fromJson(int value) {
  return Gender.values[value];
}
