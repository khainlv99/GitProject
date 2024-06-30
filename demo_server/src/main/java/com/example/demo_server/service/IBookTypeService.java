package com.example.demo_server.service;

import com.example.demo_server.DTO.BookTypeDTO;

import java.util.List;

public interface IBookTypeService {
    List<BookTypeDTO> findAll();
}
