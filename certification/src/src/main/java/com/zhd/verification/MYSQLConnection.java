package com.zhd.verification;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MYSQLConnection {

    private  final String  URL ="jdbc:mysql://192.168.200.217:8306/gridtest?characterEncoding=utf8&useSSL=false";
     Connection getConnection(){

        Connection conn = null;
        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
            conn =  DriverManager.getConnection(URL,"root","cmcc_123456");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return conn;

    }


}
