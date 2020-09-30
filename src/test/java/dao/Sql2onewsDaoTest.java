package dao;

import models.Departments;
import models.News;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Sql2onewsDaoTest {

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
        //clear all restaurants after every test
    }

    @AfterClass //changed to @AfterClass (run once after all tests in this file completed)
    public static void shutDown() throws Exception{ //changed to static
       conn.close(); // close connection once after this entire test file is finished
        System.out.println("connection closed");
    }

    @Test
    public void addingnewsetsId() throws Exception {
        News testNews = setupNews();
        assertEquals(1, testNews.getId());
    }

    @Test
    public void getAll() throws Exception {
        News news1 = setupNews();
        News news2 = setupNews();
        assertEquals(2, newsDao.getAll().size());
    }

    @Test
    public void getAllNewsByDepartment() throws Exception {
        Departments testDepartment = setupDepartment();
        Departments otherDepartment = setupDepartment(); //add in some extra data to see if it interferes
        News news1 = setupNewsForDepartment(testDepartment);
        News news2 = setupNewsForDepartment(testDepartment);
        News newsForOtherDepartment = setupNewsForDepartment(otherDepartment);
        assertEquals(2, newsDao.getAllNewsByDepartment(testDepartment.getId()).size());
    }

    @Test
    public void deleteById() throws Exception {
        News testNews = setupNews();
        News otherNews = setupNews();
        assertEquals(2, newsDao.getAll().size());
        newsDao.deleteById(testNews.getId());
        assertEquals(1, newsDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        News testNews = setupNews();
        News otherNews = setupNews();
        newsDao.clearAll();
        assertEquals(0, newsDao.getAll().size());
    }

    //helpers

    public News setupNews() {
        News news = new News("Kid Gets Whacked", "Kimani", "Joel", 555);
        newsDao.add(news);
        return news;
    }

    public News setupNewsForDepartment(Departments department) {
        News news = new News("Kid Gets Whacked", "Kimani", "Joel", department.getId());
        newsDao.add(news);
        return news;
    }

    public Departments setupDepartment() {
        Departments department = new Departments("DailyNation","Fighting Corruption", "www.DailyNation.com", "DailyNation@nation.com");
        departmentDao.add(department);
        return department;
    }
//    oduor.lovine@gmail.com
//    gokumu12@gmail.com
//    ianwilbuts@gmail.com
//
//    0719127217- lovine
//    0700643549 - Ian wilbert


}