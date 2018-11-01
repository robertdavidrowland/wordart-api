package com.example.wordart.controller;

import com.example.wordart.gen.Generator;
import com.example.wordart.gen.ImageMagickGenerator;
import com.example.wordart.gen.Job;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.EnumSet;

@RestController
public class ImageController {

    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageAsByteArray(
            @RequestParam("text") String text,
            @RequestParam(value = "format", required = false, defaultValue = "PNG") Job.Format format,
            @RequestParam(value = "effect", required = false, defaultValue = "SHADOW_REFLECT") Job.Effect[] effects,
            @RequestParam(value = "colour", required = false, defaultValue = "BLACK") Job.Colour colour,
            @RequestParam(value = "font", required = false, defaultValue = "FREE_MONO") Job.Font font) throws IOException {

        log.info("text: {}", text);
        log.info("format: {}", format);
        log.info("effects: {}, {}, {}", (Object[]) effects);
        log.info("colour: {}", colour);
        log.info("font: {}", font);

        HttpHeaders responseHeaders = new HttpHeaders();
        switch (format) {
            case PNG:
                responseHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE);
                break;
            case JPEG:
                responseHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE);
                break;
            case TIFF:
                responseHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_GIF_VALUE);
                break;
            default:
                responseHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        }

        Generator generator = new ImageMagickGenerator();

        Job job = new Job(format, text, EnumSet.copyOf(Arrays.asList(effects)), colour, font);

        InputStream image = generator.generateImage(job);

        return new ResponseEntity<>(IOUtils.toByteArray(image), responseHeaders, HttpStatus.OK);

    }
}
