ALTER TABLE products_table CHANGE active available boolean;

UPDATE products_table set available = true;