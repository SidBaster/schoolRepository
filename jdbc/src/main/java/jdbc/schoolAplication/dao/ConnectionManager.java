package jdbc.schoolAplication.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jdbc.schoolAplication.exception.DaoException;
import jdbc.schoolAplication.util.PropertiesUtil;

public final class ConnectionManager {
    private static final String URL = "db.url";
    private static final String NAME = "db.user";
    private static final String PASSWORD = "db.password";
    
    public static Connection open() {
        try {
            return DriverManager.getConnection(PropertiesUtil.get(URL),
                PropertiesUtil.get(NAME),
                PropertiesUtil.get(PASSWORD));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private ConnectionManager() {} 
 }
