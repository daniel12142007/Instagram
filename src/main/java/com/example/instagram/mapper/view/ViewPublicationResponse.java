package com.example.instagram.mapper.view;

import com.example.instagram.dto.response.PublicationResponse;
import com.example.instagram.model.Publication;
import com.example.instagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ViewPublicationResponse {
    private final UserRepository userRepository;

    public PublicationResponse mapPublication(Publication publication) {
        LocalDateTime localDateTime = publication.getDataCreated();
        LocalDate localDate = LocalDate.of(
                localDateTime.getYear(),
                localDateTime.getMonth(),
                localDateTime.getDayOfMonth()
        );
        if (publication.getUser() == null) throw new NullPointerException();
        String email = publication.getUser().getEmail();
        String fullName = publication.getUser().getFullName();
        return PublicationResponse.builder()
                .email(email)
                .date(localDate)
                .fullName(fullName)
                .id(publication.getId())
                .countLikes(userRepository.findCountByPublicationId(publication.getId()))
                .build();
    }

    public List<PublicationResponse> mapPublication(List<Publication> publications) {
        List<PublicationResponse> list = new ArrayList<>();
        publications.forEach(a -> {
            list.add(mapPublication(a));
        });
        return list;
    }
}