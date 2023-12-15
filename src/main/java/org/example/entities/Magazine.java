package org.example.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "magazines")
public class Magazine extends PrintedItem {
    @Enumerated(EnumType.STRING)
    Periodicity periodicity;

    // CONSTRUCTORS

    public Magazine() {}

    public Magazine(String isbn, String title, int publicationYear, int numberOfPages, Periodicity periodicity) {
        super(isbn, title, publicationYear, numberOfPages);
        this.periodicity = periodicity;
    }

    // GETTER AND SETTER


    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "periodicity=" + periodicity +
                '}';
    }
}
