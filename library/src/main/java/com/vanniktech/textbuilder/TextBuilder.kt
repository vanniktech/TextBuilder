package com.vanniktech.textbuilder

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.DynamicDrawableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.widget.TextView
import androidx.annotation.CheckResult
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class TextBuilder(private val context: Context) {
  private val spannables: MutableList<Spannable?> = ArrayList<Spannable?>()

  @CheckResult fun addColoredTextRes(
    text: String,
    @ColorRes colorRes: Int
  ): TextBuilder {
    return addColoredText(text, ContextCompat.getColor(context, colorRes))
  }

  @CheckResult fun addColoredTextRes(
    @StringRes textRes: Int,
    @ColorRes colorRes: Int
  ): TextBuilder {
    return addColoredText(context.getString(textRes), ContextCompat.getColor(context, colorRes))
  }

  @CheckResult fun addColoredText(
    text: String,
    @ColorInt color: Int
  ): TextBuilder {
    val word: Spannable = SpannableString(text)
    word.setSpan(ForegroundColorSpan(color), 0, word.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    spannables.add(word)
    return this
  }

  @CheckResult fun addColoredText(
    @StringRes textRes: Int,
    @ColorInt color: Int
  ): TextBuilder {
    return addColoredText(context.getString(textRes), color)
  }

  @CheckResult @JvmOverloads fun addText(
    @StringRes stringRes: Int,
    isBold: Boolean = false,
    isItalic: Boolean = false,
    shouldUnderline: Boolean = false,
    @ColorInt textColor: Int? = null,
    onClick: (() -> Unit)? = null,
  ): TextBuilder {
    val word: Spannable = SpannableString(context.getString(stringRes))

    if (isBold) {
      word.setSpan(CustomSpan(isBold, isItalic, shouldUnderline, textColor, onClick), 0, word.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

    spannables.add(word)
    return this
  }

  @CheckResult @JvmOverloads fun addText(
    text: String,
    isBold: Boolean = false,
    isItalic: Boolean = false,
    shouldUnderline: Boolean = false,
    @ColorInt textColor: Int? = null,
    onClick: (() -> Unit)? = null,
  ): TextBuilder {
    val word = SpannableString(text)

    if (isBold) {
      word.setSpan(CustomSpan(isBold, isItalic, shouldUnderline, textColor, onClick), 0, word.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

    spannables.add(word)
    return this
  }

  @CheckResult fun addWhiteSpace(): TextBuilder {
    return addText(" ")
  }

  @CheckResult fun addNewLine(): TextBuilder {
    return addText("\n")
  }

  @CheckResult fun addDrawable(@DrawableRes resDrawable: Int): TextBuilder {
    return addDrawable(resDrawable, DynamicDrawableSpan.ALIGN_BASELINE)
  }

  @CheckResult fun addDrawable(
    @DrawableRes resDrawable: Int,
    verticalAlignment: Int
  ): TextBuilder {
    val spannableString = SpannableString(" ")
    val drawable = ContextCompat.getDrawable(context, resDrawable)
    drawable!!.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight())

    val imageSpan = ImageSpan(drawable, verticalAlignment)
    spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    spannables.add(spannableString)
    return this
  }

  @CheckResult fun addSpannable(spannable: Spannable): TextBuilder {
    spannables.add(spannable)
    return this
  }

  @CheckResult fun addFormableText(formableText: String): FormableText {
    return FormableText(this, formableText)
  }

  @CheckResult fun addFormableText(@StringRes stringRes: Int): FormableText {
    return addFormableText(context.getString(stringRes))
  }

  @JvmOverloads fun into(
    textView: TextView,
    hasClickAction: Boolean = false
  ) {
    set(textView, hasClickAction, false)
  }

  @JvmOverloads fun appendInto(
    textView: TextView,
    hasClickAction: Boolean = false
  ) {
    set(textView, hasClickAction, true)
  }

  private fun set(
    textView: TextView,
    hasClickAction: Boolean,
    append: Boolean
  ) {
    textView.highlightColor = Color.TRANSPARENT

    if (hasClickAction) {
      textView.movementMethod = LinkMovementMethod.getInstance()
    }

    if (!append) {
      textView.text = null
    }

    for (spannable in spannables) {
      textView.append(spannable)
    }
  }
}
