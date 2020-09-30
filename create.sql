CREATE DATABASE news;
\c news;
CREATE TABLE  users(id SERIAL PRIMARY KEY, name VARCHAR, position VARCHAR, role VARCHAR, department VARCHAR);
CREATE TABLE departments(id SERIAL PRIMARY KEY, name VARCHAR, description VARCHAR, phone int);
CREATE TABLE news_col(id SERIAL PRIMARY KEY, title VARCHAR, includes VARCHAR, userid int );
CREATE TABLE department_news(id SERIAL PRIMARY KEY, departmetid INTEGER, newsid INTEGER);

CREATE DATABASE news_test WITH TEMPLATE news;

--SET MODE PostgreSQL;
--
--CREATE TABLE IF NOT EXISTS departments (
-- id int PRIMARY KEY auto_increment,
-- name VARCHAR,
-- about VARCHAR,
-- website VARCHAR,
-- email VARCHAR
--);
--
--CREATE TABLE IF NOT EXISTS users (
-- id int PRIMARY KEY auto_increment,
-- name VARCHAR,
-- position VARCHAR,
-- roles VARCHAR
--);
--
--CREATE TABLE IF NOT EXISTS news (
-- id int PRIMARY KEY auto_increment,
-- headline VARCHAR,
-- content VARCHAR,
-- author VARCHAR,
-- departmentid INTEGER,
-- createdat LONG
--);
--
--CREATE TABLE IF NOT EXISTS departments_users (
-- id int PRIMARY KEY auto_increment,
-- Userid INTEGER,
-- departmentid INTEGER
--);


