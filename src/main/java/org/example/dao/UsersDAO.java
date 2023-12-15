package org.example.dao;

import org.example.entities.Loan;
import org.example.entities.PrintedItem;
import org.example.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class UsersDAO {
    private final EntityManager em;

    public UsersDAO(EntityManager em) {
        this.em = em;
    }

    public void save(User user) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(user);

        transaction.commit();

        System.out.println("User saved.");
    }

    public User getByCardNumber(int cardNumber) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.cardNumber = :cardNumber", User.class);
        query.setParameter("cardNumber", cardNumber);
        return query.getSingleResult();
    }

    public List<User> getAllUsers() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u ", User.class);
        return query.getResultList();
    }


}
