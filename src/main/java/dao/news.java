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
//    List<News> getAllNewsByDepartmentSortedNewestToOldest(int departmentid);


    //update
    //omit for now

    //delete
    void deleteById(int id);
    void clearAll();
}
