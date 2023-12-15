package org.example.dao;

import net.bytebuddy.asm.Advice;
import org.example.entities.Loan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class LoansDAO {
    private final EntityManager em;

    public LoansDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Loan loan) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(loan);

        transaction.commit();

        System.out.println("Loan saved.");
}


    public List<Loan> searchCurrentlyBorrowedItems() {
        TypedQuery<Loan> query = em.createQuery("SELECT l FROM Loan l WHERE l.returnDate = null",Loan.class);
        return query.getResultList();
    }
    public List<Loan> searchCurrentlyBorrowedItemsByCardNumber(int cardNumber) {
        TypedQuery<Loan> query = em.createQuery("SELECT l FROM Loan l WHERE l.user.cardNumber = :cardNumber AND l.returnDate IS NULL",Loan.class);
        query.setParameter("cardNumber", cardNumber);
        return query.getResultList();
    }

    public List<Loan> findUnreturnedLoans () {
        TypedQuery<Loan> query = em.createQuery("SELECT l FROM Loan l WHERE l.returnDate = null AND l.loanEnd < :today",Loan.class);
        query.setParameter("today", LocalDate.now());
        return query.getResultList();
    }

    public Long countHowManyLoans () {
        TypedQuery<Long> query = em.createQuery("SELECT count(l) FROM Loan l",Long.class);
        return query.getSingleResult();
    }

    public void returnItem(LocalDate today, Loan loan) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query modify = em.createQuery("UPDATE Loan l SET l.returnDate = :today WHERE l = :loan");
        modify.setParameter("today", LocalDate.now());
        modify.setParameter("loan", loan);
        modify.executeUpdate();
        System.out.println("elemento modificato");
        transaction.commit();
    }
}
