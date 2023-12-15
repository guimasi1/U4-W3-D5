package org.example.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "books")
public class Book extends PrintedItem{
    private String author;
    private String genre;

    // CONSTRUCTORS

    public Book () {}

    public Book(String isbn, String title, int publicationYear, int numberOfPages, String author, String genre) {
        super(isbn, title, publicationYear, numberOfPages);
        this.author = author;
        this.genre = genre;
    }

    // GETTER AND SETTER


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + this.getIsbn() + '\'' +
                ", title='" + this.getTitle() + '\'' +
                ", publication year='" + this.getPublicationYear() + '\'' +
                ", number of pages='" + this.getNumberOfPages() + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
