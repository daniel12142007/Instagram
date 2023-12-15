package com.example.instagram.api;

import com.example.instagram.dto.response.PublicationResponse;
import com.example.instagram.service.PublicationService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/publication")
@RequiredArgsConstructor
public class PublicationApi {
    private final PublicationService publicationService;

    @PostMapping("/save/publication")
    public PublicationResponse savePublication(@RequestParam String email,
                                               @RequestParam List<String> image,
                                               @RequestParam String description) {
        return publicationService.postedPublication(email, image, description);
    }

    @GetMapping("/find/by/{id}")
    public PublicationResponse findByIdPublication(@PathVariable Long id) {
        return publicationService.findByIdPublication(id);
    }
}