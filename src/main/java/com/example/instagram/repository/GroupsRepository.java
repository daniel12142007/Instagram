package com.example.instagram.repository;

import com.example.instagram.model.Groups;
import com.example.instagram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface GroupsRepository extends JpaRepository<Groups, Long> {
    @Query("select g.users from Groups g where g.id = :id")
    List<User> findAllByGroupId(@Param(value = "id") Long id);
}