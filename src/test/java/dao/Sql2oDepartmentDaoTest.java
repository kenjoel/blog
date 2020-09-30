package dao;

import models.Departments;
import models.User;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;
import java.util.Base64;

import static org.junit.Assert.assertEquals;

public class Sql2oDepartmentDaoTest {

    private static Sql2oDepartmentDao DepartmentDao;
    private static Sql2oUserDao userDao;
    private static Connection conn;


    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/newsportal_test"; //connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString,"moringa","://postgres");
        DepartmentDao = new Sql2oDepartmentDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing databse");
        DepartmentDao.clearAll();
//        conn.close();
    }

    @AfterClass //changed to @AfterClass (run once after all tests in this file completed)
    public static void shutDown() throws Exception{ //changed to static
//        conn.close(); // close connection once after this entire test file is finished
        System.out.println("connection closed");
    }


//    @Test
//    public void  testSaveWorks(){
//        Departments department = new Departments("DailyNation","Fighting Corruption", "www.DailyNation.com", "DailyNation@nation.com");
//        DepartmentDao.add(department);
//        assertEquals(1, DepartmentDao.getAll().size());
//    }

    @Test
    public void DepartmentReturnsusersCorrectly() throws Exception {
        User testUser = setupNewuser();
        userDao.add(testUser);

        User otherUser = setupNewuser();
        userDao.add(otherUser);

        Departments testDepartment = setupDepartment();
        DepartmentDao.add(testDepartment);
        DepartmentDao.addDepartmentToUser(testDepartment, testUser);
        DepartmentDao.addDepartmentToUser(testDepartment, otherUser);

        User[] users = {testUser, otherUser}; //oh hi what is this? Observe how we use its assertion below.
        System.out.println(Arrays.toString(users));
        assertEquals(Arrays.asList(users), DepartmentDao.getAllUsersByDepartment(testDepartment.getId()));
    }

    //helper functions
    public Departments setupDepartment() {
        Departments department = new Departments("DailyNation","Fighting Corruption", "www.DailyNation.com", "DailyNation@nation.com");
        DepartmentDao.add(department);
        return department;
    }

    public Departments setupAltDepartment(){
        Departments department1 = new Departments("DailyNation","Fighting Corruption", "www.DailyNation.com", "DailyNation@nation.com");
        DepartmentDao.add(department1);
        return department1;

    }

    public User setupNewuser(){
        User user = new User("JohnVick", "Head-Chief", "Assisting senior supervisor");
        return user;
    }
}
