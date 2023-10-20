DROP TABLE IF EXISTS PROD_CATEGORY CASCADE;
DROP TABLE IF EXISTS PRODUCT CASCADE;

CREATE TABLE IF NOT EXISTS PROD_CATEGORY (
    ID                 BIGSERIAL NOT NULL,
    NAME               VARCHAR (250) NOT NULL UNIQUE ,
    PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS PRODUCT (
    ID                 BIGSERIAL NOT NULL UNIQUE,
    NAME               VARCHAR (250) NOT NULL,
    SHORT_DESC         VARCHAR (2500) NOT NULL,
    DESCRIPTION        VARCHAR (10000) NOT NULL,
    CATEGORY_ID        INTEGER NOT NULL REFERENCES PROD_CATEGORY (ID),
    PRICE              NUMERIC (10, 2),
    PRIMARY KEY (ID)
);