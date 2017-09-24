package com.google.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface LupdpmessageRepository extends CrudRepository<LupdpmessageEntity,String> {
    @Override
    LupdpmessageEntity findOne(String s);
}
