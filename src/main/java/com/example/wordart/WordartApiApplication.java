package com.example.wordart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableWebMvc
public class WordartApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WordartApiApplication.class, args);
    }

    public class WebConfig extends WebMvcConfigurationSupport {
        @Override
        public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
            converters.add(byteArrayHttpMessageConverter());
        }

        @Bean
        public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
            ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
            arrayHttpMessageConverter.setSupportedMediaTypes(getSupportedMediaTypes());
            return arrayHttpMessageConverter;
        }

        private List<MediaType> getSupportedMediaTypes() {
            List<MediaType> list = new ArrayList<>();
            list.add(MediaType.IMAGE_JPEG);
            list.add(MediaType.IMAGE_PNG);
            list.add(MediaType.APPLICATION_OCTET_STREAM);
            return list;
        }
    }

}
