package dao;

import models.Departments;
import models.News;

import java.util.List;

public interface department {
    //Create
    void addDepartment(Departments departments);
    void addNewstoDepartment(Departments departments, News news);

    //Read
    List<Departments> getAll();
    List<News> getNewsByDepartment(int department);
    Departments getDepartmentById(int departmentsid);

    //Delete
    void deletingdepartmentAlsoDeletesJoin();
    void clearAll();
    void deleteById(int id);
}
