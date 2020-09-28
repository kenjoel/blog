import models.News;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class generalTest {
    private static Connection conn;

    @BeforeClass //changed to @BeforeClass (run once before running any tests in this file)
    public static void setUp() throws Exception { //changed to static
        String connectionString = "jdbc:postgresql://localhost:5432/news_test"  ; //connect to postgres test database + "?sslmode=require"
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "://postgres"); //changed user and pass to null for mac users...Linux & windows need strings
        //open connection once before this test file is run
        conn = sql2o.open();
    }

    @After //run after every test
    public void tearDown() throws Exception {  //I have changed
        System.out.println("clearing database");
         //clear all restaurants after every test
         //clear all restaurants after every test
        //clear all restaurants after every test
    }

    @AfterClass //changed to @AfterClass (run once after all tests in this file completed)
    public static void shutDown() throws Exception{ //changed to static
        conn.close(); // close connection once after this entire test file is finished
        System.out.println("connection closed");
    }


    @Test
    public void newsInstanceWorks(){
       News news = new News("The king of persia commits suicide", "Today, the honoured king of persion was found dead in his hotel");
       assertTrue(news instanceof News);

    }

}