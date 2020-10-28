package com.Omar.Library_Assignment.Repositories;

import com.Omar.Library_Assignment.entities.Borrower;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowerRepository extends MongoRepository<Borrower, String> {
    Optional<Borrower> findByUsername(String username);
}