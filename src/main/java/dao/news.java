package dao;

import models.Departments;
import models.News;

import java.util.List;

public interface news {

    //create
    void add(News news);

    //read
    List<News> getAll();
    List<News> getAllNewsByDepartment(int departmentid);


    //update

    //delete
    void deleteById(int id);
    void clearAll();
}
