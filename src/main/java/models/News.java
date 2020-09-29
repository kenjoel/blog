package models;

import java.util.Objects;

public class News {

    private int id;
    private String type;
    private int department_id;
    private int userid;
    private String title;
    private String includes;
    private final String NEWS_TYPE="general";



    public News(String title, String includes, int userid) {
        this.title = title;
        this.includes = includes;
        this.userid=userid;
        this.type= NEWS_TYPE;
        this.department_id=0;
    }

    public News(String title, String includes,int department_id, int userid){
        this.title = title;
        this.includes = includes;
        this.userid=userid;
        this.department_id = department_id;
        this.type="department";
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return userid;
    }

    public String getNews_type() {
        return type;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public String getTitle() {
        return title;
    }


    public String getIncludes() {
        return includes;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id &&
                department_id == news.department_id &&
                userid == news.userid &&
                Objects.equals(news, news.type) &&
                Objects.equals(title, news.title) &&
                Objects.equals(includes, news.includes) &&
                Objects.equals(NEWS_TYPE, news.NEWS_TYPE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, department_id, userid, title, includes, NEWS_TYPE);
    }
}
