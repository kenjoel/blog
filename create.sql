CREATE DATABASE news;
\c news;
CREATE TABLE  users(id SERIAL PRIMARY KEY, name VARCHAR, position VARCHAR, role VARCHAR, department VARCHAR);
CREATE TABLE departments(id SERIAL PRIMARY KEY, name VARCHAR, description VARCHAR, size int);
CREATE TABLE news_col(id SERIAL PRIMARY KEY, title VARCHAR, includes VARCHAR, userid int );
CREATE TABLE department_news(id SERIAL PRIMARY KEY, newsid INTEGER, departmentid INTEGER);

CREATE DATABASE news_test WITH TEMPLATE news;


