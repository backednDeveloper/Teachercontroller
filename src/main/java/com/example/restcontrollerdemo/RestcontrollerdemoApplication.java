//package com.example.restcontrollerdemo;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import java.sql.*;
//
////@SpringBootApplication
//public class RestcontrollerdemoApplication {
//
//    public static void main(String[] args) {
//
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Teacher", "root", "Trako123.");
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM teacher");
//            ResultSetMetaData metaData = resultSet.getMetaData();
//
//            int columnCount = metaData.getColumnCount();
//            for (int i = 1; i <= columnCount; i++) {
//                System.out.print(metaData.getColumnName(i) + " ");
//            }
//            System.out.println();
//
//            while (resultSet.next()) {
//                for (int i = 1; i <= columnCount; i++) {
//                    System.out.print(resultSet.getString(i) + " ");
//                }
//                System.out.println();
//            }
//
//            resultSet.close();
//            statement.close();
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//
//    }
//}
