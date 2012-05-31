package com.weiss.dbutils;

import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.weiss.dbutils.Dao;
import com.weiss.dbutils.DaoImpl;

public class DaoImplTest {  
	  
    private Dao dao;  
  
    @Before  
    public void testDaoImpl() {  
        dao = new DaoImpl();  
    }  
  
    @Test  
    public void testFindAll() {  
        List<Sex> result = dao.findAll(Sex.class);  
        Assert.assertEquals(result.size(), 6);  
    }  
  
    @Test  
    public void testFindById() {  
        Sex sex = dao.findById(Sex.class, 4);  
        Assert.assertEquals(sex.getSex_mc(), "male");  
    }  
  
    @Test  
    public void testExecuteQuery() {  
        List<Map<String, Object>> result = dao.executeQuery("select * from sex where id=?", new Object[] { 1 });  
        Assert.assertEquals(result.get(0).get("id"), "female");  
    }  
  
    @Test  
    public void testInsert() {  
    	for(int i=0;i<10;i++){
    		dao.insert("insert into sex (sex_mc) values (?)", new Object[] { "male" });  
    	}
//        Assert.assertNotNull(id);  
    }  
  
    @Test  
    public void testUpdate() {  
        long id = dao.update("update sex set sex_mc=? where sex_id=?", new Object[] { "male", 5 });  
        Assert.assertEquals(id, 5);  
    }  
  
    @Test  
    public void testDelete() {  
        dao.delete("delete from sex where sex_id=?", new Object[] { 5 });  
    }  
  
    // @Test  
    public void testExecuteUpdate() {  
  
    }  
  
    @Test  
    public void testBatchUpdate() {  
        Object[][] objs = new Object[][] { { "man" }, { "women" }, { "man" } };  
        int[] ids = dao.batchUpdate("insert into sex (sex_mc) values(?)", objs);  
        Assert.assertEquals(3, ids.length);  
    }  
  
}  
