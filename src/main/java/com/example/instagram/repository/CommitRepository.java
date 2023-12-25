package com.example.instagram.repository;

import com.example.instagram.model.Commit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CommitRepository extends JpaRepository<Commit, Long> {
    @Query("select c from Publication p left join p.comments c where p.id = :id")
    List<Commit> findAllCommitByPublicationId(@Param(value = "id") Long id);

}