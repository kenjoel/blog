package dao;

import models.Departments;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.ArrayList;
import java.util.List;


import org.sql2o.Sql2o;

public class Sql2oDepartmentDao implements department {

        private final Sql2o sql2o;

        public Sql2oDepartmentDao(Sql2o sql2o) {
            this.sql2o = sql2o;
        }

        @Override
        public void addDepartment(Departments departments) {
            String sql = "INSERT INTO departments ( name, description, size) VALUES (:name, :description, :size)";
            try (Connection con = sql2o.open()) {
                int id = (int) con.createQuery(sql, true)
                        .bind(departments)
                        .executeUpdate()
                        .getKey();
                departments.setId(id);
                System.out.println(departments.getId());
            } catch (Sql2oException ex) {
                System.out.println(ex);
            }
        }


        @Override
        public void addNewstoDepartment(Departments departments, News news){
            String sql = "INSERT INTO department_news (newsid) VALUES ( :newsid) WHERE departmentid,= :departmentid,";
            try(Connection conn = sql2o.open()){
                conn.createQuery(sql)
                        .addParameter( "newsid", news.getId())
                        .addParameter("departmentid", departments.getId())
                        .executeUpdate();
            }catch (Sql2oException ex){
                System.out.println(ex);
            }
        }

        @Override
        public List<News> getNewsByDepartment(int departmentid){
            ArrayList<News> news = new ArrayList<>();

            String joinQuery = "SELECT newsid FROM department_news WHERE departmentid = :departmentid";

            try (Connection con = sql2o.open()) {
                List<Integer> allNewsIds = con.createQuery(joinQuery)
                        .addParameter("departmentid", departmentid)
                        .executeAndFetch(Integer.class);
                for (Integer eachNews : allNewsIds){
                    String newsQuery = "SELECT * FROM news_col WHERE id = :id";
                    news.add(
                            con.createQuery(newsQuery)
                                    .addParameter("id", eachNews)
                                    .executeAndFetchFirst(News.class));
                }
            } catch (Sql2oException ex){
                System.out.println(ex);
            }
            return news;
        }


        @Override
        public Departments getDepartmentById(int departmentId) {
            try (Connection con = sql2o.open()) {
                return con.createQuery("SELECT * FROM departments WHERE id = :id")
                        .addParameter("id", departmentId)
                        .executeAndFetchFirst(Departments.class);
            }
        }

        @Override
        public void clearAll(){
            String sql = "DELETE FROM departments";

            try(Connection con = sql2o.open()){
                con.createQuery(sql)
                        .executeUpdate();
            }catch (Sql2oException ex) {
                System.out.println(ex);
            }
        }

        @Override
        public void deleteById(int id) {
            String sql = "DELETE from departments WHERE id=:id";
            String deleteJoin = "DELETE from department_news WHERE departmentid = :departmentid";
            try (Connection con = sql2o.open()) {
                con.createQuery(sql)
                        .addParameter("id", id)
                        .executeUpdate();
                con.createQuery(deleteJoin)
                        .addParameter("departmentid", id)
                        .executeUpdate();
            } catch (Sql2oException ex) {
                System.out.println(ex);
            }
        }


        @Override
        public List<Departments> getAll(){
            String sql = "SELECT * FROM departments";
            try(Connection conn = sql2o.open()){
                return conn.createQuery(sql)
                        .executeAndFetch(Departments.class);
            }
        }

        @Override
        public void deletingdepartmentAlsoDeletesJoin(){
            String sql = "DELETE FROM departments WHERE id = :id";
            String sql1 = "DELETE FROM department_news WHERE departmentid=:departmentid";

            try(Connection connection = sql2o.open()){
                connection.createQuery(sql)
                        .executeUpdate();
                connection.createQuery(sql1).executeUpdate();
            }catch (Sql2oException err){
                System.out.println(err);
            }
        }
    }


