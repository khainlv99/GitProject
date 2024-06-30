package com.example.demo_server.controller;

import com.example.demo_server.DTO.BookTypeDTO;
import com.example.demo_server.service.IBookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookTypes")
@CrossOrigin("*")
public class BookTypeRestController {
    @Autowired
    private IBookTypeService bookTypeService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<BookTypeDTO> getAllBookTypes () {
        return bookTypeService.findAll();
    }
}
