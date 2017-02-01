package com.vanniktech.textbuilder;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

import static com.vanniktech.textbuilder.Preconditions.checkNotNull;

public final class FormableText {
  @NonNull final String text;
  @NonNull private final TextBuilder textBuilder;
  final SpannableStringBuilder spannableStringBuilder;

  FormableText(@NonNull final TextBuilder textBuilder, @NonNull final String text) {
    this.text = text;
    this.textBuilder = textBuilder;

    spannableStringBuilder = new SpannableStringBuilder(text);
  }

  @CheckResult public FormableOptions format(@NonNull final String textToFormat) {
    return new FormableOptions(this, checkNotNull(textToFormat, "textToFormat == null"));
  }

  public void into(final TextView textView) {
    textBuilder.addSpannable(spannableStringBuilder).into(textView);
  }
}
