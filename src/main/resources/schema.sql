--DROP SEQUENCES AND TABLES
DROP SEQUENCE IF EXISTS HIBERNATE_SEQUENCE;

DROP TABLE IF EXISTS BOOK CASCADE CONSTRAINTS;
DROP TABLE IF EXISTS TAG CASCADE CONSTRAINTS;
DROP TABLE IF EXISTS TAG CASCADE CONSTRAINTS;
DROP TABLE IF EXISTS BATCH_FILE CASCADE CONSTRAINTS;

--CREATE SEQUENCES AND TABLES
--Used of JPA AUTO ID STRATEGY
CREATE SEQUENCE HIBERNATE_SEQUENCE
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

--Table for Book
CREATE TABLE BOOK(
 IBSN VARCHAR(50) NOT NULL PRIMARY KEY,
 NAME TEXT NOT NULL,
 AUTHOR TEXT NOT NULL
);

--Table for Tags (Master Table)
CREATE TABLE TAG(
 ID NUMBER(5) NOT NULL PRIMARY KEY,
 NAME TEXT NOT NULL
);

--Mapping Table for Book and Tags
CREATE TABLE BOOK_TAGS(
 BOOK_ID VARCHAR(50) NOT NULL,
 TAG_ID NUMBER(5) NOT NULL,
 PRIMARY KEY (BOOK_ID, TAG_ID)
);

--Batch File Meta Data Table
CREATE TABLE BATCH_FILE(
 ID VARCHAR(20) NOT NULL PRIMARY KEY,
 NAME VARCHAR(150) NOT NULL,
 CONTENT_SIZE BIGINT NOT NULL,
 SOURCE_SYSTEM_ID VARCHAR(50) NOT NULL,
 START_TIME TIMESTAMP NOT NULL,
 END_TIME TIMESTAMP NOT NULL
);