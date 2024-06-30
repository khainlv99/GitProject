package com.example.demo_server.DTO;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class BookDTO {
    private Integer id;
    @Pattern(regexp = "BO-[0-9]{4}$", message = "Mã sách có định dạng BO-XXXX (X là số)")
    private String code;
    @Size(max = 100, message = "Tên sách không dài quá 100 ký tự")
    private String name;
    private String importedDate;
//    @Pattern(regexp = "^[1-9][\\\\d]*$", message = "Số lượng sách phải là số nguyên dương")
    @Positive(message = "Số lượng sách phải là số nguyên dương")
    @Digits(fraction = 0, message = "Số lượng sách phải là số nguyên", integer = 10)
    private Integer quantity;
    private BookTypeDTO bookTypeDTO;
    public BookDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImportedDate() {
        return importedDate;
    }

    public void setImportedDate(String importedDate) {
        this.importedDate = importedDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BookTypeDTO getBookTypeDTO() {
        return bookTypeDTO;
    }

    public void setBookTypeDTO(BookTypeDTO bookTypeDTO) {
        this.bookTypeDTO = bookTypeDTO;
    }
}
