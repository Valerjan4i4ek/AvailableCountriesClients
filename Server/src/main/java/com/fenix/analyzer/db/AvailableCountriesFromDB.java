package com.fenix.analyzer.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AvailableCountriesFromDB {
    private final static String fileName = "database.properties";

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public AvailableCountriesFromDB(){

    }

    public Connection getConnection() throws SQLException {
        Properties props = new Properties();
        try(InputStream in = getClass().getClassLoader().getResourceAsStream(fileName)){
            if(in != null){
                props.load(in);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }

    public Map<String, String> getAppIdAndPlatform(int userId){
        Map<String, String> mapAppIdAndPlatform = new HashMap<>();

        try{
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try{
                conn = getConnection();
                String query = "SELECT * FROM apps WHERE userId = ?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, userId);
                rs = ps.executeQuery();

                while (rs.next()){
                    String appId = rs.getString("appId");
                    String platform = rs.getString("platform");
                    mapAppIdAndPlatform.put(appId, platform);
                }
            } finally {
                connectionClose(conn, ps);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return mapAppIdAndPlatform;
    }

    public void connectionClose(Connection conn, Statement st){
        try{
            if(conn != null){
                conn.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        try{
            if(st != null){
                st.close();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
