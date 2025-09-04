-- Create additional test database schema
CREATE DATABASE IF NOT EXISTS test_psic;

-- You can also create tables or set up any initial data here if needed
USE test_psic;
CREATE TABLE IF NOT EXISTS test_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Insert some initial data for testing purposes
INSERT INTO test_table (name) VALUES ('Test Data 1'), ('Test Data 2');
