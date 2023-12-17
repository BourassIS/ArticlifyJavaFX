package com.ensa.srisearcher.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressedSerializer {

    public static String compressAndSerialize(Object obj) throws IOException {
        // Serialize the object
        byte[] serializedBytes = serialize(obj);

        // Compress the serialized data
        byte[] compressedBytes = compress(serializedBytes);

        // Convert the compressed data to a string
        return new String(compressedBytes, StandardCharsets.ISO_8859_1);
    }

    public static Object decompressAndDeserialize(String compressedData) throws IOException, ClassNotFoundException {
        // Convert the compressed string to bytes
        byte[] compressedBytes = compressedData.getBytes(StandardCharsets.ISO_8859_1);

        // Decompress the data
        byte[] decompressedBytes = decompress(compressedBytes);

        // Deserialize the decompressed data
        return deserialize(decompressedBytes);
    }

    private static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(obj);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            return objectInputStream.readObject();
        }
    }

    private static byte[] compress(byte[] data) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream)) {
            gzipOutputStream.write(data);
        }
        return outputStream.toByteArray();
    }

    private static byte[] decompress(byte[] compressedData) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(compressedData))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = gzipInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        return outputStream.toByteArray();
    }

}
