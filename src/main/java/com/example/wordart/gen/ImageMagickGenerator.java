package com.example.wordart.gen;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * A {@link Generator} implementation that uses the image magick library's
 * convert command (expected to be on the system's path) to render images from
 * text.
 */
public class ImageMagickGenerator implements Generator {

    private static final String OS = System.getProperty("os.name").toLowerCase();

    private final Properties properties;

    public ImageMagickGenerator() {
        this.properties = loadProps();
    }

    @Override
    public InputStream generateImage(Job job) throws IOException {

        final int pointsize = 48;

        List<String> command = new ArrayList<>();

        if (OS.contains("win")) {
            command.add(properties.getProperty("imagemagick.windows"));
        } else {
            command.add(properties.getProperty("imagemagick.linux"));
        }

        command.add("-size");
        command.add("400x100");
        command.add("xc:white");
        command.add("-font");
        command.add(job.getFont().getFontName());

        command.add("-pointsize");
        command.add(Integer.toString(pointsize));

        final int x = 25;
        final int y = 25 + pointsize;

        if (job.getEffects().contains(Job.Effect.SHADOW_HARD)) {
            int shadowoffset = pointsize / 12;

            command.add("-fill");
            command.add(Job.Colour.GREY.toString().toLowerCase());
            command.add("-annotate");
            command.add(String.format("+%d+%d", x - shadowoffset, y - shadowoffset));
            command.add(job.getText());
        }

        if (job.getEffects().contains(Job.Effect.SHADOW_REFLECT)) {

            command.add("-fill");
            command.add(Job.Colour.GREY.toString().toLowerCase());
            command.add("-annotate");
            command.add(String.format("0x45+%d+%d", x, y));
            command.add(job.getText());
        }

        if (job.getEffects().contains(Job.Effect.OUTLINE)) {
            command.add("-stroke");
            command.add("black");
        }

        if (job.getEffects().contains(Job.Effect.GRADIENT)) {
            command.add("-tile");
            command.add(String.format("gradient:%s-%s", Job.Colour.WHITE.toString().toLowerCase(), job.getFontColour().toString().toLowerCase()));
        } else {
            command.add("-fill");
            command.add(job.getFontColour().toString().toLowerCase());
        }
        command.add("-annotate");
        command.add(String.format("+%d+%d", x, y));
        command.add(job.getText());

        command.add(String.format("%s:-", job.getFormat().toString().toLowerCase()));

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        return process.getInputStream();
    }

    private Properties loadProps() {

        Properties properties = new Properties();

        try (InputStream input = getClass().getResourceAsStream("/application.properties")) {
            properties.load(input);
        } catch (IOException e) {
            properties.setProperty("imagemagick.linux", "convert");
            properties.setProperty("imagemagick.windows", "C:\\Program Files\\ImageMagick\\magick.exe");
        }

        return properties;
    }
}

