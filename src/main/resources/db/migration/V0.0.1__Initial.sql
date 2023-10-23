CREATE TABLE products
(
    sku         VARCHAR(16)     NOT NULL
        CONSTRAINT pk_product_id PRIMARY KEY,
    name        VARCHAR(125)    NOT NULL,
    description VARCHAR(125),
    price       DECIMAL           NOT NULL,
    created_at  TIMESTAMP     NOT NULL,
    updated_at  TIMESTAMP     NOT NULL
);


INSERT INTO products (sku, name, description, price, created_at, updated_at)
VALUES ('123', 'Product 1', 'Description 1', 10.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (sku, name, description, price, created_at, updated_at)
VALUES ('4567', 'Product 2', 'Description 2', 20.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (sku, name, description, price, created_at, updated_at)
VALUES ('8901', 'Product 3', 'Description 3', 30.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (sku, name, description, price, created_at, updated_at)
VALUES ('2345', 'Product 4', 'Description 4', 40.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (sku, name, description, price, created_at, updated_at)
VALUES ('67789', 'Product 5', 'Description 5', 50.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);