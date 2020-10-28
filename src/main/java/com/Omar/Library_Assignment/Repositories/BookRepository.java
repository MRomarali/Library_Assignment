package com.Omar.Library_Assignment.Repositories;

import com.Omar.Library_Assignment.entities.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
}