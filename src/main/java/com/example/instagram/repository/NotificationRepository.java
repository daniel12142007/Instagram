package com.example.instagram.repository;

import com.example.instagram.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("""
            select n
            from Notification n
            where n.direct.user.email = :email and n.user.email = :myEmail
            """)
    List<Notification> findAllByDirectNotification(@Param("myEmail") String myEmail, @Param("email") String email);

    @Query("select n from Notification n where n.group.id = :id")
    List<Notification> findAllNotificationByGroupId(@Param(value = "id") Long id);
}