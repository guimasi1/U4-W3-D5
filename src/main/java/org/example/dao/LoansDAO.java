package org.example.dao;

import javax.persistence.EntityManager;

public class LoansDAO {
    private final EntityManager em;

    public LoansDAO(EntityManager em) {
        this.em = em;
    }
}
