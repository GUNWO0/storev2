package com.metacoding.storev2.store;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class StoreRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Store> findAll() {
        Query query = em.createNativeQuery("select id, name, price, stock from store_tb order by id desc", Store.class);
        return query.getResultList();
    }


    public void save(String name, int stock, int price) {
        Query query = em.createNativeQuery("insert into store_tb(name, stock, price) values(?,?,?)");
        query.setParameter(1, name);
        query.setParameter(2, stock);
        query.setParameter(3, price);
        query.executeUpdate();
    }

    public Store findById(int id) {
        Query query = em.createNativeQuery("select * from store_tb where id = ?", Store.class);
        query.setParameter(1, id);
        return (Store) query.getSingleResult();
    }

    public void deleteById(int id) {
        Query query = em.createNativeQuery("delete from store_tb where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    public void updateById(int id, StoreRequest.ProductDTO productDTO) {
        Query query = em.createNativeQuery("update store_tb set name = ?, stock = ?, price = ? where id = ?");
        query.setParameter(1, productDTO.getName());
        query.setParameter(2, productDTO.getStock());
        query.setParameter(3, productDTO.getPrice());
        query.setParameter(4, id);
        query.executeUpdate();
    }
}
