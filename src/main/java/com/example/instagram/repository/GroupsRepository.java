package com.example.instagram.repository;

import com.example.instagram.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface GroupsRepository extends JpaRepository<Groups, Long> {
}