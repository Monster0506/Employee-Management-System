import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_NAME = "employees.empdata";

    public static void loadEmployees(EmployeeService employeeService) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            List<String> employeeData = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (line.equals("[Employee]")) {
                    if (!employeeData.isEmpty()) {
                        employeeService.addEmployee(Employee.fromString(employeeData));
                        employeeData.clear();
                    }
                }
                employeeData.add(line);
            }
            // Add the last employee if file doesn’t end with a new section
            if (!employeeData.isEmpty()) {
                employeeService.addEmployee(Employee.fromString(employeeData));
            }
        } catch (IOException e) {
            System.out.println("Error loading employees: " + e.getMessage());
        }
    }

    public static void saveEmployees(List<Employee> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("# Employee Data File (.empdata)");
            writer.newLine();

            for (Employee employee : employees) {
                writer.write(employee.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving employees: " + e.getMessage());
        }
    }
}

