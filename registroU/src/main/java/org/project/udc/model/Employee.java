package org.project.udc.model;

public class Employee {
    private Integer id;
    private String name;
    private String lastName;
    private String email;
    private String gender;
    private String speciality;
    private String university;
    private String worckingDay;

    public Employee() {
    }

    public Employee(Integer id, String name, String lastName, String email, String gender, String speciality, String university, String worckingDay) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.speciality = speciality;
        this.university = university;
        this.worckingDay = worckingDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getWorckingDay() {
        return worckingDay;
    }

    public void setWorckingDay(String worckingDay) {
        this.worckingDay = worckingDay;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", speciality='" + speciality + '\'' +
                ", university='" + university + '\'' +
                ", worckingDay='" + worckingDay + '\'' +
                '}';
    }
}
