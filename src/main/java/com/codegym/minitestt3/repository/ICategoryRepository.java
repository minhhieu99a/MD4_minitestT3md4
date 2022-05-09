package com.codegym.minitestt3.repository;

import com.codegym.minitestt3.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICategoryRepository extends PagingAndSortingRepository<Category,Long> {
}
