package com.vanniktech.textbuilder.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;
import com.vanniktech.textbuilder.FormableOptions;
import com.vanniktech.textbuilder.TextBuilder;

public class MainActivity extends AppCompatActivity {
  static final String TAG = "MainActivity";

  @Override protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    final TextView textView1 = (TextView) findViewById(R.id.activityMainTextView1);
    new TextBuilder(this)
        .addText(R.string.some_text)
        .addWhiteSpace()
        .addColoredTextRes("in green", R.color.green)
        .addWhiteSpace()
        .addColoredText("and blue", Color.BLUE)
        .addWhiteSpace()
        .into(textView1);

    final TextView textView2 = (TextView) findViewById(R.id.activityMainTextView2);
    new TextBuilder(this)
        .addFormableText("Terms of use and privacy terms")
        .format("Terms of use")
          .textColor(Color.RED)
          .bold()
          .clickable(new LoggingAction("Clicked on Terms of Use"))
        .done()
        .format("privacy terms")
          .underline()
          .bold()
          .clickable(new LoggingAction("Clicked on Privacy Terms"))
        .done()
        .into(textView2);

    textView2.setMovementMethod(LinkMovementMethod.getInstance());

    final TextView textView3 = (TextView) findViewById(R.id.activityMainTextView3);
    new TextBuilder(this)
        .addDrawable(R.drawable.ic_done_black_18dp)
        .addWhiteSpace()
        .addText("Shopping")
        .addNewLine()
        .addText("Cleaning")
        .into(textView3);
  }

  static class LoggingAction implements FormableOptions.ClickableAction {
    final String text;

    LoggingAction(final String text) {
      this.text = text;
    }

    @Override public void onClick() {
      Log.d(TAG, text);
    }
  }
}
