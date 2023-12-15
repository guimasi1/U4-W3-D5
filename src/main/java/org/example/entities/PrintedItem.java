package org.example.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "printed_items")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PrintedItem {

    @Id
    private String isbn;
    private String title;
    private int publicationYear;
    private int numberOfPages;
    @OneToMany(mappedBy = "item")
    private List<Loan> loans;

    // CONSTRUCTORS
    public PrintedItem() {}

    public PrintedItem(String isbn, String title, int publicationYear, int numberOfPages) {
        this.isbn = isbn;
        this.title = title;
        this.publicationYear = publicationYear;
        this.numberOfPages = numberOfPages;
    }

// GETTER AND SETTER


    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    @Override
    public String toString() {
        return
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                ", numberOfPages=" + numberOfPages +
                ", loans=" + loans +
                '}';
    }
}
