package org.example;

import net.bytebuddy.asm.Advice;
import org.example.dao.LoansDAO;
import org.example.dao.PrintedItemsDAO;
import org.example.dao.UsersDAO;
import org.example.entities.Book;
import org.example.entities.Loan;
import org.example.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Scanner;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U4-W3-D5");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = emf.createEntityManager();
        PrintedItemsDAO itemsDAO = new PrintedItemsDAO(em);
        LocalDate date1 = LocalDate.parse("2023-12-01");
        LocalDate date2 = LocalDate.parse("2023-10-05");
        LocalDate date3 = LocalDate.parse("2023-11-16");
        LocalDate date4 = LocalDate.parse("2023-01-06");
        LocalDate date5 = LocalDate.parse("2023-02-02");
        UsersDAO usersDAO = new UsersDAO(em);
        LoansDAO loansDAO = new LoansDAO(em);
        Book book1 = new Book("123", "title", 2010, 100,"gianni", "fiction");
        Book book2 = new Book("124", "hey", 2010, 100,"gianni", "fiction");
        Book book3 = new Book("125", "title", 2010, 100,"gianni", "fiction");
        // itemsDAO.save(book1);
        // itemsDAO.save(book2);
        // itemsDAO.save(book3);
        // itemsDAO.searchByTitle("it").forEach(System.out::println);
        User user1 = new User("Chicco", "Pallo",LocalDate.now());
        User user2 = new User("Marco", "Frasci",LocalDate.now());
        User user3 = new User("Peppo", "Milli",LocalDate.now());

        Loan loan1 = new Loan(usersDAO.getByCardNumber(1), itemsDAO.findByISBN("123"), date2);
        Loan loan2 = new Loan(usersDAO.getByCardNumber(2), itemsDAO.findByISBN("124"), date2, date1);
        Loan loan3 = new Loan(usersDAO.getByCardNumber(3), itemsDAO.findByISBN("125"), date4, date5);
        Loan loan4 = new Loan(usersDAO.getByCardNumber(1),itemsDAO.findByISBN("123"), date5);

        // usersDAO.save(user1);
        // usersDAO.save(user2);
        // usersDAO.save(user3);

        // loansDAO.save(loan1);
        // loansDAO.save(loan2);
        // loansDAO.save(loan3);
        // loansDAO.save(loan4);


        // loansDAO.searchCurrentlyBorrowedItems().forEach(System.out::println);
        // loansDAO.searchCurrentlyBorrowedItemsByCardNumber(1).forEach(System.out::println);
        // loansDAO.findUnreturnedLoans().forEach(System.out::println);

        for (int i = 0; i < 10; i++) {

        }

        scanner.close();
        em.close();
        emf.close();
    }
}
