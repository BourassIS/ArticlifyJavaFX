package com.ensa.srisearcher.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private Connection connection;

    public Connection getCon() {
        return  connection;
    }

    public void setCon(Connection connection) {
        this.connection = connection;
    }

    public ConnectionDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yocto?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
            System.out.println("Connection OK");
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Add this method to close the connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
