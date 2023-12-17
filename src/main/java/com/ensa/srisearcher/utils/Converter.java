package com.ensa.srisearcher.utils;

import com.ensa.srisearcher.algorithms.DataStore;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

public class Converter{
    public static String serializeObject(Object obj) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(obj);
            oos.flush();
            return Base64.getEncoder().encodeToString(bos.toByteArray());
        }
    }

    public static Object deserializeObject(String str) throws IOException, ClassNotFoundException {
        byte[] bytes = Base64.getDecoder().decode(str);
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            return ois.readObject();
        }
    }


    public static boolean update(DataStore dataStore) {
        ConnectionDB conDb = new ConnectionDB();

        try {
            // Delete existing record
            try (PreparedStatement deletePs = conDb.getCon().prepareStatement("DELETE FROM data_store_table")) {
                deletePs.executeUpdate();
            }

            // Insert new data
            try (PreparedStatement insertPs = conDb.getCon().prepareStatement("INSERT INTO data_store_table (data) VALUES (?)")) {
                String serializedData = Converter.serializeObject(dataStore);
                insertPs.setString(1, serializedData);
                insertPs.executeUpdate();
            }

            System.out.println("Database update successful");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database update failed");
            return false;
        }
    }



    public static DataStore getDataStore() {
        ConnectionDB conDb = new ConnectionDB();

        try (PreparedStatement ps = conDb.getCon().prepareStatement("SELECT data FROM data_store_table")) {
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String serializedData = rs.getString("data");
                System.out.println("DataStore from getDataStore\n" + (DataStore) Converter.deserializeObject(serializedData));

                return (DataStore) Converter.deserializeObject(serializedData);
            } else {
                System.out.println("No data found in data_store_table");
                update(new DataStore());
                return new DataStore();
            }
        } catch (Exception e) {
            e.printStackTrace();
            update(new DataStore());
            return new DataStore();
        }
    }





}
