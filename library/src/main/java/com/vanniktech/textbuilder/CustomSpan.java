package com.vanniktech.textbuilder;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

final class CustomSpan extends ClickableSpan {
  private final boolean isBold;
  private final boolean isItalic;
  private final boolean shouldUnderline;
  @Nullable @ColorInt private final Integer textColor;
  @Nullable private final FormableOptions.ClickableAction clickableAction;

  CustomSpan(
      final boolean isBold,
      final boolean isItalic,
      final boolean shouldUnderline,
      @Nullable final Integer textColor,
      @Nullable final FormableOptions.ClickableAction clickableAction) {
    this.isBold = isBold;
    this.isItalic = isItalic;
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
    if (isItalic && isBold) {
      ds.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC));
    } else if (isBold) {
      ds.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
    } else if (isItalic) {
      ds.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
    }

    ds.setUnderlineText(shouldUnderline);

    if (textColor != null) {
      ds.setColor(textColor);
    }
  }
}
