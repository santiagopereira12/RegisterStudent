package org.project.udc.repository;

import org.project.udc.model.Employee;
import org.project.udc.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Reposirory<Employee>{

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }
    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try(Statement myStamt = getConnection().createStatement();
            ResultSet myResult = myStamt.executeQuery("SELECT * FROM student");){
            while (myResult.next()){
                Employee e = createEmployee(myResult);
                employees.add(e);
            }
        }
        return employees;
    }

    @Override
    public Employee getById(Integer id) throws SQLException {
        Employee employee = null;
        try(PreparedStatement myPrep = getConnection().prepareStatement("SELECT * FROM student WHERE id = ?");){
            myPrep.setInt(1,id);
            try(ResultSet myResult = myPrep.executeQuery()){
                if (myResult.next()){
                    employee = createEmployee(myResult);
                }
            }
        }
        return employee;
    }

    @Override
    public void save(Employee employee) throws SQLException {
        String sql;
        if (employee.getId() != null && employee.getId() > 0){
            sql = "UPDATE student set name = ?, lastName = ?, email = ?, gender = ?, speciality = ?, university = ?, workingDay = ? WHERE id = ?";
        }else{
            sql = "INSERT INTO student (name,lastName,email,gender,speciality,university,workingDay) VALUES (?,?,?,?,?,?,?)";
        }
        try (PreparedStatement myPrep = getConnection().prepareStatement(sql)){
            myPrep.setString(1,employee.getName());
            myPrep.setString(2,employee.getLastName());
            myPrep.setString(3,employee.getEmail());
            myPrep.setString(4,employee.getGender());
            myPrep.setString(5,employee.getSpeciality());
            myPrep.setString(6, employee.getUniversity());
            myPrep.setString(7, employee.getWorckingDay());

            if (employee.getId() != null && employee.getId() > 0){
                myPrep.setInt(8, employee.getId());
            }
            myPrep.executeUpdate();
        }catch (SQLException em){
            em.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM student WHERE id = ?";
        try(PreparedStatement myPrep = getConnection().prepareStatement(sql)){
            myPrep.setInt(1,id);
            myPrep.executeUpdate();
        }

    }

    private Employee createEmployee(ResultSet myResult) throws SQLException {
        Employee em = new Employee();
        em.setId(myResult.getInt("id"));
        em.setName(myResult.getString("name"));
        em.setLastName(myResult.getString("lastName"));
        em.setEmail(myResult.getString("email"));
        em.setGender(myResult.getString("gender"));
        em.setSpeciality(myResult.getString("speciality"));
        em.setUniversity(myResult.getString("university"));
        em.setWorckingDay(myResult.getString("workingDay"));
        return em;
    }
}
