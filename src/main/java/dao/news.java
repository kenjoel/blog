package dao;

import models.Departments;
import models.News;

import java.util.List;

public interface news {

    //create
    void add(News news);
    void addNewsToDepartment(News news, Departments departments);


    //Read
    News findNewsById(int userid);
    List<News>findNewsByDepartmentId(int departmentid);
    List<News>getAll();

    //Delete
    void deleteById(int userid);
    void clearAll();
}
