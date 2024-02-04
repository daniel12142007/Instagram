package com.example.instagram.repository;

import com.example.instagram.dto.response.CommitResponse;
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
    @Query("""
            select new com.example.instagram.dto.response.CommitResponse(
            p.id,
            c.id,
            c.userCommit.fullName,
            c.userCommit.email,
            c.commit,
            (select count(likeCount) from c.users likeCount),
            (select coalesce(
            case when count(myLike)>0 then true else false end,false
            )
            from Commit commit join commit.users myLike where myLike.email = :email),
            c.dataNow
            )
            from Publication p
            left join p.comments c
            where p.id = :publicationId
            order by c.dataNow desc 
            """)
    List<CommitResponse> findAllCommitResponse(
            @Param(value = "publicationId") Long publicationId,
            @Param(value = "email") String email);
}