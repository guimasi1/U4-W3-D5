package org.example.entities;


import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private PrintedItem item;

    @Column(name = "loan_start_date")
    private LocalDate loanStart;

    @Column(name = "loan_end_date")
    private LocalDate loanEnd;

    @Column(name = "return_date")
    private LocalDate returnDate;

    // CONSTRUCTOR

    public Loan () {}

    public Loan(User user, PrintedItem item, LocalDate loanStart) {
        this.user = user;
        this.item = item;
        this.loanStart = loanStart;
        this.loanEnd = loanStart.plusDays(30);
    }

    public Loan(User user, PrintedItem item, LocalDate loanStart, LocalDate returnDate) {
        this.user = user;
        this.item = item;
        this.loanStart = loanStart;
        this.loanEnd = loanStart.plusDays(30);
        this.returnDate = returnDate;
    }

    // GETTER AND SETTER


    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PrintedItem getItem() {
        return item;
    }

    public void setItem(PrintedItem item) {
        this.item = item;
    }

    public LocalDate getLoanStart() {
        return loanStart;
    }

    public void setLoanStart(LocalDate loanStart) {
        this.loanStart = loanStart;
    }

    public LocalDate getLoanEnd() {
        return loanEnd;
    }

    public void setLoanEnd(LocalDate loanEnd) {
        this.loanEnd = loanEnd;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", user=" + user +
                ", item=" + item +
                ", loanStart=" + loanStart +
                ", loanEnd=" + loanEnd +
                ", returnDate=" + returnDate +
                '}';
    }
}
