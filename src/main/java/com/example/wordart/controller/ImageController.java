package com.example.wordart.controller;

import com.example.wordart.gen.Generator;
import com.example.wordart.gen.ImageMagickGenerator;
import com.example.wordart.gen.Job;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;

@RestController
public class ImageController {

    @RequestMapping(value = "/image", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImageAsByteArray(@RequestParam("text") String text) throws IOException {

        Generator generator = new ImageMagickGenerator();

        EnumSet<Job.Effect> effects = EnumSet.of(Job.Effect.SHADOW_REFLECT);

        Job job = new Job(Job.Format.PNG, text, effects, Job.Colour.BLACK, Job.Font.FREE_MONO);

        InputStream image = generator.generateImage(job);

        return IOUtils.toByteArray(image);
    }
}
