package dao;

import static org.junit.Assert.*;
import models.Departments;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


public class Sql2oUserDaoTest {

    private Connection conn;
    private Sql2oUserDao userDao;
    private Sql2oDepartmentDao DepartmentDao;

    private static  Sql2o sql2o;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/newsportal_test"; //connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString,"moringa","://postgres");
        userDao = new Sql2oUserDao(sql2o);
        DepartmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
        userDao.clearAll();
    }

    @After
    public void tearDown() throws Exception {
        userDao.clearAll();
        conn.close();
    }


    @Test
    public void addingFoodSetsId() throws Exception {
        User testUser = setupNewuser();
        int originalUserId = testUser.getId();
        userDao.add(testUser);
        assertNotEquals(originalUserId, testUser.getId());
    }

    @Test
    public void addedusersAreReturnedFromGetAll() throws Exception {
        User testuser = setupNewuser();
        userDao.add(testuser);
        assertEquals(1, userDao.getAll().size());
    }

    @Test
    public void nousersReturnsEmptyList() throws Exception {
        assertEquals(0, userDao.getAll().size());
    }

    @Test
    public void deleteByIdDeletesCorrectuser() throws Exception {
        User user = setupNewuser();
        userDao.add(user);
        userDao.deleteById(user.getId());
        assertEquals(0, userDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        User testUser = setupNewuser();
        User otherUser = setupNewuser();
        userDao.clearAll();
        assertEquals(0, userDao.getAll().size());
    }

    @Test
    public void addUserToDepartmentAddsTypeCorrectly() throws Exception {

        Departments testDepartment = setupDepartment();
        Departments altDepartment = setupAltDepartment();

        DepartmentDao.add(testDepartment);
        DepartmentDao.add(altDepartment);

        User testUser = setupNewuser();

        userDao.add(testUser);

        userDao.addUserToDepartment(testUser, testDepartment);
        userDao.addUserToDepartment(testUser, altDepartment);

        assertEquals(2, userDao.getAlldepartmentsForAuser(testUser.getId()).size());
    }

    @Test
    public void deleteingDepartmentAlsoUpdatesJoinTable() throws Exception {
        User testUser = new User("Drake", "Head-Chief", "Senior supervisor");
        userDao.add(testUser);

        Departments testDepartment = setupDepartment();
        DepartmentDao.add(testDepartment);

        Departments altDepartment = setupAltDepartment();
        DepartmentDao.add(altDepartment);

        DepartmentDao.addDepartmentToUser(testDepartment, testUser);
        DepartmentDao.addDepartmentToUser(altDepartment, testUser);

        DepartmentDao.deleteById(testDepartment.getId());
        assertEquals(0, DepartmentDao.getAllUsersByDepartment(testDepartment.getId()).size());
    }

    // helpers

    public User setupNewuser(){
        return new User("Imman", "Head-Chief", "Assisting senior supervisor");
    }

    public Departments setupDepartment (){
        Departments department = new Departments("General news","Financial status of the company", "www.story.com", "story@nation.com");
        DepartmentDao.add(department);
        return department;
    }

    public Departments setupAltDepartment (){
        Departments department = new Departments("Other news","Arsenal wins league", "www.arsenal.com", "arsenal@nation.com");
        DepartmentDao.add(department);
        return department;
}

}