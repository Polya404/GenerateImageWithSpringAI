package org.application.openaiapi.services;

import org.springframework.ai.image.Image;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.stabilityai.StabilityAiImageClient;
import org.springframework.ai.stabilityai.api.StabilityAiImageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.io.*;

@Service
public class ImageService {

    @Autowired
    private StabilityAiImageClient stabilityAiImageClient;

    public Image createWithStabilityAIImage(String prompt) {
        ImageResponse response = stabilityAiImageClient.call(
                new ImagePrompt(prompt,
                        StabilityAiImageOptions.builder()
                                .withStylePreset("cinematic")
                                .withN(4)
                                .withHeight(1024)
                                .withWidth(1024).build())

        );
        return response.getResult().getOutput();
    }

    public void saveImage(Image image, String fileName, String format) {
        String b64Json = image.getB64Json();
        byte[] data = DatatypeConverter.parseBase64Binary(b64Json);
        String resourcesDir = "src/main/resources/";
        String path = resourcesDir + fileName + "." + format;
        File file = new File(path);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
