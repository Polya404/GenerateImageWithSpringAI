package org.application.openaiapi;

import org.application.openaiapi.services.ImageService;
import org.springframework.ai.image.Image;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class OpenAIAPIApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OpenAIAPIApplication.class, args);
        ImageService imageService = context.getBean(ImageService.class);
        Image image = imageService.createWithStabilityAIImage("Meat");
        imageService.saveImage(image, "test", "png");
        System.out.println("End");
    }

}
