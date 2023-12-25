package com.example.instagram.service;

import com.example.instagram.dto.response.PublicationResponse;
import com.example.instagram.mapper.view.ViewPublicationResponse;
import com.example.instagram.model.Image;
import com.example.instagram.model.Publication;
import com.example.instagram.model.User;
import com.example.instagram.repository.PublicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PublicationService {
    private final CrudService crudService;
    private final PublicationRepository publicationRepository;
    private final ViewPublicationResponse viewPublicationResponse;

    public PublicationResponse postedPublication(String myEmail,
                                                 List<String> images,
                                                 String description) {
        User user = crudService.findByEmail(myEmail);
        Publication publication = Publication.builder()
                .block(false)
                .user(user)
                .description(description)
                .build();
        List<Image> list = new ArrayList<>();
        images.forEach(a -> {
            Image image = new Image();
//            image.setData(a.getBytes());
//            image.setData(a.getBytes());
//            image.setFileName(a.getOriginalFilename());
            image.setFileName("image");
            image.setPublication(publication);
            list.add(image);
        });
        publication.setImages(list);
        publicationRepository.save(publication);
        return viewPublicationResponse.mapPublication(publication);
    }

    public PublicationResponse findByIdPublication(Long id) {
        Publication publication = publicationRepository.findById(id).orElseThrow(RuntimeException::new);
        return viewPublicationResponse.mapPublication(publication);
    }
//    public
}