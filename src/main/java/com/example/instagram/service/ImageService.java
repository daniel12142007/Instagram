package com.example.instagram.service;

import com.example.instagram.dto.response.ChatOneResponse;
import com.example.instagram.model.Groups;
import com.example.instagram.model.Image;
import com.example.instagram.model.Notification;
import com.example.instagram.model.User;
import com.example.instagram.model.enums.FormatMessage;
import com.example.instagram.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public ResponseEntity<byte[]> findByImage(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found with id: " + id));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(image.getData(), headers, HttpStatus.OK);
    }

    public String saveImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setData(file.getBytes());
        image.setData(file.getBytes());
        image.setFileName(file.getOriginalFilename());
        imageRepository.save(image);
        return "Ok";
    }
}
