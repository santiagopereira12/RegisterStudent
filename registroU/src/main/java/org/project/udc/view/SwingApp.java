package org.project.udc.view;

import org.project.udc.model.Employee;
import org.project.udc.repository.EmployeeRepository;
import org.project.udc.repository.Reposirory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class SwingApp extends JFrame {
    private final Reposirory<Employee> employeeReposirory;
    private final JTable employeeTable;

    public SwingApp(){
        setTitle("Gestion de Estudiantes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 230);

        employeeTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton agregarButton = new JButton("Agregar");
        JButton actualizarButton = new JButton("Actualizar");
        JButton eliminarButton = new JButton("Eliminar");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(agregarButton);
        buttonPanel.add(actualizarButton);
        buttonPanel.add(eliminarButton);
        add(buttonPanel, BorderLayout.SOUTH);

        agregarButton.setBackground(new Color(44,200,110));
        agregarButton.setForeground(Color.BLACK);
        agregarButton.setFocusPainted(false);

        actualizarButton.setBackground(new Color(50,160,220));
        agregarButton.setForeground(Color.BLACK);
        agregarButton.setFocusPainted(false);

        eliminarButton.setBackground(new Color(231,76,60));
        eliminarButton.setForeground(Color.BLACK);
        agregarButton.setFocusPainted(false);

        employeeReposirory = new EmployeeRepository();

        refreshEmployeeTable();

        agregarButton.addActionListener(e -> {
            try{
                agregarEstudiante();
            }catch (SQLException ex){
                throw new RuntimeException(ex);
            }
        });

        actualizarButton.addActionListener(e -> actualizarEstudiante());

        eliminarButton.addActionListener(e -> eliminarEstudiante());
    }

    private void refreshEmployeeTable(){
        try{
            List<Employee> employees = employeeReposirory.findAll();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Nombre");
            model.addColumn("Apellido");
            model.addColumn("Email");
            model.addColumn("Genero");
            model.addColumn("Especialidad");
            model.addColumn("Universidad");
            model.addColumn("Jornada");

            for (Employee employee : employees){
                Object[] rowData = {
                        employee.getId(),
                        employee.getName(),
                        employee.getLastName(),
                        employee.getEmail(),
                        employee.getGender(),
                        employee.getSpeciality(),
                        employee.getUniversity(),
                        employee.getWorckingDay()
                };
                model.addRow(rowData);
            }
            employeeTable.setModel(model);
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(this,"Error al obtener los registros.", "Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarEstudiante() throws SQLException{
        JTextField nameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField specialityField = new JTextField();
        JTextField universityField = new JTextField();
        JTextField workingDayField = new JTextField();

        Object[] fields = {
                "Nombre", nameField,
                "Apellido", lastNameField,
                "Email", emailField,
                "Genero", genderField,
                "Especialidad", specialityField,
                "Universidad", universityField,
                "Jornada", workingDayField
        };
        int result = JOptionPane.showConfirmDialog(this,fields,"Agregar Registro",JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION){
            Employee employee = new Employee();
            employee.setName(nameField.getText());
            employee.setLastName(lastNameField.getText());
            employee.setEmail(emailField.getText());
            employee.setGender(genderField.getText());
            employee.setSpeciality(specialityField.getText());
            employee.setUniversity(universityField.getText());
            employee.setWorckingDay(workingDayField.getText());

            employeeReposirory.save(employee);

            refreshEmployeeTable();

            JOptionPane.showMessageDialog(this,"Estudiante Registrado","Exito",JOptionPane.INFORMATION_MESSAGE);

        }
    }

    private void actualizarEstudiante(){
        String estudianteIdStr = JOptionPane.showInputDialog(this,"Ingrese ID del estudiante","Actualizar Estudiante",JOptionPane.QUESTION_MESSAGE);
        if (estudianteIdStr != null){
            try{
                int estudianteId = Integer.parseInt(estudianteIdStr);

                Employee student = employeeReposirory.getById(estudianteId);

                if (student != null){
                    JTextField nameField = new JTextField(student.getName());
                    JTextField lastNameField = new JTextField(student.getLastName());
                    JTextField emailField = new JTextField(student.getEmail());
                    JTextField genderField = new JTextField(student.getGender());
                    JTextField specialityField = new JTextField(student.getSpeciality());
                    JTextField universityField = new JTextField(student.getUniversity());
                    JTextField workingDayField = new JTextField(student.getWorckingDay());

                    Object[] fields = {
                            "nombre", nameField,
                            "Apellido", lastNameField,
                            "Email", emailField,
                            "Genero", genderField,
                            "Especialidad", specialityField,
                            "Universidad", universityField,
                            "Jornada", workingDayField
                    };
                    int confirmResult = JOptionPane.showConfirmDialog(this,fields,"Actualizar Registro",JOptionPane.OK_CANCEL_OPTION);
                    if (confirmResult == JOptionPane.OK_OPTION){
                        student.setName(nameField.getText());
                        student.setLastName(lastNameField.getText());
                        student.setEmail(emailField.getText());
                        student.setGender(genderField.getText());
                        student.setSpeciality(specialityField.getText());
                        student.setUniversity(specialityField.getText());
                        student.setWorckingDay(workingDayField.getText());

                        employeeReposirory.save(student);

                        refreshEmployeeTable();
                    }
                }else {
                    JOptionPane.showMessageDialog(this,"No se encontro el registro","Error",JOptionPane.ERROR_MESSAGE);
                }
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(this,"Ingrese un valor numerico valido","Error",JOptionPane.ERROR_MESSAGE);
            }catch (SQLException e){
                JOptionPane.showMessageDialog(this,"Error al Obtener registro en la base de datos","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarEstudiante(){
        String studentIdStr = JOptionPane.showInputDialog(this,"Ingrese el Id del estudiante","Eliminar estudiante",JOptionPane.QUESTION_MESSAGE);
        if (studentIdStr != null){
            try{
                int student = Integer.parseInt(studentIdStr);
                int confirm = JOptionPane.showConfirmDialog(this,"¿Seguro desea Eliminar el registro?","Confirmar Eliminación",JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION){
                    employeeReposirory.delete(student);
                    refreshEmployeeTable();
                }
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(this,"Ingrese un ID numerico segn los registros","Error",JOptionPane.ERROR_MESSAGE);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }
}
