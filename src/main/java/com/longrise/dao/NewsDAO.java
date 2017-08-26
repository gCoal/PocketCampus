package com.longrise.dao;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by 扬雅丹 on 2016/11/28.
 */
@Component
public class NewsDAO {

    @Resource(name = "lupdp")
    DruidDataSource lupdp;

    @Resource(name = "LSIP_train")
    DruidDataSource LSIP_train;

    public List<Map<String,Object>> CommonSearch(String sql, Object[] objs) throws Exception{
        List<Map<String,Object>> result=null;
        QueryRunner qr = new QueryRunner(lupdp);
        result=qr.query(sql,new MapListHandler(),objs);
        return result;
    }

    public List<Map<String,Object>> CommonSearch02(String sql, Object[] objs) throws Exception{
        List<Map<String,Object>> result=null;
        QueryRunner qr = new QueryRunner(LSIP_train);
        result=qr.query(sql,new MapListHandler(),objs);
        return result;
    }
}