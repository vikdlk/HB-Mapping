package com.hibernate.onetomanyuni;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test_Connections {
    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/hb-04-one-to-many-uni?useSSL=false";
        String user = "hbstudent";
        String pass = "hbstudent";

        try {

            System.out.println("Connecting to dataBase: " + jdbcUrl);
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
            System.out.println("Connection successful!!");

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Connections fail");
        }
    }
}