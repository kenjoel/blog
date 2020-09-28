package dao;

import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

public class Sql2onewsDao implements news {

    private final Sql2o sql2o;
    public Sql2onewsDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(News news) {
        String sql = "INSERT INTO news (type, title, content, departmentId) VALUES (:type, :title, :content, :departmentId);"; //if you change your model, be sure to update here as well!
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




}
