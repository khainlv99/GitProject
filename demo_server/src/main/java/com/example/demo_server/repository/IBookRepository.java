package com.example.demo_server.repository;
import com.example.demo_server.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IBookRepository extends JpaRepository<Notification, Integer> {
    @Query(value = "select * from book where name like concat('%', :name, '%') " +
            "and book_type_id like concat('%', :id, '%')", nativeQuery = true)
    Page<Notification> findAllBooks(@Param("name") String name, @Param("id") String bookTypeId, Pageable pageable);
}
