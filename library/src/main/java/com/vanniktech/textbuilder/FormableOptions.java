package com.vanniktech.textbuilder;

import androidx.annotation.CheckResult;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
import static com.vanniktech.textbuilder.Preconditions.checkNotNull;

public final class FormableOptions {
  @NonNull private final FormableText formableText;
  @NonNull private final String textToFormat;

  @Nullable private ClickableAction clickableAction;
  private boolean isBold;
  private boolean isItalic;
  private boolean shouldUnderline;
  @ColorInt @Nullable private Integer textColor;

  FormableOptions(@NonNull final FormableText formableText, @NonNull final String textToFormat) {
    this.formableText = formableText;
    this.textToFormat = textToFormat;
  }

  @CheckResult public FormableOptions bold() {
    isBold = true;
    return this;
  }

  @CheckResult public FormableOptions italic() {
    isItalic = true;
    return this;
  }

  @CheckResult public FormableOptions underline() {
    shouldUnderline = true;
    return this;
  }

  @CheckResult public FormableOptions textColor(@ColorInt final int color) {
    textColor = color;
    return this;
  }

  @CheckResult public FormableOptions clickable(@NonNull final ClickableAction action) {
    formableText.hasClickAction = true;
    clickableAction = checkNotNull(action, "action == null");
    return this;
  }

  @CheckResult public FormableText done() {
    final int start = formableText.text.indexOf(textToFormat);

    final CustomSpan customSpan = new CustomSpan(isBold, isItalic, shouldUnderline, textColor, clickableAction);
    formableText.spannableStringBuilder.setSpan(customSpan, start, start + textToFormat.length(), SPAN_EXCLUSIVE_EXCLUSIVE);

    return formableText;
  }

  public interface ClickableAction {
    void onClick();
  }
}
