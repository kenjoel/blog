package models;

import java.util.Objects;

public class News {
    public int id;
    public String title;
    public String content;
    public int departmentId;

    public News(String title, String content){
        this.title = title;
        this.content = content;
    }

    public News(String title, String content, int departmentId){
        this.title = title;
        this.content = content;
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return id == news.id &&
                departmentId == news.departmentId &&
                Objects.equals(title, news.title) &&
                Objects.equals(content, news.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, departmentId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }


    public int getDepartmentId() {
        return departmentId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

}
