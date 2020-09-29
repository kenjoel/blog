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
    public void addingFoodSetsId() throws Exception {
        News testNews = setupNews();
        int originalnewstypeId = testNews.getId();
        newsDao.add(testNews);
        assertNotEquals(originalnewstypeId,testNews.getId());
    }

    @Test
    public void addedNewsAreReturnedFromGetAll() throws Exception {
        News testNewstype = setupNews();
        newsDao.add(testNewstype);
        assertEquals(1, newsDao.getAll().size());
    }

    @Test
    public void noNewstypesReturnsEmptyList() throws Exception {
        assertEquals(0, newsDao.getAll().size());
    }

    @Test
    public void deleteByIdDeletesCorrectNewstype() throws Exception {
        News news = setupDepartmentNews();
        newsDao.add(news);
        newsDao.deleteById(news.getId());
        assertEquals(0, newsDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        News testNewstype = setupNews();
        newsDao.add(testNewstype);
        News otherNewstype = setupDepartmentNews();
        newsDao.add(otherNewstype);
        newsDao.clearAll();
        assertEquals(0, newsDao.getAll().size());
    }

    @Test
    public void addFoodTypeToRestaurantAddsTypeCorrectly() throws Exception {

        News testNews = setupNews();
        News altNews = setupDepartmentNews();

        newsDao.add(testNews);
        newsDao.add(altNews);

        Departments departments = setupDepartment();

        departmentDao.addDepartment(departments);

        newsDao.addNewsToDepartment(testNews, departments);
        departmentDao.addNewstoDepartment(altNews, departments);

        assertEquals(2, departmentDao.getNewsByDepartment(departments.getId()).size());
    }

    @Test
    public void deleteingRestaurantAlsoUpdatesJoinTable() throws Exception {
        News news  = setupNews();
        newsDao.add(news);

        Departments testDepartment = setupDepartment();
        departmentDao.addDepartment(testDepartment);

        News altNews = setupDepartmentNews();
        newsDao.add(altNews);

        newsDao.addNewsToDepartment(altNews,testDepartment);
        departmentDao.addNewstoDepartment(news, testDepartment);

//        departmentDao.deleteById(testDepartment.getId());
        assertEquals(0, newsDao.findNewsByDepartmentId(testDepartment.getId()).size());
    }

    // helpers

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
        Departments departments = new Departments("Fish Omena", "214 NE Safaricom", 8);
        departmentDao.addDepartment(departments);
        return departments;
    }


//    oduor.lovine@gmail.com
//    gokumu12@gmail.com
//    ianwilbuts@gmail.com
//
//    0719127217- lovine
//    0700643549 - Ian wilbert


}