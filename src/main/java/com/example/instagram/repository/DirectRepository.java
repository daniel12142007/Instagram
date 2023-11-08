package com.example.instagram.repository;

import com.example.instagram.model.Direct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DirectRepository extends JpaRepository<Direct, Long> {
}