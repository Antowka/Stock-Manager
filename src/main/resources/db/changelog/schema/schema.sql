CREATE SEQUENCE tickers_id_seq START 1;
CREATE SEQUENCE transaction_types_id_seq START 1;
CREATE SEQUENCE transactions_id_seq START 1;
CREATE SEQUENCE price_id_seq START 1;

CREATE TABLE tickers
(
  id INTEGER DEFAULT nextval('tickers_id_seq'::regclass) PRIMARY KEY NOT NULL,
  name VARCHAR(10) NOT NULL,
  short_description TEXT,
  full_description TEXT,
  isin VARCHAR(20),
  last_update_price TIMESTAMP
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

CREATE TABLE price
(
    id BIGINT DEFAULT nextval('price_id_seq'::regclass) PRIMARY KEY NOT NULL,
    close REAL,
    date TIMESTAMP,
    high REAL,
    low REAL,
    open REAL,
    prev_price REAL,
    volume BIGINT,
    ticker_id BIGINT,
    CONSTRAINT fkcslmjbv3wo8uely5ug5w5tt1 FOREIGN KEY (ticker_id) REFERENCES tickers (id)
);

CREATE UNIQUE INDEX "Tickers_id_uindex" ON tickers (id);
CREATE UNIQUE INDEX "Tickers_name_uindex" ON tickers (name);
CREATE UNIQUE INDEX transaction_types_id_uindex ON transaction_types (id);
CREATE UNIQUE INDEX transaction_types_name_uindex ON transaction_types (name);
ALTER TABLE transactions ADD FOREIGN KEY (type_id) REFERENCES transaction_types (id);
ALTER TABLE transactions ADD FOREIGN KEY (ticker_id) REFERENCES tickers (id);
CREATE UNIQUE INDEX transactions_id_uindex ON transactions (id);


