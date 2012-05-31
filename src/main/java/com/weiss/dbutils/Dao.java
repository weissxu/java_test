package com.weiss.dbutils;

import java.util.List;
import java.util.Map;

public interface Dao {  
	  
    public <T> List<T> findAll(Class<T> clazz);  
  
    public <T> T findById(Class<T> clazz, int id);  
  
    public long insert(String sql, Object... args);  
  
    public long update(String sql, Object... args);  
  
    public List<Map<String, Object>> executeQuery(String sql, Object... args);  
  
    public long executeUpdate(String sql, Object... args);  
  
    public void delete(String sql, Object... args);  
  
    public int[] batchUpdate(String sql, Object[][] objs);  
  
}  
