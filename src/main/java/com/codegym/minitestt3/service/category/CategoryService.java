package com.codegym.minitestt3.service.category;

import com.codegym.minitestt3.model.Category;
import com.codegym.minitestt3.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll() ;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public void remove(Long id) {

    }
}
