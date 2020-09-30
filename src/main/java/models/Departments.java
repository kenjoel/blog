//package models;
//
//import java.util.Objects;
//
//public class Departments {
//
//    private int id;
//    private String name;
//    private String description;
//    private int size;
//
//    public Departments(String name, String description, int size) {
//        this.name = name;
//        this.description = description;
//        this.size = size;
//    }
//
//    public void setName (String name) {
//        this.name = name;
//    }
//
//    public void setDescription (String description) {
//        this.description = description;
//    }
//
//    public void setId (int id) {
//        this.id = id;
//    }
//
//    public void setSize (int size) {
//        this.size = size;
//    }
//
//    public String getName () {
//        return name;
//    }
//
//    public String getDescription () {
//        return description;
//    }
//
//    public int getId () {
//        return id;
//    }
//
//    public int getSize () {
//        return size;
//    }
//
//    @Override
//    public boolean equals (Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass () != o.getClass ())
//            return false;
//        Departments that = (Departments) o;
//        return id == that.id &&
//                size == that.size &&
//                Objects.equals (name, that.name) &&
//                Objects.equals (description, that.description);
//    }
//
//    @Override
//    public int hashCode () {
//        return Objects.hash (name, description, id, size);
//    }
//}


package models;

import java.util.Objects;

public class Departments {

    private String name;
    private String about;
    private String phone;
    private String email;
    private int id;


    public Departments(String name, String about) {
        this.name = name;
        this.about = about;
        this.phone = "no phone listed";
        this.email = "no email available";
    }

    public Departments(String name, String about, String website, String email) {
        this.name = name;
        this.about = about;
        this.phone = website;
        this.email = email;
    }

    //override equals n hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departments)) return false;
        Departments that = (Departments) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(about, that.about) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, about, phone, email, id);
    }


    ///Getters

    public String getName() {
        return name;
    }

    public String getAbout() {
        return about;
    }


    public String getWebsite() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setWebsite(String website) {
        this.phone = website;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }
}


