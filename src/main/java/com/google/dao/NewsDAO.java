package com.google.dao;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 扬雅丹 on 2016/11/28.
 */
@Component
public class NewsDAO {

    @Resource(name = "lupdp")
    DruidDataSource lupdp;

    public List<Map<String,Object>> CommonSearch(String sql, Object[] objs) throws Exception{
        List<Map<String,Object>> result=null;
        QueryRunner qr = new QueryRunner(lupdp);
        result=qr.query(sql,new MapListHandler(),objs);
        return result;
    }
}