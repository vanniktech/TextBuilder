package com.vanniktech.textbuilder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
import static android.text.Spanned.SPAN_INCLUSIVE_EXCLUSIVE;
import static android.text.style.DynamicDrawableSpan.ALIGN_BASELINE;
import static com.vanniktech.textbuilder.Preconditions.checkNotNull;

public final class TextBuilder {
  @NonNull private final Context context;

  @NonNull private final List<Spannable> spannables = new ArrayList<>();

  public TextBuilder(@NonNull final Context context) {
    this.context = checkNotNull(context, "context == null");
  }

  @CheckResult public TextBuilder addColoredTextRes(@NonNull final String text, @ColorRes final int color) {
    return addColoredText(text, ContextCompat.getColor(context, color));
  }

  @CheckResult public TextBuilder addColoredText(@NonNull final String text, @ColorInt final int color) {
    final Spannable word = new SpannableString(checkNotNull(text, "text == null"));
    word.setSpan(new ForegroundColorSpan(color), 0, word.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
    spannables.add(word);
    return this;
  }

  @CheckResult public TextBuilder addText(@StringRes final int stringRes) {
    spannables.add(new SpannableString(context.getString(stringRes)));
    return this;
  }

  @CheckResult public TextBuilder addText(@NonNull final String text) {
    spannables.add(new SpannableString(checkNotNull(text, "text == null")));
    return this;
  }

  @CheckResult public TextBuilder addWhiteSpace() {
    return addText(" ");
  }

  @CheckResult public TextBuilder addNewLine() {
    return addText("\n");
  }

  @CheckResult public TextBuilder addDrawable(@DrawableRes final int resDrawable) {
    return addDrawable(resDrawable, ALIGN_BASELINE);
  }

  @CheckResult public TextBuilder addDrawable(@DrawableRes final int resDrawable, final int verticalAlignment) {
    final SpannableString spannableString = new SpannableString(" ");
    final Drawable drawable = ContextCompat.getDrawable(context, resDrawable);
    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

    final ImageSpan imageSpan = new ImageSpan(drawable, verticalAlignment);
    spannableString.setSpan(imageSpan, 0, 1, SPAN_INCLUSIVE_EXCLUSIVE);
    spannables.add(spannableString);
    return this;
  }

  @CheckResult public TextBuilder addSpannable(@NonNull final Spannable spannable) {
    spannables.add(checkNotNull(spannable, "spannable == null"));
    return this;
  }

  @CheckResult public FormableText addFormableText(@NonNull final String formableText) {
    return new FormableText(this, checkNotNull(formableText, "formableText == null"));
  }

  public void into(@NonNull final TextView textView) {
    checkNotNull(textView, "textView == null");

    for (final Spannable spannable : spannables) {
      textView.append(spannable);
    }
  }
}
