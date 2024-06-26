package com.vanniktech.textbuilder;

import android.text.SpannableStringBuilder;
import android.widget.TextView;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;

import static com.vanniktech.textbuilder.Preconditions.checkNotNull;

public final class FormableText {
  @NonNull final String text;
  @NonNull private final TextBuilder textBuilder;
  final SpannableStringBuilder spannableStringBuilder;
  boolean hasClickAction;

  FormableText(@NonNull final TextBuilder textBuilder, @NonNull final String text) {
    this.text = text;
    this.textBuilder = textBuilder;

    spannableStringBuilder = new SpannableStringBuilder(text);
  }

  @CheckResult public FormableOptions format(@NonNull final String textToFormat) {
    return new FormableOptions(this, checkNotNull(textToFormat, "textToFormat == null"));
  }

  @CheckResult public FormableOptions formatEverything() {
    return new FormableOptions(this, text);
  }

  public void into(final TextView textView) {
    textBuilder.addSpannable(spannableStringBuilder).into(textView, hasClickAction);
  }

  public void appendInto(final TextView textView) {
    textBuilder.addSpannable(spannableStringBuilder).appendInto(textView, hasClickAction);
  }
}
