package com.workintech.demo.dao;

import com.workintech.demo.entity.BreadType;
import com.workintech.demo.entity.Burger;
import com.workintech.demo.exceptions.BurgerException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BurgerDaoImpl implements BurgerDao {
    private final EntityManager entityManager;

    @Autowired
    public BurgerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Burger save(Burger burger) {
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public List<Burger> findAll() {
        TypedQuery<Burger> foundAll = entityManager.createQuery("SELECT b FROM Burger b", Burger.class);
        return foundAll.getResultList();
    }

    @Override
    public Burger findById(long id) {
        Burger burger = entityManager.find(Burger.class, id);
        if(burger == null){
            throw new BurgerException("Burger with given id is not exist: "+id, HttpStatus.NOT_FOUND);
        }
        return burger;
    }

    @Override
    public List<Burger> findByPrice(Integer price) {
        TypedQuery<Burger> query =
                entityManager.createQuery("SELECT b FROM Burger b WHERE b.price > :price ORDER BY b.price DESC",
                        Burger.class);
        query.setParameter("price", price);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b WHERE b.breadType=:breadType ORDER BY b.name DESC",
                Burger.class);
        query.setParameter("breadType", breadType);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByContent(String content) {
        //Select * from burger b where b.contents like "%content=cheese,tomato%" order by b.name
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b WHERE" +
                "b.content LIKE CONCAT('%',:content,'%') ORDER BY b.name", Burger.class);
        query.setParameter("content", content);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Burger update(Burger burger) {
        return entityManager.merge(burger);
    }

    @Transactional
    @Override
    public Burger remove(Long id) {
        Burger burger = findById(id);
        entityManager.remove(burger);
        return burger;
    }
}
