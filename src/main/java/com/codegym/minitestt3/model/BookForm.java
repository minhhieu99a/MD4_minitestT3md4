package com.codegym.minitestt3.model;

import org.springframework.web.multipart.MultipartFile;

public class BookForm {
    private Long id;
    private String name;
    private int price;
    private String author;
    private Category category;
    private MultipartFile image;

    public BookForm() {
    }

    public BookForm(String name, int price, String author, MultipartFile image) {
        this.name = name;
        this.price = price;
        this.author = author;
        this.image = image;
    }

    public BookForm(Long id, String name, int price, String author, Category category, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.author = author;
        this.category = category;
        this.image = image;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public BookForm(String name, int price, String author, Category category, MultipartFile image) {
        this.name = name;
        this.price = price;
        this.author = author;
        this.category = category;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
