package dao;

import models.Departments;
import models.News;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oDepartmentDaoTest {

        private static Sql2onewsDao newsDao; //these variables are now static.
        private static Sql2oDepartmentDao departmentDao;
        private static Connection conn;

        @BeforeClass //changed to @BeforeClass (run once before running any tests in this file)
        public static void setUp() throws Exception { //changed to static
            String connectionString = "jdbc:postgresql://localhost:5432/news_test"; //connect to postgres test database
            Sql2o sql2o = new Sql2o(connectionString, "moringa", "://postgres"); //changed user and pass to null for mac users...Linux & windows need strings
            newsDao = new Sql2onewsDao(sql2o);
            departmentDao = new Sql2oDepartmentDao(sql2o);
            conn =sql2o.open(); //open connection once before this test file is run
        }

        @After //run after every test
        public void tearDown() throws Exception {  //I have changed
            System.out.println("clearing database");
            newsDao.clearAll();
            departmentDao.clearAll();
            //clear all items after every test
        }

        @AfterClass //changed to @AfterClass (run once after all tests in this file completed)
        public static void shutDown() throws Exception{ //changed to static
            departmentDao.clearAll();
            newsDao.clearAll();
            conn.close(); // close connection once after this entire test file is finished
            System.out.println("connection closed");
        }

        @Test
        public void savingDepartmentSetsId(){
            Departments departments = setupDepartment();
            assertEquals(1, departmentDao.getAll().size());
        }


        @Test
        public void testGetAllMethod(){
            Departments departments = setupDepartment();
            Departments departments1 = setupDepartment();
            assertEquals(2, departmentDao.getAll().size());
        }

        @Test
        public void addingNewsToDepartmentWorks(){
            Departments departments = setupDepartment();
            departmentDao.addDepartment(departments);

            News news = setupDepartmentNews();
            newsDao.add(news);
            departmentDao.addNewstoDepartment(departments, news);
            assertEquals(1, departmentDao.getNewsByDepartment(departments.getId()));
        }


        public News setupNews(){
            News news = new News("The Great White","The great white shark",8);
            return news;
        }

        public News setupDepartmentNews (){
            News news = new News(" Omena", "214 NE Safaricom", 1,2);
            newsDao.add(news);
            return news;
        }

        public Departments setupDepartment (){
            Departments departments = new Departments("Fish Omena", "214 NE Safaricom", 5);
            departmentDao.addDepartment(departments);
            return departments;
        }


}