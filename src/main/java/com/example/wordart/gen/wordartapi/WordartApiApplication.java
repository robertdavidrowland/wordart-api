package com.example.wordart.gen.wordartapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class WordartApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordartApiApplication.class, args);
	}

//	public class WebConfig extends WebMvcConfigurationSupport {
//		@Override
//		public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//			converters.add(byteArrayHttpMessageConverter());
//		}
//
//		@Bean
//		public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
//			ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
//			arrayHttpMessageConverter.setSupportedMediaTypes(getSupportedMediaTypes());
//			return arrayHttpMessageConverter;
//		}
//
//		private List<MediaType> getSupportedMediaTypes() {
//			List<MediaType> list = new ArrayList<MediaType>();
//			list.add(MediaType.IMAGE_JPEG);
//			list.add(MediaType.IMAGE_PNG);
//			list.add(MediaType.APPLICATION_OCTET_STREAM);
//			return list;
//		}
//	}

}
