package dao;

import models.Departments;
import models.User;

import java.util.List;

public interface UserDao {

    void add(User user);
    void addUserToDepartment(User user, Departments department);


    //read
    List<User> getAll();
    User findUserById(int id);
    List<Departments> getAlldepartmentsForAuser(int id);

    //update
    //omit for now

    //delete
    void deleteById(int id);

    void clearAll();
}
