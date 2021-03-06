package com.example.bookland.Helpers;

public class Book {
    private String name;
    private String author;
    private String price;
    private String image;
    private String rating;
    private String description;
    private String category;
    private String pages;

    public Book() {
    }

    public Book(String name, String author, String price, String image, String rating, String description,
                String category, String pages) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.image = image;
        this.rating = rating;
        this.description = description;
        this.category = category;
        this.pages = pages;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
