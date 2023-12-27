package com.ensa.srisearcher.utils;

import com.ensa.srisearcher.algorithms.DataStore;
import com.ensa.srisearcher.utils.serializables.SerializableHashMap;
import com.ensa.srisearcher.utils.serializables.SerializableHashSet;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Converter{
    //public static HashMap<String, SerializableHashSet<Integer>> index=new SerializableHashMap<>();
    public static String serializeObject(Object obj) throws IOException {
        return CompressedSerializer.compressAndSerialize(obj);
    }

    public static Object deserializeObject(String str) throws IOException, ClassNotFoundException {
        return CompressedSerializer.decompressAndDeserialize(str);
    }

    public static boolean update(DataStore dataStore) {
        ConnectionDB conDb = new ConnectionDB();
        dataStore.docId=Converter.getDataStore().getDocId()+1;
        System.out.println("The received index inside update is: "+dataStore.getIndex());
        try {
            // Delete existing record
            try (PreparedStatement deletePs = conDb.getCon().prepareStatement("DELETE FROM yocto.data_store_table")) {
                deletePs.executeUpdate();
            }

            // Insert new data
            try (PreparedStatement insertPs = conDb.getCon().prepareStatement("INSERT INTO yocto.data_store_table (data) VALUES (?)")) {
                String serializedData = Converter.serializeObject(dataStore);
                insertPs.setString(1, serializedData);
                insertPs.executeUpdate();
            }

            System.out.println("Database update successful");
            conDb.closeConnection();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database update failed");
            conDb.closeConnection();
            return false;
        }
    }



    public static DataStore getDataStore() {
        ConnectionDB conDb = new ConnectionDB();
        try (PreparedStatement ps = conDb.getCon().prepareStatement("SELECT data FROM yocto.data_store_table")) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String serializedData = rs.getString("data");
                System.out.println("Data was found");
                conDb.closeConnection();
                return (DataStore) Converter.deserializeObject(serializedData);
            } else {
                System.out.println("No data found in data_store_table");
                conDb.closeConnection();
                update(new DataStore());
                return new DataStore();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //update(new DataStore());
            conDb.closeConnection();
            return new DataStore();
        }
    }

    public static Set<Integer> search(String query) {
        DataStore dataStore= Converter.getDataStore();
        return dataStore.index.getOrDefault(query.toLowerCase(), new SerializableHashSet<>());
    }

    public static DataStore addDocument(int documentId, List<String> words) {
        DataStore dataStore = Converter.getDataStore();
        System.out.println("The old index is:\n" + dataStore.getIndex());
        for (String word : words) {
            String lowercaseWord = word.toLowerCase();
            SerializableHashSet<Integer> docIds = dataStore.index.computeIfAbsent(lowercaseWord, k -> new SerializableHashSet<>());
            docIds.add(documentId);
            dataStore.index.put(lowercaseWord, docIds);
        }

        System.out.println("The Available Indexes:\n" + Converter.getDataStore().getIndex());
        return  dataStore;
    }


}
