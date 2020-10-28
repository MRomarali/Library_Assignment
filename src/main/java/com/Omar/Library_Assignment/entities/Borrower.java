package com.Omar.Library_Assignment.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
public class Borrower {

    private static final long serialVersionUID = 9876543343424324L;

    @Id
    private String id;
    private String mail;
    private String username;
    private String password;
    private List<String> acl;
}