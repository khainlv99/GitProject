package com.example.demo_server.service.impl;
import com.example.demo_server.DTO.BookTypeDTO;
import com.example.demo_server.entity.NotificationType;
import com.example.demo_server.repository.IBookTypeRepository;
import com.example.demo_server.service.IBookTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookTypeService implements IBookTypeService {
    @Autowired
    private IBookTypeRepository bookTypeRepository;

    @Override
    public List<BookTypeDTO> findAll() {
        List<NotificationType> notificationTypeList = bookTypeRepository.findAllBookTypes();
        return notificationTypeList.stream().map(notificationType -> {
            BookTypeDTO bookTypeDTO = new BookTypeDTO();
            BeanUtils.copyProperties(notificationType, bookTypeDTO);
            return bookTypeDTO;
        }).collect(Collectors.toList());
    }
}
