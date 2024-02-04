package com.example.instagram.api;

import com.example.instagram.dto.response.PublicationResponse;
import com.example.instagram.service.PublicationService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/publication")
@RequiredArgsConstructor
public class PublicationApi {
    private final PublicationService publicationService;

    @PostMapping("/save/publication")
    public PublicationResponse savePublication(@RequestParam List<String> image,
                                               @RequestParam String description) {
        String myEmail = SecurityContextHolder.getContext().getAuthentication() != null
                ? SecurityContextHolder.getContext().getAuthentication().getName()
                : null;
        return publicationService.postedPublication(myEmail, image, description);
    }

    @GetMapping("/find/by/{id}")
    public PublicationResponse findByIdPublication(@PathVariable Long id) {
        return publicationService.findByIdPublication(id);
    }
}