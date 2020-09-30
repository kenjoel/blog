CREATE DATABASE news;
\c news;
CREATE TABLE  users(id SERIAL PRIMARY KEY, name VARCHAR, position VARCHAR, role VARCHAR, departmentId int);
CREATE TABLE departments(id SERIAL PRIMARY KEY, name VARCHAR, description VARCHAR, phone VARCHAR, email VARCHAR );
CREATE TABLE news_col(id SERIAL PRIMARY KEY, title VARCHAR, content VARCHAR, author VARCHAR, departmentid int );
CREATE TABLE department_news(id SERIAL PRIMARY KEY, departmetid INTEGER, newsid INTEGER);

CREATE DATABASE news_test WITH TEMPLATE news;


