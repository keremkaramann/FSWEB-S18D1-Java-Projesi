package com.workintech.demo.dao;

import com.workintech.demo.entity.BreadType;
import com.workintech.demo.entity.Burger;

import java.util.List;
import java.util.Optional;

public interface BurgerDao {

    Burger save(Burger burger);

    List<Burger> findAll();

    Burger findById(long id);

    Burger update(Burger burger);

    Burger remove(Long id);

    List<Burger> findByPrice(Integer price);

    List<Burger> findByBreadType(BreadType breadType);

    List<Burger> findByContent(String content);
}
