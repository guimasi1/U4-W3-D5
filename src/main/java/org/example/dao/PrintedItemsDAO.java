package org.example.dao;

import org.example.entities.Book;
import org.example.entities.Periodicity;
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

    public PrintedItem searchByExactTitle(String title) {
        TypedQuery<PrintedItem> query = em.createQuery("SELECT p FROM PrintedItem p WHERE LOWER(p.title) = LOWER(:title)", PrintedItem.class);
        query.setParameter("title", title);
        return query.getSingleResult();
    }

    public List<PrintedItem> showAllElements() {
        TypedQuery<PrintedItem> query = em.createQuery("SELECT p FROM PrintedItem p", PrintedItem.class);
        return query.getResultList();
    }
    public List<PrintedItem> orderElementsByTitleAZ() {
        TypedQuery<PrintedItem> query = em.createQuery("SELECT p FROM PrintedItem p ORDER BY p.title", PrintedItem.class);
        return query.getResultList();
    }
    public List<PrintedItem> orderElementsByTitleZA() {
        TypedQuery<PrintedItem> query = em.createQuery("SELECT p FROM PrintedItem p ORDER BY p.title DESC", PrintedItem.class);
        return query.getResultList();
    }
    public List<PrintedItem> orderElementsByOldest() {
        TypedQuery<PrintedItem> query = em.createQuery("SELECT p FROM PrintedItem p ORDER BY p.publicationYear", PrintedItem.class);
        return query.getResultList();
    }
    public List<PrintedItem> orderElementsByMostRecent() {
        TypedQuery<PrintedItem> query = em.createQuery("SELECT p FROM PrintedItem p ORDER BY p.publicationYear DESC", PrintedItem.class);
        return query.getResultList();
    }
    public List<PrintedItem> orderByMostPages() {
        TypedQuery<PrintedItem> query = em.createQuery("SELECT p FROM PrintedItem p ORDER BY p.numberOfPages DESC", PrintedItem.class);
        return query.getResultList();
    }
    public List<PrintedItem> orderByLeastPages() {
        TypedQuery<PrintedItem> query = em.createQuery("SELECT p FROM PrintedItem p ORDER BY p.numberOfPages", PrintedItem.class);
        return query.getResultList();
    }
    public List<PrintedItem> getByPeriodicity(Periodicity periodicity) {
        TypedQuery<PrintedItem> query = em.createQuery("SELECT p FROM PrintedItem p WHERE p.periodicity = :periodicity", PrintedItem.class);
        query.setParameter("periodicity", periodicity);
        return query.getResultList();
    }
    public List<PrintedItem> getByGenre(String genre) {
        TypedQuery<PrintedItem> query = em.createQuery("SELECT p FROM PrintedItem p WHERE LOWER(p.genre) = LOWER(:genre)", PrintedItem.class);
        query.setParameter("genre", genre);
        return query.getResultList();
    }

}
