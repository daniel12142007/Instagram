package com.example.instagram.repository;

import com.example.instagram.model.Image;
import com.example.instagram.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query("select i.id from Image i where i.publication = :publication")
    List<Long> findAllImageByPublication(@Param(value = "publication") Publication publication);
}