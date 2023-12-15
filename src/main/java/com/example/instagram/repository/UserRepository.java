package com.example.instagram.repository;

import com.example.instagram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

// TODO: 3
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT count(u) FROM User u JOIN u.likes p WHERE p.id = :publicationId")
    int findCountByPublicationId(@Param(value = "publicationId") Long publicationId);
}