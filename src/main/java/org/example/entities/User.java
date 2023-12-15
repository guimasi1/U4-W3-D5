package org.example.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    private String name;
    private String surname;
    private String birthday;
    @Id
    @GeneratedValue
    @Column(name = "card_number")
    private int cardNumber;

    @OneToMany(mappedBy = "user")
    private List<Loan> loans;

    // CONSTRUCTORS

    public User () {}

    public User(String name, String surname, String birthday, int cardNumber) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.cardNumber = cardNumber;
    }

    // GETTER AND SETTER

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday='" + birthday + '\'' +
                ", cardNumber=" + cardNumber +
                '}';
    }
}
