TextBuilder
===========

Provides a Class with a builder pattern for building beautiful text super easily. Internally it uses the Spannable API.

```java
new TextBuilder(this) // Context
    .addText(R.string.some_text)
    .addWhiteSpace()
    .addColoredTextRes("in green", R.color.green)
    .addWhiteSpace()
    .addColoredText("and blue", Color.BLUE)
    .addWhiteSpace()
    .into(textView);
```

Also certain parts of a text can be formatted:

```java
new TextBuilder(this) // Context
    .addFormableText("Terms of use and privacy terms")
    .format("Terms of use")
      .textColor(Color.RED)
      .italic()
      .bold()
      .clickable(() -> Log.d(TAG, "Clicked on Terms of Use"))
    .done()
    .format("privacy terms")
      .underline()
      .bold()
      .clickable(() -> Log.d(TAG, "Clicked on Privacy Terms"))
    .done()
    .into(textView);
```

Displaying images is also not a problem:

```java
new TextBuilder(this)
    .addDrawable(R.drawable.ic_done_black_18dp)
    .addWhiteSpace()
    .addText("Shopping")
    .addNewLine()
    .addText("Cleaning")
    .into(textView);
```

For now only the basic things are supported and some features may be lacking. I'm open for any suggestions or new APIs.

# Setup

**build.gradle**

```groovy
compile 'com.vanniktech:textbuilder:0.3.0'
compile 'com.vanniktech:textbuilder:0.3.0-SNAPSHOT'
```

Modules are located on [Maven Central](https://oss.sonatype.org/#nexus-search;quick~textbuilder).

# Proguard

No configuration needed.

# License

Copyright (C) 2017 Vanniktech - Niklas Baudy

Licensed under the Apache License, Version 2.0
