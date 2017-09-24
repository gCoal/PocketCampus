package com.google.repository;


import com.dingdingzaixian.entity.insurance.InsuranceOrderEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsuranceOrderRepository extends JpaRepository<InsuranceOrderEntity,String> {
    @Override
    List<InsuranceOrderEntity> findAll(Sort sort);

    @Override
    List<InsuranceOrderEntity> findAll(Iterable<String> strings);

    @Override
    void flush();

    @Override
    <S extends InsuranceOrderEntity> List<S> save(Iterable<S> entities);

    @Override
    <S extends InsuranceOrderEntity> S save(S entity);

    @Override
    InsuranceOrderEntity findOne(String s);

    @Override
    long count();

    @Override
    void delete(String s);

    @Override
    void delete(InsuranceOrderEntity entity);

}
