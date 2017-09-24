package com.google.repository;


import com.dingdingzaixian.entity.customer.AuthorityEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity,String> {
    @Override
    List<AuthorityEntity> findAll(Sort sort);

    @Override
    List<AuthorityEntity> findAll(Iterable<String> strings);

    @Override
    void flush();

    @Override
    <S extends AuthorityEntity> List<S> save(Iterable<S> entities);

    @Override
    <S extends AuthorityEntity> S save(S entity);

    @Override
    AuthorityEntity findOne(String s);


    @Override
    long count();

    @Override
    void delete(String s);

    @Override
    void delete(AuthorityEntity entity);

}
