package org.example;

import org.example.dao.LoansDAO;
import org.example.dao.PrintedItemsDAO;
import org.example.dao.UsersDAO;
import org.example.entities.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U4-W3-D5");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        PrintedItemsDAO itemsDAO = new PrintedItemsDAO(em);
        UsersDAO usersDAO = new UsersDAO(em);
        LoansDAO loansDAO = new LoansDAO(em);
        Book book1 = new Book("123", "title", 2010, 100,"gianni", "fiction");
        Book book2 = new Book("124", "hey", 2010, 100,"gianni", "fiction");
        Book book3 = new Book("125", "title", 2010, 100,"gianni", "fiction");
        // itemsDAO.save(book1);
        // itemsDAO.save(book2);
        // itemsDAO.save(book3);
        itemsDAO.searchByTitle("it").forEach(System.out::println);

    }
}
