DROP TABLE IF EXISTS order_items CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS stores CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- USERS

CREATE TABLE users (
    user_id BIGSERIAL PRIMARY KEY,

    username VARCHAR(100) NOT NULL,

    email VARCHAR(255) NOT NULL UNIQUE,

    password VARCHAR(255) NOT NULL,

    role VARCHAR(20) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- STORES
-- One seller owns one store

CREATE TABLE stores (
    store_id BIGSERIAL PRIMARY KEY,

    store_name VARCHAR(150) NOT NULL,

    description TEXT,

    seller_id BIGINT NOT NULL UNIQUE,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_store_seller
        FOREIGN KEY (seller_id)
        REFERENCES users(user_id)
        ON DELETE CASCADE
);

-- PRODUCTS

CREATE TABLE products (
    product_id BIGSERIAL PRIMARY KEY,

    product_name VARCHAR(255) NOT NULL,

    description TEXT,

    price NUMERIC(10,2) NOT NULL,

    stock_quantity INTEGER NOT NULL DEFAULT 0,

    category VARCHAR(50),

    store_id BIGINT NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_product_store
        FOREIGN KEY (store_id)
        REFERENCES stores(store_id)
        ON DELETE CASCADE
);

-- ORDERS

CREATE TABLE orders (
    order_id BIGSERIAL PRIMARY KEY,

    customer_id BIGINT NOT NULL,

    store_id BIGINT NOT NULL,

    total_amount NUMERIC(10,2) NOT NULL,

    status VARCHAR(30) NOT NULL DEFAULT 'PENDING',

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_order_customer
        FOREIGN KEY (customer_id)
        REFERENCES users(user_id),

    CONSTRAINT fk_order_store
        FOREIGN KEY (store_id)
        REFERENCES stores(store_id)
);

-- ORDER ITEMS

CREATE TABLE order_items (
    order_items_id BIGSERIAL PRIMARY KEY,

    order_id BIGINT NOT NULL,

    product_id BIGINT NOT NULL,

    quantity INTEGER NOT NULL,

    price NUMERIC(10,2) NOT NULL,

    CONSTRAINT fk_order_item_order
        FOREIGN KEY (order_id)
        REFERENCES orders(order_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_order_item_product
        FOREIGN KEY (product_id)
        REFERENCES products(product_id)
);

-- INDEXES

CREATE INDEX idx_users_email
ON users(email);

CREATE INDEX idx_products_store
ON products(store_id);

CREATE INDEX idx_orders_customer
ON orders(customer_id);

CREATE INDEX idx_orders_store
ON orders(store_id);

CREATE INDEX idx_order_items_order
ON order_items(order_id);

-- SAMPLE ENUM VALUES

-- ROLES:
-- ADMIN
-- SELLER
-- CUSTOMER

-- ORDER STATUS:
-- PENDING
-- CONFIRMED
-- SHIPPED
-- DELIVERED
-- CANCELLED

-- PRODUCT CATEGORIES:
-- ELECTRONICS
-- FASHION
-- BOOKS
-- SPORTS
-- OTHER