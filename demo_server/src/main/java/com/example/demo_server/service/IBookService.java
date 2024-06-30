package com.example.demo_server.service;

import com.example.demo_server.DTO.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService {
    Page<BookDTO> findAll(String name, String bookTypeId ,Pageable pageable);
    void save(BookDTO bookDTO);
    void delete (Integer id);
    BookDTO findById(Integer id);
    void update(BookDTO bookDTO);
}
