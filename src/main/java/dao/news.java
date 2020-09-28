package dao;

import models.News;

import java.util.List;

public interface news {

    //create
    void add(News news);
    void addNewsToDepartment();


    //Read
    List<News>findReviewByDepartmentId();

    //Delete
//    void delete();
}
