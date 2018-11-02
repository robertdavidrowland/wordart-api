package com.example.wordart.gen;

import java.util.EnumSet;

public class Job {

    private final Format format;
    private String text;
    private EnumSet<Effect> effects;
    private Colour fontColour;
    private Font font;

    /**
     * Create a new job specification
     *
     * @param format     file output format for the result.
     * @param text       the text that is to be rendered
     * @param effects    the set of effects that you want applied to your output
     *                   text
     * @param fontColour the colour to use when rendering the font
     * @param font       the font face to use when rendering the font
     */
    public Job(Format format, String text, EnumSet<Effect> effects, Colour fontColour, Font font) {
        this.format = format;
        this.text = text;
        this.effects = effects;
        this.fontColour = fontColour;
        this.font = font;
    }

    Format getFormat() {
        return format;
    }

    String getText() {
        return text;
    }

    EnumSet<Effect> getEffects() {
        return effects;
    }

    Colour getFontColour() {
        return fontColour;
    }

    Font getFont() {
        return font;
    }

    /**
     * Supported output formats
     */
    public enum Format {
        PNG,
        JPEG,
        TIFF,
        ASCII
    }

    /**
     * Font effects for rendered output
     */
    public enum Effect {
        SHADOW_HARD,
        SHADOW_REFLECT,
        GRADIENT,
        OUTLINE
    }

    /**
     * Supported colours
     */
    public enum Colour {
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
    public enum Font {
        FREE_MONO("FreeMono");

        private String fontName;

        Font(String fontName) {
            this.fontName = fontName;
        }

        public String getFontName() {
            return fontName;
        }
    }
}


