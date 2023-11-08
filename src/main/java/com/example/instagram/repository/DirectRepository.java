package com.example.instagram.repository;

import com.example.instagram.model.Direct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DirectRepository extends JpaRepository<Direct, Long> {
    @Query("select d from Direct d where d.user.email = :email")
    Direct findByUserEmail(String email);
}