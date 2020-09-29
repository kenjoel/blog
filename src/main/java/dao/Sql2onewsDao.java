package dao;

import models.Departments;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2onewsDao implements news {

    private final Sql2o sql2o;
    public Sql2onewsDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(News news) {
        String sql = "INSERT INTO news_col (title, includes, userid) VALUES (:title, :includes, :userid);"; //if you change your model, be sure to update here as well!
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(news)
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<News> getAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM news_col")
                    .executeAndFetch(News.class);

        }
    }

    @Override
    public News findNewsById(int userid){
        String sql = "SELECT * FROM news_col WHERE userid = :userid";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("userid", userid)
                    .executeAndFetchFirst(News.class);
        }
    }

    @Override
    public void addNewsToDepartment(News news, Departments departments){
        String sql = "INSERT INTO department_news (newsid, departmentid) VALUES (:newsid, :departmentid)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("newsid", news.getId())
                    .addParameter("departmentid", departments.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<News> findNewsByDepartmentId(int departmentid) {
        ArrayList<News> news = new ArrayList<>();

        String joinQuery = "SELECT newsid FROM department_news WHERE departmentid = :departmentid";

        try (Connection con = sql2o.open()) {
            List<Integer> allNewsIds = con.createQuery(joinQuery)
                    .addParameter("departmentid", departmentid)
                    .executeAndFetch(Integer.class); //what is happening in the lines above?
            for (Integer eachId : allNewsIds){
                String newsQuery = "SELECT * FROM news_col WHERE id = :eachId";
                news.add(
                        con.createQuery(newsQuery)
                                .addParameter("eachId", eachId)
                                .executeAndFetchFirst(News.class));
            } //why are we doing a second sql query - set?
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return news;
    }


    @Override
    public void deleteById(int id) {
        String sql = "DELETE from news_col WHERE id = :id";
        String deleteJoin = "DELETE from department_news WHERE newsid = :newsid";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("newsid", id)
                    .executeUpdate();

        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }



    @Override
    public void clearAll(){
        String sql = "DELETE FROM news_col";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .executeUpdate();
        }catch (Error error){
            throw error;
        }
    }




}
