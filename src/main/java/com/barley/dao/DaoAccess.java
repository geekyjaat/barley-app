//package com.barley.dao;
//
//import com.barley.model.Author;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DaoAccess {
//
//    private final String GET_AUTHORS = "SELECT * FROM public.authors";
//
//    public DaoAccess() {
//        prepareDb();
//    }
//
//    public static Connection getConnection() throws URISyntaxException, SQLException {
//        URI dbUri = new URI(System.getenv("DATABASE_URL"));
//        String username = dbUri.getUserInfo().split(":")[0];
//        String password = dbUri.getUserInfo().split(":")[1];
//        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
//        System.out.println(dbUrl);
//        return DriverManager.getConnection(dbUrl, username, password);
//    }
//
//    private void prepareDb() {
//        try {
//            InputStream is = ClassLoader.getSystemResourceAsStream("db/db.sql");
//            InputStreamReader inputstreamreader = new InputStreamReader(is);
//            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
//            String statement = "";
//            String line;
//            while ((line = bufferedreader.readLine()) != null) {
//                statement += line;
//            }
//            Statement st = getConnection().createStatement();
//            int success = st.executeUpdate(statement);
//            System.out.println(success);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}
