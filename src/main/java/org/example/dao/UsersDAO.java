package org.example.dao;

import org.example.entities.PrintedItem;
import org.example.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
        return em.find(User.class, cardNumber);
    }


}
