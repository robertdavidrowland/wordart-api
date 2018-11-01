package com.example.wordart.gen;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ImageMagickGeneratorTest {

    private static final Logger log = LoggerFactory.getLogger(ImageMagickGeneratorTest.class);

    @Test
    public void generateImage() throws IOException {

        log.info("generateImage");

        String text = "foo";

        Generator generator = new ImageMagickGenerator();

        EnumSet<Job.Effect> effects = EnumSet.of(Job.Effect.SHADOW_REFLECT);

        Job job = new Job(Job.Format.PNG, text, effects, Job.Colour.BLACK, Job.Font.FREE_MONO);

        InputStream generatedImage = generator.generateImage(job);

        byte[] contentBytes = IOUtils.toByteArray(generatedImage);
        Long contentLength = (long) contentBytes.length;

        log.debug("contentLength: {}", contentLength);

        assertThat(contentLength, equalTo(2774L));
    }
}
