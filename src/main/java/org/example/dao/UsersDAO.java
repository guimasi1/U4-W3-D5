package org.example.dao;

import javax.persistence.EntityManager;

public class UsersDAO {
    private final EntityManager em;

    public UsersDAO(EntityManager em) {
        this.em = em;
    }
}
