import com.google.gson.Gson;
import dao.Sql2oDepartmentDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUserDao;
import exceptions.ApiExceptions;
import models.Departments;
import models.News;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }



    public static void main(String[] args) {

        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        Sql2oUserDao userDao;
        Sql2oDepartmentDao DepartmentDao;
        Sql2oNewsDao NewsDao;
        Connection conn;
        Gson gson = new Gson();
        //h2 DB
//        String connectionString = "jdbc:h2:~/newsportal.db;INIT=RUNSCRIPT from 'classpath:DB/create.sql'";
//        Sql2o sql2o = new Sql2o(connectionString, "", "");




        String connectionString = "jdbc:postgresql://ec2-34-197-212-240.compute-1.amazonaws.com:5432/ddttbhua5hnvau"; //connect to newsportal, not newsportal_test!
        Sql2o sql2o = new Sql2o(connectionString, "jharusqetiuhgh", "://postgres");  //Ubuntu Sql2o sql2o = new Sql2o(connectionString, "user", "1234");

        DepartmentDao = new Sql2oDepartmentDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        NewsDao = new Sql2oNewsDao(sql2o);
        conn = sql2o.open();

        get("/", "application/json", (req, res) ->
                "{\"Ola!\":\"Hello Welcome to News-Portal-Api(NPA). Feel free to explore the different routes, check readme :)\"}");


        get("/departments", "application/json", (req, res) -> { //accept a request in format JSON from an app
            System.out.println(DepartmentDao.getAll());
            if(DepartmentDao.getAll().size() > 1){
              return gson.toJson(DepartmentDao.getAll());
            }else {
                return "{No departments yet}";
            }
        });

        get("/departments/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            int departmentid = Integer.parseInt(req.params("id"));

            if (DepartmentDao.findById(departmentid) == null){
                throw new ApiExceptions(404, String.format("No Department with the id: \"%s\" exists", req.params("id")));
            }
            return gson.toJson(DepartmentDao.findById(departmentid));
        });

//        get("/departments", "application/json", (req, res) -> {
//            System.out.println(DepartmentDao.getAll());
//
//            if(DepartmentDao.getAll().size() > 0){
//                return gson.toJson(DepartmentDao.getAll());
//            }
//
//            else {
//                return "{\"message\":\"I'm sorry, but no departments are currently listed in the database.\"}";
//            }
//
//        });

        get("/users/:id", "application/json", (request, response) -> {
            int target = Integer.parseInt(request.params("id"));
            User user =  userDao.findUserById(target);
            if(user != null){
                return gson.toJson(user);
            }else{
                throw new Error("There currently no departments with the aid ID");
            }
        });



        get("/departments/:id/users", "application/json", (req, res) -> {
            int departmentid = Integer.parseInt(req.params("id"));
            Departments departmentToFind = DepartmentDao.findById(departmentid);
            if (departmentToFind == null){
                throw new Error(String.format("No Department with the id: \"%s\" exists", req.params("id")));
            }
            else if (DepartmentDao.getAllUsersByDepartment(departmentid).size()==0){
                return "{\"message\":\"I'm sorry, but no users are listed for this Department.\"}";
            }
            else {
                return gson.toJson(DepartmentDao.getAllUsersByDepartment(departmentid));
            }
        });



        get("/users/:id/departments", "application/json", (req, res) -> {
            int UserId = Integer.parseInt(req.params("id"));
            User userToFind = userDao.findUserById(UserId);
            if (userToFind == null){
                throw new Error(String.format("No user with the id: \"%s\" exists", req.params("id")));
            }
            else if (userDao.getAlldepartmentsForAuser(UserId).size()==0){
                return "{\"message\":\"I'm sorry, but no departments are listed for this user.\"}";
            }
            else {
                return gson.toJson(userDao.getAlldepartmentsForAuser(UserId));
            }
        });



        get("/departments/:id/news", "application/json", (req, res) -> { //// TODO: 1/18/18 generalize this route so that it can be used to return either sorted news or unsorted ones.
            int departmentid = Integer.parseInt(req.params("id"));
            Departments departmentToFind = DepartmentDao.findById(departmentid);
            List<News> allNews;
            if (departmentToFind == null){
               throw new ApiExceptions(404, String.format("No Department with the id: \"%s\" exists", req.params("id")));
            }
            allNews = NewsDao.getAllNewsByDepartment(departmentid);
            return gson.toJson(allNews);
        });


        //POST REQUESTS

        post("/departments/new", "application/json", (req, res) -> {
            Departments department = gson.fromJson(req.body(), Departments.class);
            DepartmentDao.add(department);
            res.status(201);
            return gson.toJson(department);
        });

//        post("/departments/:departmentid/news/new", "application/json", (req, res) -> {
//            int departmentid = Integer.parseInt(req.params("departmentid"));
//            News News = gson.fromJson(req.body(), News.class);
//
//            News.setdepartmentid(departmentid); //we need to set this separately because it comes from our route, not our JSON input.
//            NewsDao.add(News);
//            res.status(201);
//            return gson.toJson(News);
//        });


        post("/departments/:departmentid/news/new", "application/json", (req, res) -> {
            int departmentid = Integer.parseInt(req.params("departmentid"));
            News news = gson.fromJson(req.body(), News.class);
            news.setdepartmentid(departmentid);
            NewsDao.add(news);
            res.status(201);
            return gson.toJson(news);
        });

        post("/users/new", "application/json", (req, res) -> {
            User user = gson.fromJson(req.body(), models.User.class);
            userDao.add(user);
            res.status(201);
            return gson.toJson(user);
        });



        post("/Department/:id/update", "application/json", (request, response) -> {
            int departmentid = Integer.parseInt(request.params("id"));
            Departments target = DepartmentDao.findById(departmentid);
            Departments update = gson.fromJson(request.body(), Departments.class);
            return null;
        });

        post("/departments/:departmentid/user/:Userid", "application/json", (req, res) -> {
            int departmentid = Integer.parseInt(req.params("departmentid"));
            int UserId = Integer.parseInt(req.params("Userid"));
            Departments department = DepartmentDao.findById(departmentid);
            User user = userDao.findUserById(UserId);

            if (department != null && user != null){
                //both exist and can be associated - we should probably not connect things that are not here.
                userDao.addUserToDepartment(user, department);
                res.status(201);
                return gson.toJson(String.format("Department '%s' has user '%s' within it's gated community", department.getName(), user.getName()));
            }
            else {
                throw new Error("Department or user does not exist");
            }
        });

        //postgresql-rectangular-17479
        //ec2-34-197-212-240.compute-1.amazonaws.com
        //ddttbhua5hnvau

        //FILTERS
        exception(ApiExceptions.class, (exc, req, res) -> {
            ApiExceptions err = exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json"); //after does not run in case of an exception.
            res.status(err.getStatusCode()); //set the status
            res.body(gson.toJson(jsonMap));  //set the output.
        });

        after((req, res) ->{
            res.type("application/json");
        });
    }
}
