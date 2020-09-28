CREATE DATABASE news;
\c news;
CREATE TABLE  users(id SERIAL PRIMARY KEY, name VARCHAR, position VARCHAR, role VARCHAR, department VARCHAR);
CREATE TABLE department(id SERIAL PRIMARY KEY, departmentname VARCHAR, description VARCHAR, numberofemployees INTEGER,departmentid INTEGER);
CREATE TABLE news(id SERIAL PRIMARY KEY, title VARCHAR, content VARCHAR, type VARCHAR);


