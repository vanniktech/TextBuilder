package com.vanniktech.textbuilder;

final class Preconditions {
  static <T> T checkNotNull(final T value, final String message) {
    if (value == null) {
      throw new NullPointerException(message);
    }
    return value;
  }

  private Preconditions() {
    throw new AssertionError("No instances.");
  }
}
