CREATE DATABASE news;
\c news;
CREATE TABLE  users(id SERIAL PRIMARY KEY, name VARCHAR, position VARCHAR, role VARCHAR, department VARCHAR);
CREATE TABLE department(id SERIAL PRIMARY KEY, departmentname VARCHAR, description VARCHAR, numberofemployees INTEGER,departmentid INTEGER);
CREATE TABLE news_col(id SERIAL PRIMARY KEY, type VARCHAR, title VARCHAR, content VARCHAR, departmentId int );

CREATE DATABASE news_test WITH TEMPLATE news;


