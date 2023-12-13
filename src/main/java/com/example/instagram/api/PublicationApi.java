package com.example.instagram.api;

import com.example.instagram.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/publication")
@RequiredArgsConstructor
public class PublicationApi {
    private final PublicationService publicationService;

    @PostMapping("/save/publication")
    public String save(@RequestParam String email, @RequestParam List<String> list, @RequestParam String description) {
        publicationService.postedPublication(email, list, description);
        return "succesfully";
    }
}