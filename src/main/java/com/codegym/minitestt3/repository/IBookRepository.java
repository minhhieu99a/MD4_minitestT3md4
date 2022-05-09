package com.codegym.minitestt3.repository;

import com.codegym.minitestt3.model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBookRepository extends PagingAndSortingRepository<Book,Long> {
}
