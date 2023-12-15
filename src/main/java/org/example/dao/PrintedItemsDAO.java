package org.example.dao;

import org.example.entities.Book;
import org.example.entities.PrintedItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
import java.util.List;

public class PrintedItemsDAO {

    private final EntityManager em;

    public PrintedItemsDAO(EntityManager em){
        this.em = em;
    }

    public void save(PrintedItem item) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(item);

        transaction.commit();

        System.out.println("Item created.");
    }

    public PrintedItem findByISBN(String isbn) {
        TypedQuery<PrintedItem> query = em.createQuery("SELECT p FROM PrintedItem p WHERE p.isbn = :isbn", PrintedItem.class);
        query.setParameter("isbn", isbn);
        return query.getSingleResult();
    }

    public void deleteByISBN(String isbn) {
        PrintedItem itemToDelete = this.findByISBN(isbn);
        if(itemToDelete != null) {
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            em.remove(itemToDelete);

            transaction.commit();

            System.out.println("Item removed. ISBN: " + isbn);
        } else System.out.println("Item not found.");
    }

    public List<PrintedItem> searchByPublicationYear(int year) {
        TypedQuery<PrintedItem> query = em.createQuery("SELECT p FROM PrintedItem p WHERE p.publicationYear = :year", PrintedItem.class);
        query.setParameter("year", year);
        return query.getResultList();
    }

    public List<Book> searchByAuthor(String author) {
        TypedQuery<Book> query = em.createQuery("SELECT p FROM PrintedItem p WHERE p.author = :author", Book.class);
        query.setParameter("author", author);
        return query.getResultList();
    }

    public List<PrintedItem> searchByTitle(String title) {
        TypedQuery<PrintedItem> query = em.createQuery("SELECT p FROM PrintedItem p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%',:title, '%'))", PrintedItem.class);
        query.setParameter("title", title);
        return query.getResultList();
    }



}
