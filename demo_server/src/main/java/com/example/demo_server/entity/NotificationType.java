package com.example.demo_server.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "book_type")
public class NotificationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "bookType")
    @JsonManagedReference
    private Set<Notification> notificationSet;

    public NotificationType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Notification> getBookSet() {
        return notificationSet;
    }

    public void setBookSet(Set<Notification> notificationSet) {
        this.notificationSet = notificationSet;
    }
}
