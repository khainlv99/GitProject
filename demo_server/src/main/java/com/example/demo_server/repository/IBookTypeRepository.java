package com.example.demo_server.repository;

import com.example.demo_server.entity.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBookTypeRepository extends JpaRepository<NotificationType, Integer> {
    @Query(value = "select * from book_type", nativeQuery = true)
    List<NotificationType> findAllBookTypes();

    @Query(value = "select * from book_type where id = :id", nativeQuery = true)
    NotificationType findTypeOfBookById(@Param("id") Integer id);
}
