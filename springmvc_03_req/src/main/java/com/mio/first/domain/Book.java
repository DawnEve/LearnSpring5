package com.mio.first.domain;

public class Book {
    private String name;
    private int price;

    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Book[name:%s, price:%d, author: %s]", name, price, author);
    }
}
