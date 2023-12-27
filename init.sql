-- Create database if not exists
CREATE DATABASE IF NOT EXISTS yocto;

USE yocto;

-- Drop table if it exists
DROP TABLE IF EXISTS data_store_table;

-- Table for storing the current store
CREATE TABLE data_store_table (
    data TEXT NOT NULL
);