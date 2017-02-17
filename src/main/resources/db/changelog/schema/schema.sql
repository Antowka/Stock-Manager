CREATE SEQUENCE tickers_id_seq START 1;
CREATE SEQUENCE transaction_types_id_seq START 1;
CREATE SEQUENCE transactions_id_seq START 1;

CREATE TABLE tickers
(
  id INTEGER DEFAULT nextval('tickers_id_seq'::regclass) PRIMARY KEY NOT NULL,
  name VARCHAR(10) NOT NULL,
  short_description TEXT,
  full_description TEXT,
  isin VARCHAR(20)
);
CREATE TABLE transaction_types
(
  id INTEGER DEFAULT nextval('transaction_types_id_seq'::regclass) PRIMARY KEY NOT NULL,
  name VARCHAR(4) NOT NULL
);
CREATE TABLE transactions
(
  id INTEGER DEFAULT nextval('transactions_id_seq'::regclass) PRIMARY KEY NOT NULL,
  type_id INTEGER NOT NULL,
  date TIMESTAMP,
  comment TEXT,
  ticker_id INTEGER NOT NULL,
  price DOUBLE PRECISION NOT NULL,
  amount INTEGER DEFAULT 0
);
CREATE UNIQUE INDEX "Tickers_id_uindex" ON tickers (id);
CREATE UNIQUE INDEX "Tickers_name_uindex" ON tickers (name);
CREATE UNIQUE INDEX transaction_types_id_uindex ON transaction_types (id);
CREATE UNIQUE INDEX transaction_types_name_uindex ON transaction_types (name);
ALTER TABLE transactions ADD FOREIGN KEY (type_id) REFERENCES transaction_types (id);
ALTER TABLE transactions ADD FOREIGN KEY (ticker_id) REFERENCES tickers (id);
CREATE UNIQUE INDEX transactions_id_uindex ON transactions (id);


