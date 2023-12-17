package com.ensa.srisearcher.utils;

import com.ensa.srisearcher.algorithms.DataStore;
import com.ensa.srisearcher.utils.serializables.SerializableHashSet;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Converter{
    public static String serializeObject(Object obj) throws IOException {
        return CompressedSerializer.compressAndSerialize(obj);
    }

    public static Object deserializeObject(String str) throws IOException, ClassNotFoundException {
        return CompressedSerializer.decompressAndDeserialize(str);
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
                System.out.println("Data was found");
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

    public static Set<Integer> search(String query) {
        DataStore dataStore= Converter.getDataStore();
        System.out.println("Indexes: " + dataStore.getIndex());
        return dataStore.index.getOrDefault(query.toLowerCase(), new SerializableHashSet<>());
    }

    public static void addDocument(int documentId, List<String> words) {
        DataStore dataStore= Converter.getDataStore();
        for (String word : words) {
            dataStore.index.putIfAbsent(word.toLowerCase(), new SerializableHashSet<>());
            SerializableHashSet<Integer> docIds=dataStore.index.getOrDefault(word.toLowerCase(), new SerializableHashSet<>());
            docIds.add(documentId);
            dataStore.index.put(word.toLowerCase(), docIds);
        }
        System.out.println("The index data:\n" + dataStore.index);
        Converter.update(dataStore);

    }


}
