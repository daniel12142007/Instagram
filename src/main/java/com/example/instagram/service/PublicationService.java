package com.example.instagram.service;

import com.example.instagram.model.Image;
import com.example.instagram.model.Publication;
import com.example.instagram.model.User;
import com.example.instagram.repository.PublicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PublicationService {
    private final CrudService crudService;
    private final PublicationRepository publicationRepository;

    public void postedPublication(String myEmail,
                                  List<String> images,
                                  String description) {
        User user = crudService.findByEmail(myEmail);
        Publication publication = Publication.builder()
                .block(false)
                .user(user)
                .description(description)
                .build();
//        List<Image> list = new ArrayList<>();
//        images.forEach(a -> {
//            Image image = new Image();
//            image.setData(a.getBytes());
//            image.setData(a.getBytes());
//            image.setFileName(a.getOriginalFilename());
//            image.setFileName("image");
//            image.setPublication(publication);
//            list.add(image);
//        });
//        publication.setImages(list);
        publicationRepository.save(publication);
    }

//    public ResponseEntity<byte[]> findByImageForGroup(Long id, String email, Long groupId) {
//        Image image = imageRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Image not found with id: " + id));
//        if (!isUserInGroup(email, groupId)) {
//            throw new IsUserNotPartOfGroup();
//        }
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG);
//
//        return new ResponseEntity<>(image.getData(), headers, HttpStatus.OK);
//    }
//
}