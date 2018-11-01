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
    public void noText() throws Exception {
        this.mockMvc.perform(
                get("/image"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void defaultImage() throws Exception {
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
    public void format() throws Exception {
        MvcResult result = this.mockMvc.perform(
                get("/image?text=foo&format=JPEG"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("image/jpeg"))
                .andReturn();

        byte[] contentBytes = result.getResponse().getContentAsByteArray();
        Long contentLength = (long) contentBytes.length;

        assertThat(contentLength, equalTo(2096L));
    }

    @Test
    public void effect() throws Exception {
        MvcResult result = this.mockMvc.perform(
                get("/image?text=foo&effect=OUTLINE"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("image/png"))
                .andReturn();

        byte[] contentBytes = result.getResponse().getContentAsByteArray();
        Long contentLength = (long) contentBytes.length;

        assertThat(contentLength, equalTo(1478L));
    }

    @Test
    public void multipleEffects() throws Exception {
        MvcResult result = this.mockMvc.perform(
                get("/image?text=foo&effect=OUTLINE&effect=GRADIENT"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("image/png"))
                .andReturn();

        byte[] contentBytes = result.getResponse().getContentAsByteArray();
        Long contentLength = (long) contentBytes.length;

        assertThat(contentLength, equalTo(2784L));
    }

    @Test
    public void colour() throws Exception {
        MvcResult result = this.mockMvc.perform(
                get("/image?text=foo&colour=RED"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("image/png"))
                .andReturn();

        byte[] contentBytes = result.getResponse().getContentAsByteArray();
        Long contentLength = (long) contentBytes.length;

        assertThat(contentLength, equalTo(4890L));
    }

    @Test
    public void badFont() throws Exception {
        this.mockMvc.perform(
                get("/image?text=foo&font=ARIAL"))
                .andExpect(status().isBadRequest());
    }
}
