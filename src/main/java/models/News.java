package models;

import java.util.Date;
import java.util.Objects;

public class News {
    private int id;
    private String headline;
    private String content;
    private String author;
    private int departmentid; //will be used to connect Department to News (one-to-many)

    public News(String headline, String content, String author, int departmentid) {
        this.headline = headline;
        this.content = content;
        this.author = author;
        this.departmentid = departmentid;
         //we'll make me in a minute
    }

    //Create comparison



    //Hashcode n equals override


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return id == news.id &&
                departmentid == news.departmentid &&
                Objects.equals(headline, news.headline) &&
                Objects.equals(content, news.content) &&
                Objects.equals(author, news.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(headline, content, author, id, departmentid);
    }

    //Getters


    public String getHeadline() {
        return headline;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }


    public int getId() {
        return id;
    }

    public int getdepartmentid() {
        return departmentid;
    }


    //Setters

    public void setContent(String content) {
        this.content = content;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setdepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }


}