package com.google.logic;

import com.google.dao.NewsDAO;
import com.google.entity.LupdpmessageEntity;
import net.sf.json.JSONObject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class NewsLogic {
    @Resource
    private NewsDAO newsDAO;

    @Resource
    private SessionFactory sessionFactory;

    public JSONObject DbTest(){
        String sql=" select * from newscontent limit 1";
        try {
            List<Map<String,Object>> list=newsDAO.CommonSearch(sql,null);
            JSONObject result=JSONObject.fromObject(list.get(0));


            //SpringMVC 使用jackson返回json时，value不能为null
            Set<Map.Entry<String,Object>> set=result.entrySet();
            for(Map.Entry<String,Object> entry:set) {
                  if(entry.getValue()==null || entry.getValue().toString().trim().equals("null"))
                  {
                      entry.setValue("");
                  }
              }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }


    public boolean PersistenceTest(){
        Session session=null;
        Transaction trans=null;
        try {
            session= sessionFactory.getCurrentSession();
            trans=session.beginTransaction();

            LupdpmessageEntity lupdpmessageEntity=new LupdpmessageEntity();
            lupdpmessageEntity.setId("001");
            lupdpmessageEntity.setNewid("123");

            session.save(lupdpmessageEntity);

            session.getTransaction().commit();
            return true;

        } catch (HibernateException e) {
            if(trans!=null)
            {
                System.out.println(e);
                trans.rollback();
            }
            return  false;
        }finally{
            if(session!=null)
            {
                session.close();
            }
        }
    }
}
