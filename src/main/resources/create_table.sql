CREATE TABLE IF NOT EXISTS client
(
    id_client     IDENTITY NOT NULL,
    fio           VARCHAR  NOT NULL UNIQUE,
    passport_numb VARCHAR  NOT NULL,
    PRIMARY KEY (id_client)
    );
CREATE TABLE IF NOT EXISTS counterparty
(
    id_counterparty IDENTITY NOT NULL,
    name            VARCHAR  NOT NULL UNIQUE,
    inn_number      VARCHAR  NOT NULL,
    PRIMARY KEY (id_counterparty)
    );
CREATE TABLE IF NOT EXISTS bank_account_client
(
    id_account_cl IDENTITY NOT NULL,
    account_number       VARCHAR  NOT NULL,
    id_client     BIGINT,
    balance       BIGINT   NOT NULL,
    active        BOOLEAN DEFAULT FALSE,
    open_date     DATE,
    FOREIGN KEY (id_client) REFERENCES client (id_client),
    PRIMARY KEY (id_account_cl)

    );
CREATE TABLE IF NOT EXISTS bank_account_counterparty
(
    id_account_cou  IDENTITY NOT NULL,
    account_number         VARCHAR  NOT NULL,
    id_counterparty BIGINT,
    balance         BIGINT   NOT NULL,
    active          BOOLEAN DEFAULT FALSE,
    open_date       DATE,
    FOREIGN KEY (id_counterparty) REFERENCES counterparty (id_counterparty),
    PRIMARY KEY (id_account_cou)
    );
CREATE TABLE IF NOT EXISTS bank_card
(
    id_card           IDENTITY NOT NULL,
    card_number       VARCHAR  NOT NULL UNIQUE ,
    id_account_client BIGINT   NOT NULL,
    active            BOOLEAN DEFAULT FALSE,
    open_date         DATE,
    FOREIGN KEY (id_account_client) REFERENCES bank_account_client (id_account_cl),
    PRIMARY KEY (id_card)
    );

