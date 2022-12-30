package employee;

import java.util.List;

public class EmployeeOld {
    private int id;
    private String email;
    private String firstname;
    private String lastname;
    private String avatar;
    private List<String> jobs;
    private Foods foods;

    public EmployeeOld() {
    }

    public EmployeeOld(int id, String email, String firstname, String lastname, String avatar, List<String> jobs, Foods foods) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.avatar = avatar;
        this.jobs = jobs;
        this.foods = foods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getJobs() {
        return jobs;
    }

    public void setJobs(List<String> jobs) {
        this.jobs = jobs;
    }

    public Foods getFoods() {
        return foods;
    }

    public void setFoods(Foods foods) {
        this.foods = foods;
    }
}

/*

[
        {
        "id": 8432,
        "email": "lindsay.ferguson@reqres.in",
        "first_name": "Lindsay",
        "last_name": "Ferguson",
        "avatar": "https://reqres.in/img/faces/8-image.jpg",
        "jobs":["tester", "teacher"],
        "foods" : {
        "breakfast": "Bread",
        "Lunch": "Spagetti",
        "Dinner": ["Milk","Beans"]
        }
        }
        ]

 */

