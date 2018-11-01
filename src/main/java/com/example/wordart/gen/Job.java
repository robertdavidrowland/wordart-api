package com.example.wordart.gen;

import java.util.EnumSet;

public class Job {

  /**
   * Supported output formats
   */
  enum Format {
    PNG,
    JPEG,
    TIFF,
    ASCII
  }

  /**
   * Font effects for rendered output
   */
  enum Effect {
    SHADOW_HARD,
    SHADOW_REFLECT,
    GRADIENT,
    OUTLINE
  }

  /**
   * Supported colours
   */
  enum Colour {
    RED,
    GREEN,
    BLUE,
    BLACK,
    GREY,
    WHITE
  }

  /**
   * Support fonts for word art
   */
  enum Font {
    FREE_MONO("FreeMono");

    Font(String fontName) {
      this.fontName = fontName;
    }

    private String fontName;

    public String getFontName() {
      return fontName;
    }
  }

  private final Format format;
  private String text;
  private EnumSet<Effect> effects;
  private Colour fontColour;
  private Font font;

  /**
   * Create a new job specification
   * @param format file output format for the result.
   * @param text the text that is to be rendered
   * @param effects the set of effects that you want applied to your output
   * text
   * @param fontColour the colour to use when rendering the font
   * @param font the font face to use when rendering the font
   */
  public Job(Format format, String text, EnumSet<Effect> effects, Colour fontColour, Font font) {
    this.format = format;
    this.text = text;
    this.effects = effects;
    this.fontColour = fontColour;
    this.font = font;
  }


  public Format getFormat() {
    return format;
  }

  public String getText() {
    return text;
  }

  public EnumSet<Effect> getEffects() {
    return effects;
  }

  public Colour getFontColour() {
    return fontColour;
  }

  public Font getFont() {
    return font;
  }
}


