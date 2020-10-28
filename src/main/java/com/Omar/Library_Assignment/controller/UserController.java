package com.Omar.Library_Assignment.controller;

import com.Omar.Library_Assignment.entities.Borrower;
import com.Omar.Library_Assignment.services.BorrowerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private BorrowerService userService;

    @PostMapping("/start")
    public ResponseEntity<Borrower> save(@RequestBody Borrower user){
        return ResponseEntity.ok(userService.save(user));
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<Borrower>> findAll(@RequestParam(required = false) String users){
        return ResponseEntity.ok(userService.findAll(users));
    }
}