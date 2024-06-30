package com.example.demo_server.controller;
import com.example.demo_server.DTO.BookDTO;
import com.example.demo_server.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
@CrossOrigin("*")
public class BookRestController {
    @Autowired
    private IBookService bookService;
    public ResponseEntity<?> handleValidationErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> map = new LinkedHashMap<>();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                if (!map.containsKey(error.getField())) {
                    map.put(error.getField(), error.getDefaultMessage());
                }
            }
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Page<BookDTO> getAllBooks (@PageableDefault(size = 3)Pageable pageable,
                                      @RequestParam(required = false, defaultValue = "") String name,
                                      @RequestParam(required = false, defaultValue = "") String bookTypeId) {

        return bookService.findAll(name, bookTypeId, pageable);
    }

    @PostMapping("")
    public ResponseEntity<?> saveBook (@Validated @RequestBody BookDTO bookDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            bookService.save(bookDTO);
        } else {
            return handleValidationErrors(bindingResult);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable Integer id) {
        bookService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getBookDetail(@PathVariable Integer id) {
        return bookService.findById(id);
    }

    @PutMapping("")
    public ResponseEntity<?> updateBook (@Validated @RequestBody BookDTO bookDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            bookService.update(bookDTO);
        } else {
            return handleValidationErrors(bindingResult);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
