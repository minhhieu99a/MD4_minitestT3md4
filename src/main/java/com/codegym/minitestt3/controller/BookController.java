package com.codegym.minitestt3.controller;

import com.codegym.minitestt3.model.Book;
import com.codegym.minitestt3.model.BookForm;
import com.codegym.minitestt3.model.Category;
import com.codegym.minitestt3.service.book.IBookService;
import com.codegym.minitestt3.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private IBookService bookService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    Environment env;

    @ModelAttribute("categories")
    public Iterable<Category> listAllCategory() {
        return categoryService.findAll();
    }

    @GetMapping("/cate")
    public ResponseEntity<Iterable<Category>> showCate() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ModelAndView showBooks() {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("book", bookService.findAll());
        return modelAndView;
    }

    @GetMapping
    public ResponseEntity<Iterable<Book>> listBooks() {
//        List<Book> bookList = (List<Book>) bookService.findAll();
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@ModelAttribute BookForm bookForm) {
        MultipartFile multipartFile = bookForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("upload.path");
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Book book = new Book(bookForm.getName(), bookForm.getPrice(), bookForm.getAuthor(), bookForm.getCategory(), fileName);
        bookService.save(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        Optional<Book> bookOptional = bookService.findById(id);
        if (bookOptional.isPresent()) {
            bookService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBook(@PathVariable Long id) {

        Optional<Book> bookOptional = bookService.findById(id);

        return new ResponseEntity<>(bookOptional.get(), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @ModelAttribute BookForm bookForm) {
        Optional<Book> bookOptional = bookService.findById(id);
        bookForm.setId(bookOptional.get().getId());
        MultipartFile multipartFile = bookForm.getImage();
        String fileName = multipartFile.getOriginalFilename();  
        String fileUpload = env.getProperty("upload.path");
        Book book = new Book(id, bookForm.getName(), bookForm.getPrice(), bookForm.getAuthor(), bookForm.getCategory(), fileName);
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (book.getImage().equals("filename.jpg")) {
            book.setImage(bookOptional.get().getImage());
        }

        bookService.save(book);
        return new ResponseEntity<>(book, HttpStatus.ACCEPTED);
    }
}

