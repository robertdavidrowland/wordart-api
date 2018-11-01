package com.example.wordart.controller;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class ImageController {

    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/image-byte-array", method = RequestMethod.GET)
    public byte[] getImageAsByteArray() throws IOException {
        InputStream in = servletContext.getResourceAsStream("resources/foo.png");
        return IOUtils.toByteArray(in);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() throws IOException {
        InputStream in = servletContext.getResourceAsStream("/WEB-INF/foo.png");

        log.debug("in: {}", in);

        if (in == null) {
            return "WTF 2: " + servletContext.getContextPath();
        }
        return String.valueOf(in.available());
    }
}
