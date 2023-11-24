package com.example.instagram.api;

import com.example.instagram.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ImageApi {
    private final ImageService imageService;

//    @PostMapping(value = "send/image/direct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public String sendImage(@RequestPart MultipartFile file) throws IOException {
//        return imageService.saveImage(file);
//    }
}