package com.Omar.Library_Assignment.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
public class Book implements Serializable {

    private static final long serialVersionUID = 1578783440421245204L;
    @Id
    private String id;
    private String isbn;
    private String title;
    private String author;
    private List<String> genre;
    private boolean isAvailable;
    private Borrower borrower;
}