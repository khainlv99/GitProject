package com.example.demo_server.service.impl;

import com.example.demo_server.DTO.BookDTO;
import com.example.demo_server.DTO.BookTypeDTO;
import com.example.demo_server.entity.Notification;
import com.example.demo_server.repository.IBookRepository;
import com.example.demo_server.repository.IBookTypeRepository;
import com.example.demo_server.service.IBookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService {
    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    private IBookTypeRepository bookTypeRepository;
    public BookDTO mapToBookDTO(Notification notification) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookTypeDTO(new BookTypeDTO());
        BeanUtils.copyProperties(notification.getBookType(), bookDTO.getBookTypeDTO());
        BeanUtils.copyProperties(notification, bookDTO);
        return bookDTO;
    }

    @Override
    public Page<BookDTO> findAll(String name, String bookTypeId, Pageable pageable) {
        Page<Notification> bookPage = bookRepository.findAllBooks(name, bookTypeId, pageable);
        List<BookDTO> bookDTOList = bookPage.stream().map(this::mapToBookDTO).collect(Collectors.toList());
        return new PageImpl<>(bookDTOList, pageable, bookPage.getTotalElements());
    }

    @Override
    public void save(BookDTO bookDTO) {
        Notification notification = new Notification();
        notification.setBookType(bookTypeRepository.findTypeOfBookById(bookDTO.getBookTypeDTO().getId()));
        BeanUtils.copyProperties(bookDTO, notification);
        bookRepository.save(notification);
    }

    @Override
    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDTO findById(Integer id) {
        Notification notification = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find the book required"));
        return mapToBookDTO(notification);
    }

    @Override
    public void update(BookDTO bookDTO) {
        Notification notification = new Notification();
        notification.setBookType(bookTypeRepository.findTypeOfBookById(bookDTO.getBookTypeDTO().getId()));
        BeanUtils.copyProperties(bookDTO, notification);
        bookRepository.save(notification);
    }
}
