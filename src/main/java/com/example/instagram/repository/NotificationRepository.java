package com.example.instagram.repository;

import com.example.instagram.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}