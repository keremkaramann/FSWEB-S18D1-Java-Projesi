package com.workintech.demo.controller;

import com.workintech.demo.dao.BurgerDao;
import com.workintech.demo.entity.BreadType;
import com.workintech.demo.entity.Burger;
import com.workintech.demo.util.BurgerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/burger")
public class BurgerController {

    private final BurgerDao burgerDao;

    @Autowired
    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }

    @PostMapping
    public Burger save(@RequestBody Burger burger) {
        BurgerValidation.checkName(burger.getName());
        return burgerDao.save(burger);
    }

    @GetMapping
    public List<Burger> findAll() {
        return burgerDao.findAll();
    }

    @GetMapping("/{id}")
    public Burger getById(@PathVariable Long id) {
        BurgerValidation.checkId(id);
        return burgerDao.findById(id);
    }

    @PutMapping
    public Burger update(@RequestBody Burger burger) {
        return burgerDao.update(burger);
    }

    @DeleteMapping("/{id}")
    public Burger remove(@PathVariable Long id) {
        BurgerValidation.checkId(id);
        return burgerDao.remove(id);
    }

    @GetMapping("/breadType/{breadType}")
    public List<Burger> getById(@PathVariable String breadType) {
        BreadType bt = BreadType.valueOf(breadType);
        return burgerDao.findByBreadType(bt);
    }

    @GetMapping("/price/{price}")
    public List<Burger> getByPrice(@PathVariable Integer price) {
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/content/{content}")
    public List<Burger> getByContent(@PathVariable String content) {
        return burgerDao.findByContent(content);
    }
}
