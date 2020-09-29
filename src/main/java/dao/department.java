package dao;

import models.Departments;
import models.News;

import java.util.List;

public interface department {
//    //Create
//    void addDepartment(Departments departments);
//    void addNewstoDepartment(Departments departments, News news);
//
//    //Read
//    List<Departments> getAll();
//    List<News> getNewsByDepartment(int department);
//    Departments getDepartmentById(int departmentsid);
//
//    //Delete
//    void deletingdepartmentAlsoDeletesJoin();
//    void clearAll();
//    void deleteById(int id);

    //create
    void add(Departments department);
    void addDepartmentToUser(Departments department, user user);

    //read
    List<Departments> getAll();
    List<user> getAllUsersByDepartment(int departmentid);


    Departments  findById(int id);
    // List<user> getAllusersForADepartment(int departmentid);

    //update
    void update(int id, String name, String about, String website, String email);

    //delete
    void deleteById(int id);
    void clearAll();
}
