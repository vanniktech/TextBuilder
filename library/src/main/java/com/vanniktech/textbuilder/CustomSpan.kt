package com.vanniktech.textbuilder

import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import androidx.annotation.ColorInt
import com.vanniktech.textbuilder.FormableOptions.ClickableAction

internal class CustomSpan(
  private val isBold: Boolean,
  private val isItalic: Boolean,
  private val shouldUnderline: Boolean,
  @field:ColorInt private val textColor: Int?,
  private val clickableAction: ClickableAction?
) : ClickableSpan() {
  override fun onClick(widget: View) {
    clickableAction?.onClick()
  }

  override fun updateDrawState(ds: TextPaint) {
    if (isItalic && isBold) {
      ds.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC))
    } else if (isBold) {
      ds.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD))
    } else if (isItalic) {
      ds.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC))
    }

    ds.isUnderlineText = shouldUnderline

    if (textColor != null) {
      ds.setColor(textColor)
    }
  }
}
