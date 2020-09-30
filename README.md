# BLOG


### Description
This is an Organizational Rest API that queries and retrieves news both General and organizational.

## Features
A user visiting the news portal Api site will be able to:

1. Add New Departments & and also view local departments present.
2. Adding Users to Departments.
3. Add news in a particular department and view the news under the particular department.

## Prerequisites & Setup
- Use of version control. ie. Git
- Java SDK & Environment.
- Java IntelliJIDEA Community.
- Gradle.
- PostgreSQL

## Expected Behaviour. (USERS' GUIDE)
### View Main Landing Page.
* Method: GET
* Route: /
This is the empty path that introduces user to the API
- Returns an empty welcome message

### View Departments
* Method: GET
* Route: `/departments`
---
- Returns an array of all your departments

### View Departments by ID
* Method: GET
* Route: `/departments/:id`
---
- Returns the particular department

### View News for particular department
* Method: GET
* Route: `/departments/:id/news`

- Returns news for particular department

### View Users by ID
* Method: GET
* Route: `/users/:id`

- Retrieve users by ID

## Technologies Used
- Java
- Java Spark*
- PostgreSQL
- Postman

## Bugs
~ At the moment there are no known bugs, but that is as if you come across any bug or functionality that fails, please feel free to reach out.

## Demo

## By
**Ken Joel Muigai**

## LICENSE
Copyright 2020 K. J. M

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.