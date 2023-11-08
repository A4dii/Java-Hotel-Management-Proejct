package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class EmployeeController {
    private List<Employee> employees = new ArrayList<>();
    private static final String EMPLOYEE_CSV_FILE = "util/employees.csv";

    public EmployeeController() {
        loadEmployeesFromCSV();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        saveEmployeesToCSV();
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    // Implement other employee-related methods as needed

    private void loadEmployeesFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_CSV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String jobTitle = data[2];
                String contactInfo = data[3];
                employees.add(new Employee(id, name, jobTitle, contactInfo));
            }
        } catch (IOException e) {
            System.err.println("Error loading employee data from employees.csv: " + e.getMessage());
        }
    }

    private void saveEmployeesToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEE_CSV_FILE))) {
            for (Employee employee : employees) {
                String employeeData = String.format("%d,%s,%s,%s",
                        employee.getId(),
                        employee.getName(),
                        employee.getJobTitle(),
                        employee.getContactInfo());
                writer.write(employeeData);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving employee data to employees.csv: " + e.getMessage());
        }
    }
}

