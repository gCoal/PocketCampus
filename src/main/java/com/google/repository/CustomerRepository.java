package com.google.repository;


import com.google.entity.customer.CustomerEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity,String> {
    @Override
    List<CustomerEntity> findAll(Sort sort);

    @Override
    List<CustomerEntity> findAll(Iterable<String> strings);

    @Override
    void flush();

    @Override
    <S extends CustomerEntity> List<S> save(Iterable<S> entities);

    @Override
    <S extends CustomerEntity> S save(S entity);

    @Override
    CustomerEntity findOne(String s);

    CustomerEntity findOneByUsername(String s);

    @Override
    long count();

    @Override
    void delete(String s);

    @Override
    void delete(CustomerEntity entity);

}
