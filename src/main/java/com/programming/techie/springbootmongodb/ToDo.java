package com.programming.techie.springbootmongodb;

import org.springframework.data.annotation.Id;

public class ToDo {
    @Id
    private String id;
    private String details;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}