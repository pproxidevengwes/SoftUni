package com.example.springintro.service;

import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.AuthorNamesWithTotalCopies;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();

    void findByFirstNameEndingWith(String letters);

    List<AuthorNamesWithTotalCopies> getTotalCopies();}
