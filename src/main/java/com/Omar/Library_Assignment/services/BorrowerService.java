package com.Omar.Library_Assignment.services;

import com.Omar.Library_Assignment.Repositories.BookRepository;
import com.Omar.Library_Assignment.Repositories.BorrowerRepository;
import com.Omar.Library_Assignment.entities.Borrower;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BorrowerService {
    private final BookRepository bookRepository;
    private final BorrowerRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Borrower> findAll(String user){
        var users = userRepository.findAll();
        if(user!=null){
            users = users.stream()
                    .filter(b -> b.getUsername()
                            .toLowerCase()
                            .contains(user.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return users;
    }

    public Borrower findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @PutMapping
    public Borrower save(Borrower user){
        if(user == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);

        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
