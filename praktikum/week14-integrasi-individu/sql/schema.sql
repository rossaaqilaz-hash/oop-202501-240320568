-- Database: agripos
-- Week 14: Integrasi Individu

-- Create database (run this separately in psql or pgAdmin)
-- CREATE DATABASE agripos;

-- Connect to agripos database first, then run:

-- Drop table if exists
DROP TABLE IF EXISTS products CASCADE;

-- Create products table
CREATE TABLE products (
    code VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE PRECISION NOT NULL CHECK (price > 0),
    stock INT NOT NULL CHECK (stock >= 0)
);

-- Insert sample data
INSERT INTO products (code, name, price, stock) VALUES
('BNH-001', 'Benih Padi Unggul', 25000, 100),
('BNH-002', 'Benih Jagung', 30000, 75),
('BNH-003', 'Benih Kedelai', 20000, 150),
('PUP-001', 'Pupuk Organik', 50000, 50),
('PUP-002', 'Pupuk NPK', 75000, 40),
('PUP-003', 'Pupuk Urea', 60000, 80),
('PES-001', 'Pestisida Nabati', 75000, 30),
('PES-002', 'Insektisida', 85000, 25),
('ALT-001', 'Cangkul', 150000, 20),
('ALT-002', 'Sabit', 85000, 35);

-- Verify data
SELECT * FROM products ORDER BY code;

-- Query examples for testing
SELECT * FROM products WHERE code = 'BNH-001';
SELECT * FROM products WHERE price > 50000;
SELECT COUNT(*) as total_products FROM products;
SELECT SUM(stock) as total_stock FROM products;