/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shnva
 * @param <T>
 */
public class Dataprovider<T> {
    
    private void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public int remove(T item) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                Field f = item.getClass().getDeclaredField("id");
                f.setAccessible(true);
                int id = f.getInt(item);
                Statement st = conn.createStatement();
                return st.executeUpdate("DELETE FROM " + getTableName() + " WHERE id = " + id);
            } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
                System.out.println(e.getMessage());
            } finally {
                closeConnection(conn);
            }
        }
        return 0;
    }
    
    private String getTableName() {
        String className = c.getName().toLowerCase();
        String superClassName = c.getGenericSuperclass().getTypeName().toLowerCase();
        if (!superClassName.endsWith("object")) {
            className = superClassName;
        }
        
        int index = className.indexOf(".");
        if (index > 0) {
            return className.substring(index + 1, className.length());
        }
        return className;
    }
    
    private List<String> getDatabaseFields() {
        Connection conn = getConnection();
        List<String> result = new ArrayList<>();
        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                String s = "SELECT * FROM " + getTableName();
                ResultSet rs = st.executeQuery(s);
                ResultSetMetaData md = rs.getMetaData();
                int count = md.getColumnCount();
                for (int i = 1; i <= count; i++) {
                    result.add(md.getColumnName(i).toLowerCase());
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                closeConnection(conn);
            }
        }
        return result;
    }
    
    private List<String> getQueryValues(T item) {
        List<String> result = new ArrayList<>();
        getDatabaseFields().forEach((s) -> {
            for (Field f : c.getDeclaredFields()) {
                if (s.toLowerCase().equals(f.getName().toLowerCase())) {
                    try {
                        Field f_ = item.getClass().getDeclaredField(f.getName());
                        f_.setAccessible(true);
                        String value = f_.get(item).toString();
                        if(f.getType() == String.class) {
                            result.add("'" + value + "'");
                        } else{
                            result.add(value);
                        }
                    } catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        });
        return result;
    }
    
    private String getQueryValueString(T item) {
        return String.join(", ", getQueryValues(item));
    }

    public void add(T item) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                String columns = String.join(", ", getDatabaseFields());
                Statement st = conn.createStatement();
                String query = "INSERT INTO " + getTableName() + " (" + columns + ") VALUES (" + getQueryValueString(item) + ")";
                st.executeUpdate(query);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                closeConnection(conn);
            }
        }
    }
    
    public String update(T item) {
         Connection conn = getConnection();
        if (conn != null) {
            try {
                Field f = item.getClass().getDeclaredField("id");
                f.setAccessible(true);
                int id = f.getInt(item);
                
                List<String> updateStrings = new ArrayList<>();
                List<String> queryValues = getQueryValues(item);
                List<String> columns = getDatabaseFields();
                for (int i = 0; i < columns.size(); i++) {
                    updateStrings.add(columns.get(i) + " = " + queryValues.get(i));
                }
                
                Statement st = conn.createStatement();
                String query = "UPDATE " + getTableName() + " SET " + String.join(", ", updateStrings) + " WHERE id = " + id;
                st.executeUpdate(query);
            } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
                System.out.println(e.getMessage());
            } finally {
                closeConnection(conn);
            }
        }
        return "";
    }
    
    public List<T> getAll() {
        List<T> result = new ArrayList<>();
        Connection conn = getConnection();
        
        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                String s = "SELECT * FROM " + getTableName();
                ResultSet rs = st.executeQuery(s);
                
                while (rs.next()) {
                    try {
                        ResultSetMetaData md = rs.getMetaData();
                        T object = createContents();
                        for (Field f : c.getDeclaredFields()) {
                            for (int i = 1; i <= md.getColumnCount(); i++) {
                                if (f.getName().toLowerCase().equals(md.getColumnName(i).toLowerCase())) {
                                    //column exists
                                    f.setAccessible(true);
                                    f.set(object, rs.getObject(md.getColumnName(i)));
                                }
                            }
                        }
                        result.add(object);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                closeConnection(conn);
            }
        }
        
        return result;
    }
    
    public T get(int id) {
        T result = null;
        Connection conn = getConnection();
        
        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                String s = "SELECT * FROM " + getTableName() + " WHERE id = " + id;
                ResultSet rs = st.executeQuery(s);
                
                while (rs.next()) {
                    try {
                        ResultSetMetaData md = rs.getMetaData();
                        T object = createContents();
                        for (Field f : c.getDeclaredFields()) {
                            for (int i = 1; i <= md.getColumnCount(); i++) {
                                if (f.getName().toLowerCase().equals(md.getColumnName(i).toLowerCase())) {
                                    //column exists
                                    f.setAccessible(true);
                                    f.set(object, rs.getObject(md.getColumnName(i)));
                                }
                            }
                        }
                        result = object;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                closeConnection(conn);
            }
        }
        
        return result;
    }
    
    private Connection getConnection() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3306/hangman";
            String user = "root";
            String password = "root";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            // more processing here
            // ...    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            return conn;
        }
    }
    
    private final Class<T> c;
    
    public Dataprovider(Class<T> c) {
        this.c = c;
    }
    
    private T createContents() throws Exception {
        return c.newInstance();
    }
}
