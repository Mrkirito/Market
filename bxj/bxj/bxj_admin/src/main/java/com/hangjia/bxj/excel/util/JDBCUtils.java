package com.hangjia.bxj.excel.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Administrator on 2017/7/25.
 */
public class JDBCUtils {
    // 连接数据库的参数
    private static String url = "jdbc:mysql://112.124.98.245:3306/bxj?useUnicode=true&autoReconnect=true&characterEncoding=UTF-8";
    private static String user = "root";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String password = "2016_bxj_test";

    private JDBCUtils() {

    }

    private static JDBCUtils instance = null;

    public static JDBCUtils getInstance() {
        if (instance == null) {
            synchronized (JDBCUtils.class) {
                if (instance == null) {
                    instance = new JDBCUtils();
                }

            }
        }

        return instance;
    }

    // 注册驱动
    static {
        try {
            Class.forName(driver);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 该方法获得连接
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // 释放资源
    public void free(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {

                e.printStackTrace();
            } finally {
                if (st != null) {
                    try {
                        st.close();
                    } catch (SQLException e) {

                        e.printStackTrace();
                    } finally {
                        if (conn != null) {
                            try {
                                conn.close();
                            } catch (SQLException e) {

                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

}
