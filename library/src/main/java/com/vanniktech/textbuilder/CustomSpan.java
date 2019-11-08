package com.vanniktech.textbuilder;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

final class CustomSpan extends ClickableSpan {
  private final boolean isBold;
  private final boolean shouldUnderline;
  @Nullable @ColorInt private final Integer textColor;
  @Nullable private final FormableOptions.ClickableAction clickableAction;

  CustomSpan(final boolean isBold, final boolean shouldUnderline,
      @Nullable final Integer textColor,
      @Nullable final FormableOptions.ClickableAction clickableAction) {
    this.isBold = isBold;
    this.shouldUnderline = shouldUnderline;
    this.textColor = textColor;
    this.clickableAction = clickableAction;
  }

  @Override public void onClick(final View widget) {
    if (clickableAction != null) {
      clickableAction.onClick();
    }
  }

  @Override public void updateDrawState(final TextPaint ds) {
    if (isBold) {
      ds.setTypeface(Typeface.DEFAULT_BOLD);
    }

    ds.setUnderlineText(shouldUnderline);

    if (textColor != null) {
      ds.setColor(textColor);
    }
  }
}
