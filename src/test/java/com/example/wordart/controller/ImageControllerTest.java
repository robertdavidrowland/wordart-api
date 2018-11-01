package com.example.wordart.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ImageController.class)
public class ImageControllerTest {

    private static final Logger log = LoggerFactory.getLogger(ImageControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void goodRequest_shouldReturnImage() throws Exception {
        MvcResult result = this.mockMvc.perform(
                get("/image?text=foo"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("image/png"))
                .andReturn();

        byte[] contentBytes = result.getResponse().getContentAsByteArray();
        Long contentLength = (long) contentBytes.length;

        assertThat(contentLength, equalTo(2774L));
    }

    @Test
    public void noText_shouldReturn400() throws Exception {
        this.mockMvc.perform(
                get("/image"))
                .andExpect(status().isBadRequest());
    }
}
