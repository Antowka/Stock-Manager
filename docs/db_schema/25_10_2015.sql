CREATE TABLE operation
(
    operation_id INT PRIMARY KEY NOT NULL,
    user_id INT NOT NULL,
    ticker_id INT NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    operation_type_id INT NOT NULL
);
CREATE TABLE operation_type
(
    operation_type_id SERIAL PRIMARY KEY NOT NULL,
    operation_type_name INT NOT NULL
);
CREATE TABLE price
(
    price_id SERIAL PRIMARY KEY NOT NULL,
    ticker_id INT NOT NULL,
    price_high DOUBLE PRECISION NOT NULL,
    price_open DOUBLE PRECISION NOT NULL,
    price_close DOUBLE PRECISION NOT NULL,
    price_low DOUBLE PRECISION NOT NULL,
    datetime TIMESTAMP NOT NULL
);
CREATE TABLE role
(
    role_id SERIAL PRIMARY KEY NOT NULL,
    role_name VARCHAR(20) NOT NULL
);
CREATE TABLE ticker
(
    ticker_id SERIAL PRIMARY KEY NOT NULL,
    ticker_type INT NOT NULL,
    ticker_name VARCHAR(50) NOT NULL,
    ticker_description VARCHAR NOT NULL,
    board_id VARCHAR(8) NOT NULL
);
CREATE TABLE ticker_type
(
    ticker_type_id SERIAL PRIMARY KEY NOT NULL,
    ticker_type_name VARCHAR(50) NOT NULL
);
CREATE TABLE user_role
(
    user_id INT NOT NULL,
    role_id INT NOT NULL
);
CREATE TABLE users
(
    user_id SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(60) NOT NULL,
    email VARCHAR(255) NOT NULL,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL
);
ALTER TABLE operation ADD FOREIGN KEY (operation_type_id) REFERENCES operation_type (operation_type_id);
ALTER TABLE operation ADD FOREIGN KEY (ticker_id) REFERENCES ticker (ticker_id);
ALTER TABLE operation ADD FOREIGN KEY (user_id) REFERENCES users (user_id);
CREATE UNIQUE INDEX unique_operation_type_id ON operation_type (operation_type_id);
ALTER TABLE price ADD FOREIGN KEY (ticker_id) REFERENCES ticker (ticker_id);
CREATE UNIQUE INDEX unique_price_id ON price (price_id);
CREATE UNIQUE INDEX unique_role_id ON role (role_id);
CREATE UNIQUE INDEX unique_role_name ON role (role_name);
ALTER TABLE ticker ADD FOREIGN KEY (ticker_type_id) REFERENCES ticker_type (ticker_type_id);
CREATE UNIQUE INDEX unique_ticker_id ON ticker (ticker_id);
CREATE UNIQUE INDEX unique_ticker_name ON ticker (ticker_name);
CREATE UNIQUE INDEX unique_ticker_type_id ON ticker_type (ticker_type_id);
ALTER TABLE user_role ADD FOREIGN KEY (role_id) REFERENCES role (role_id);
ALTER TABLE user_role ADD FOREIGN KEY (user_id) REFERENCES users (user_id);
CREATE UNIQUE INDEX unique_email ON users (email);
CREATE UNIQUE INDEX unique_user_id ON users (user_id);
CREATE UNIQUE INDEX unique_username ON users (username);
